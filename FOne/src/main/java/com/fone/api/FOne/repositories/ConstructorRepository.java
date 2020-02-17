package com.fone.api.FOne.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fone.api.FOne.domain.Constructor;

@Repository
public interface ConstructorRepository extends MongoRepository<Constructor, String> {

	// Consultas usadas en el sistema internamente
	
	
	// Consultas usadas por la API
	@Query("{country: {$regex: ?0, $options: 'i'}}")
	Page<Constructor> findByCountryAPI(String country, Pageable pageable);
	
	@Query("{name: {$regex: ?0, $options: 'i'}}")
	Page<Constructor> findByNameAPI(String name, Pageable pageable);
	
	// Ambas
	@Query("{name: ?0}")
	Constructor findByName(String name);
}
