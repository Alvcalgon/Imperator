package com.formulaOne.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.formulaOne.api.domain.Circuit;

@Repository
public interface CircuitRepository extends MongoRepository<Circuit, String> {

}
