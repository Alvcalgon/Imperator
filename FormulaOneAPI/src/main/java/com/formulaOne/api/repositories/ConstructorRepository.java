package com.formulaOne.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.formulaOne.api.domain.Constructor;

@Repository
public interface ConstructorRepository extends MongoRepository<Constructor, String> {

	
}
