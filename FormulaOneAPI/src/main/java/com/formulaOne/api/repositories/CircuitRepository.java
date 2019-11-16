package com.formulaOne.api.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.formulaOne.api.domain.Circuit;

@Repository
public interface CircuitRepository extends MongoRepository<Circuit, String> {

	@Query("{name: ?0}")
	Circuit findByName(String name);
	
	@Query("{type: ?0}")
	List<Circuit> findByType(String type);
	
	//TODO: el parametro location debe de estar contenido el campo Circuit::location
	@Query("")
	List<Circuit> findByLocation(String location);
	
}
