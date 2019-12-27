package com.fone.api.FOne.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fone.api.FOne.domain.Driver;
import com.fone.api.FOne.domain.Race;

@Repository
public interface RaceRepository extends MongoRepository<Race, String> {

	// Consultas disponibles en la API ---------------------------
	@Query(value = "{season: ?0}")
	List<Driver> findDriversBySeason(String season);
	
	@Query("{\"circuit.circuitId\": ?0}")
	List<Race> findByCircuit(String circuitId);
	
	@Query("{season: ?0}")
	List<Race> findBySeason(String season, Sort sort);	
		
	@Query("{event: ?0, season: ?1}")
	List<Race> findByEvent(String event, String season);
}
