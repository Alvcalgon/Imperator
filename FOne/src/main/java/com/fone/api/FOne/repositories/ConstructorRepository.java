package com.fone.api.FOne.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fone.api.FOne.domain.Constructor;

@Repository
public interface ConstructorRepository extends MongoRepository<Constructor, String> {

	// Consultas usadas en el sistema internamente
	@Query("{name: ?0}")
	Constructor findByName(String name);
	
	// Consultas usadas por la API
	@Query("{country: {$regex: ?0, $options: 'i'}}")
	List<Constructor> findByCountryAPI(String country, Sort sort);
	
	@Query("{name: {$regex: ?0, $options: 'i'}}")
	List<Constructor> findByNameAPI(String name, Sort sort);
	
	// Ambas
}
