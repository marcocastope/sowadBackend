package com.marcocastope.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.marcocastope.model.Street;

@Repository
public interface StreetRepository extends CrudRepository<Street, Integer> {

}
