package com.marcocastope.model.request;

public class SaveIncidentRequest {

	private String description;
	private int street;
	private int user;

	public SaveIncidentRequest(String description, int street, int user) {
		super();
		this.description = description;
		this.street = street;
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStreet() {
		return street;
	}

	public void setStreet(int street) {
		this.street = street;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}
}
