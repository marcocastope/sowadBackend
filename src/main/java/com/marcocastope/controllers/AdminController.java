package com.marcocastope.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcocastope.model.Incident;
import com.marcocastope.services.IncidentService;
import com.marcocastope.utils.ValidateUtils;

@RestController
@RequestMapping("/api/admin/")
public class AdminController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IncidentService services;

	@GetMapping("incident/getAll")
	public ResponseEntity<?> getAll() {
		log.info("getAllIncidents() .");
		ResponseEntity<?> resp = null;
		try {
			List<Incident> incidents = services.findAll();
			resp = ValidateUtils.validateIncidentData(incidents);
		} catch (

		Exception e) {
			log.error("Error " + e.getMessage());
			resp = new ResponseEntity<String>("No hay datos válidos", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

}
