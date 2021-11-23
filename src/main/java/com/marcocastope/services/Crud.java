package com.marcocastope.services;

import java.util.List;
import java.util.Optional;

public interface Crud<T> {

	void create(T entity);

	void update(T entity);

	void delete(Integer id);

	List<T> findAll();

	Optional<T> find(Integer id);
}
