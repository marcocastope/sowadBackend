package com.marcocastope.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.marcocastope.model.Incident;

@Repository
public interface IncidentRepository extends CrudRepository<Incident, Integer> {

	@Query("from Incident i where i.user.iduser = :iduser")
	List<Incident> getIncidentsByUserId(@Param("iduser") Integer iduser);
}
