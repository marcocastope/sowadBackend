package com.marcocastope.utils;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.marcocastope.model.Incident;
import com.marcocastope.model.response.IncidentsResponse;

public class ValidateUtils {

	public static ResponseEntity<?> validateIncidentData(List<Incident> incidents) {
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
