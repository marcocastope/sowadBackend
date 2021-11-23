package com.marcocastope.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "incident")
public class Incident {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idincident;
	@Column(name = "description", nullable = false, length = 100)
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	@ManyToOne
	@JoinColumn(name = "status", nullable = false, columnDefinition = "int default 1")
	private Status status;
	@ManyToOne
	@JoinColumn(name = "street", nullable = false)
	private Street street;
	@ManyToOne
	@JoinColumn(name = "user", nullable = false)
	private User user;

	public Incident() {

	}

	public Incident(int idincident, String description, Date date, Status status, Street street, User user) {
		this.idincident = idincident;
		this.description = description;
		this.date = date;
		this.status = status;
		this.street = street;
		this.user = user;
	}

	public int getIdincident() {
		return idincident;
	}

	public void setIdincident(int idincident) {
		this.idincident = idincident;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Street getStreet() {
		return street;
	}

	public void setStreet(Street street) {
		this.street = street;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
