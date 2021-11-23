package com.marcocastope.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "street")
public class Street {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idstreet;
	@Column(name = "address", nullable = false, length = 40)
	private String address;

	public int getIdstreet() {
		return idstreet;
	}

	public void setIdstreet(int idstreet) {
		this.idstreet = idstreet;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
