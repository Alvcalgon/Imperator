package com.fone.api.FOne.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fone.api.FOne.domain.Circuit;

@Repository
public interface CircuitRepository extends MongoRepository<Circuit, String> {

	// Consultas usadas por el sistema internamente ------------
	@Query("{name: ?0}")
	Circuit findByName(String name);
	
	// Consultas disponibles en la API ------------------------
	@Query("{type: {$regex: ?0, $options: 'i'}}")
	Page<Circuit> findByTypeAPI(String type, Pageable pageable);
	
	@Query("{location: {$regex: ?0, $options: 'i'}}")
	Page<Circuit> findByLocationAPI(String location, Pageable pageable);
	
	@Query("{name: {$regex: ?0, $options: 'i'}}")
	List<Circuit> findByNameAPI(String name, Sort sort);
	
	// Global
	
}
