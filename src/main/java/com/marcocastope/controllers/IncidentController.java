package com.marcocastope.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcocastope.model.Incident;
import com.marcocastope.model.Status;
import com.marcocastope.model.Street;
import com.marcocastope.model.User;
import com.marcocastope.model.request.SaveIncidentRequest;
import com.marcocastope.model.response.GenericResponse;
import com.marcocastope.model.response.IncidentsResponse;
import com.marcocastope.services.IncidentService;
import com.marcocastope.services.UserService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/incident")
public class IncidentController {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IncidentService services;
	@Autowired
	private UserService userService;

	@GetMapping("/getAll")
	public ResponseEntity<?> getAll() {
		log.info("getAllIncidents() .");
		ResponseEntity<?> resp = null;
		try {
			List<Incident> incidents = services.findAll();
			resp = validateIncidentData(incidents);
		} catch (

		Exception e) {
			log.error("Error " + e.getMessage());
			resp = new ResponseEntity<String>("No hay datos válidos", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@GetMapping("/getAll/{id}")
	public ResponseEntity<?> getAllIncidents(@PathVariable("id") Integer id) {
		log.info("getAllIncidents() .");
		ResponseEntity<?> resp = null;
		try {

			Optional<User> user = userService.find(id);
			User currentUser = user.get();
			log.info("getAllIncidents()" + currentUser.getRole().getIdrole());
			if (currentUser.getRole().getIdrole().contains("2")) {
				List<Incident> incidents = services.getIncidentsByUserId(id);
				resp = validateIncidentData(incidents);
			} else {
				List<Incident> incidents = services.findAll();
				resp = validateIncidentData(incidents);
			}

		} catch (Exception e) {
			log.error("Error " + e.getMessage());
			resp = new ResponseEntity<String>("No hay datos válidos", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@PostMapping("/save")
	public ResponseEntity<GenericResponse> save(@RequestBody SaveIncidentRequest incident) {
		log.info("saveIncident() .");
		ResponseEntity<GenericResponse> resp = null;
		try {
			if (incident == null) {
				resp = new ResponseEntity<GenericResponse>(new GenericResponse("Los campos son requeridos."),
						HttpStatus.BAD_REQUEST);
			}
			Street street = new Street();
			User user = new User();
			Incident inc = new Incident();
			inc.setDescription(incident.getDescription());
			street.setIdstreet(incident.getStreet());
			inc.setStreet(street);
			user.setIduser(incident.getUser());
			inc.setUser(user);
			inc.setDate(new Date());
			inc.setStatus(new Status(1, "da"));
			services.create(inc);
			resp = new ResponseEntity<GenericResponse>(new GenericResponse("Incidencia guardada con éxito"),
					HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("No data available" + e.getMessage());
			resp = new ResponseEntity<GenericResponse>(new GenericResponse("No data available"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<?> getIncident(@PathVariable("id") Integer id) {
		log.info("getIncident() .");

		ResponseEntity<?> resp = null;
		try {
			if (id == null) {
				resp = new ResponseEntity<String>("invalid id", HttpStatus.BAD_REQUEST);
			}

			Optional<Incident> incident = services.find(id);
			if (!incident.isPresent()) {
				resp = new ResponseEntity<String>("No se encotró el recurso", HttpStatus.NOT_FOUND);
			}
			resp = new ResponseEntity<Incident>(incident.get(), HttpStatus.OK);
		} catch (Exception e) {
			log.error("No data available", e.getMessage());
			resp = new ResponseEntity<String>("No data available", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@PutMapping("/modify/{id}")
	public ResponseEntity<Incident> updateIncident(@PathVariable Integer id, @RequestBody Incident incident) {
		ResponseEntity<Incident> resp = null;
		try {

			Optional<Incident> currentIncident = services.find(id);
			currentIncident.get().setDescription(incident.getDescription());
			currentIncident.get().setStreet(incident.getStreet());
			services.update(currentIncident.get());
			resp = new ResponseEntity<Incident>(currentIncident.get(), HttpStatus.RESET_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<GenericResponse> deleteIncident(@PathVariable Integer id) {
		ResponseEntity<GenericResponse> resp = null;
		try {
			services.delete(id);
			resp = new ResponseEntity<GenericResponse>(new GenericResponse("incident eliminado"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<GenericResponse>(new GenericResponse("Unable to delete Incident"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return resp;
	}

	private ResponseEntity<?> validateIncidentData(List<Incident> incidents) {
		ResponseEntity<?> resp;
		if (incidents.isEmpty()) {
			resp = new ResponseEntity<String>("No hay datos", HttpStatus.NO_CONTENT);
		} else {
			IncidentsResponse response = new IncidentsResponse(incidents);
			resp = new ResponseEntity<IncidentsResponse>(response, HttpStatus.OK);
		}
		return resp;
	}
}
