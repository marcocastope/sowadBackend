package com.marcocastope.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.marcocastope.model.Incident;

public interface IncidentService extends Crud<Incident> {
	List<Incident> getIncidentsByUserId(@Param("iduser") Integer iduser);
}
