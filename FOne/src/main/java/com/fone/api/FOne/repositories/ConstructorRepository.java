package com.fone.api.FOne.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fone.api.FOne.domain.Constructor;

@Repository
public interface ConstructorRepository extends MongoRepository<Constructor, String> {

	// Consultas usadas en el sistema internamente
	@Query("{name: ?0}")
	Constructor findByName(String name);
	
	@Query("{country: ?0}")
	List<Constructor> findByCountry(String country);
	
	// Consultas usadas por la API
	
	// Ambas
	@Query("{name: {$regex: ?0, $options: 'i'}}")
	List<Constructor> findByName2(String name);
}
