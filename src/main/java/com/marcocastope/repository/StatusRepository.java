package com.marcocastope.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.marcocastope.model.Status;

@Repository
public interface StatusRepository extends CrudRepository<Status, Integer> {

}
