package com.marcocastope.model.response;

import java.util.List;

import com.marcocastope.model.Street;

public class StreetsResponse {

	private List<Street> streets;

	public StreetsResponse(List<Street> streets) {
		this.streets = streets;
	}

	public List<Street> getStreets() {
		return streets;
	}

	public void setStreets(List<Street> streets) {
		this.streets = streets;
	}

}
