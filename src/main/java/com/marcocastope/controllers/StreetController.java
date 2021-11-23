package com.marcocastope.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcocastope.model.Street;
import com.marcocastope.model.response.StreetsResponse;
import com.marcocastope.services.StreetService;

@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/api/street")
public class StreetController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StreetService service;

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllIncidents() {
		log.info("getAllStreets() .");
		ResponseEntity<?> resp = null;
		try {
			List<Street> streets = service.findAll();
			if (streets.isEmpty()) {
				resp = new ResponseEntity<String>("No hay datos", HttpStatus.NO_CONTENT);
			}
			StreetsResponse response = new StreetsResponse(streets);
			resp = new ResponseEntity<StreetsResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error " + e.getMessage());
			resp = new ResponseEntity<String>("No hay datos válidos", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Street street) {
		log.info("saveStreet() .");
		ResponseEntity<String> resp = null;
		try {
			if (street == null) {
				resp = new ResponseEntity<String>("Los campos son requeridos.", HttpStatus.BAD_REQUEST);
			}
			service.create(street);
			resp = new ResponseEntity<String>("Street guardada con éxito.", HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("No data available", e.getMessage());
			resp = new ResponseEntity<String>("No data available", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<?> getIncident(@PathVariable("id") Integer id) {
		log.info("getStreet() .");

		ResponseEntity<?> resp = null;
		try {
			if (id == null) {
				resp = new ResponseEntity<String>("invalid id", HttpStatus.BAD_REQUEST);
			}

			Optional<Street> street = service.find(id);
			if (!street.isPresent()) {
				resp = new ResponseEntity<String>("No se encotró el recurso", HttpStatus.NOT_FOUND);
			}
			resp = new ResponseEntity<Street>(street.get(), HttpStatus.OK);
		} catch (Exception e) {
			log.error("No data available", e.getMessage());
			resp = new ResponseEntity<String>("No data available", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}
}
