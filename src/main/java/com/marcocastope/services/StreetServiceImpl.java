package com.marcocastope.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcocastope.model.Street;
import com.marcocastope.repository.StreetRepository;

@Service
public class StreetServiceImpl implements StreetService {

	@Autowired
	private StreetRepository repository;
	
	@Override
	public void create(Street entity) {
		repository.save(entity);
	}

	@Override
	public void update(Street entity) {
		repository.save(entity);
	}

	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public List<Street> findAll() {
		return (List<Street>) repository.findAll();
	}

	@Override
	public Optional<Street> find(Integer id) {
		return repository.findById(id);
	}

}
