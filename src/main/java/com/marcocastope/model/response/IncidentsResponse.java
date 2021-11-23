package com.marcocastope.model.response;

import java.util.List;

import com.marcocastope.model.Incident;

public class IncidentsResponse {

	private List<Incident> incidents;
	
	public IncidentsResponse() {
		
	}

	public IncidentsResponse(List<Incident> incidents) {
		this.incidents = incidents;
	}

	public List<Incident> getIncidents() {
		return incidents;
	}

	public void setIncidents(List<Incident> incidents) {
		this.incidents = incidents;
	}

}
