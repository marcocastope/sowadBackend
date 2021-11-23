package com.marcocastope.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcocastope.model.Incident;
import com.marcocastope.repository.IncidentRepository;

@Service
public class IncidentServiceImpl implements IncidentService {

	@Autowired
	private IncidentRepository repository;

	@Override
	@Transactional
	public void create(Incident entity) {
		repository.save(entity);
	}

	@Override
	@Transactional
	public void update(Incident entity) {
		repository.save(entity);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Incident> findAll() {
		// TODO Auto-generated method stub
		return (List<Incident>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Incident> find(Integer id) {
		return repository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Incident> getIncidentsByUserId(Integer iduser) {
		// TODO Auto-generated method stub
		return repository.getIncidentsByUserId(iduser);
	}

}
