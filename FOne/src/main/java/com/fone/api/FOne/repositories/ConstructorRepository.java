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
	@Query("{nationality: {$regex: ?0, $options: 'i'}}")
	Page<Constructor> findByNationalityAPI(String nationality, Pageable pageable);
	
	@Query("{name: {$regex: ?0, $options: 'i'}}")
	Page<Constructor> findByNameAPI(String name, Pageable pageable);
	
	@Query(value = "{nationality: {$regex: ?1, $options: 'i'}, name: {$regex: ?0, $options: 'i'}}")
	Page<Constructor> findByParametersAPI(String name, String nationality, Pageable pageable);
	
	// Ambas
	@Query("{name: ?0}")
	Constructor findByName(String name);
}
