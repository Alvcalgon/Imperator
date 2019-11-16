package com.formulaOne.api.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.formulaOne.api.domain.Constructor;

@Repository
public interface ConstructorRepository extends MongoRepository<Constructor, String> {

	@Query("{name: ?0}")
	Constructor findByName(String name);
	
	@Query("{country: ?0}")
	List<Constructor> findByCountry(String country);
	
}
