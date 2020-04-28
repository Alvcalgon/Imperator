package com.fone.api.FOne.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fone.api.FOne.domain.Circuit;

@Repository
public interface CircuitRepository extends MongoRepository<Circuit, String> {

	// Consultas usadas por el sistema internamente ------------
	
	// Consultas disponibles en la API ------------------------
	
	@Query("{$or: [{locality: {$regex: ?0, $options: 'i'}}, {country: {$regex: ?0, $options: 'i'}}]}")
	Page<Circuit> findByLocationAPI(String location, Pageable pageable);
	
	@Query("{name: {$regex: ?0, $options: 'i'}}")
	Page<Circuit> findByNameAPI(String name, Pageable pageable);
	
	@Query("{$and: [{$or: [{locality: {$regex: ?0, $options: 'i'}},{locality: {$regex: ?0, $options: 'i'}}]}, {name: {$regex: ?1, $options: 'i'}}]}")
	Page<Circuit> findByAllParametersAPI(String location, String name, Pageable pageable);
	
	// Global
	@Query("{name: ?0}")
	Circuit findByName(String name);
}
