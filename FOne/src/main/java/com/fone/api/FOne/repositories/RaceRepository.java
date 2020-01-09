package com.fone.api.FOne.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fone.api.FOne.domain.Race;

@Repository
public interface RaceRepository extends MongoRepository<Race, String> {

	// Consultas disponibles en la API ---------------------------
	@Query("{\"circuit.name\": {$regex: ?0, $options: 'i'}}")
	Page<Race> findByCircuitAPI(String name, Pageable pageable);
	
	@Query("{season: ?0}")
	List<Race> findBySeasonAPI(String season, Sort sort);	
	
	@Query("{\"results.driver.fullname\": ?0}")
	Page<Race> findRacesByDriverAPI(String driverFullname, Pageable pageable);
	
	@Query("{\"results.constructor.name\": ?0}")
	Page<Race> findRacesByConstructorAPI(String constructorName, Pageable pageable);
	
	@Query("{\"results.driver.fullname\": {$regex: ?0, $options: 'i'}, season: ?1}")
	Page<Race> findRacesByDriverAndSeasonAPI(String driverFullname, String season, Pageable pageable);
	
	@Query("{\"results.constructor.name\": {$regex: ?0, $options: 'i'}, season: ?1}")
	Page<Race> findRacesByConstructorAndSeasonAPI(String constructorName, String season, Pageable pageable);
	
	@Query("{season: ?1, event: {$regex: ?0, $options: 'i'}}")
	List<Race> findRaceBySeasonAndEventAPI(String event, String season, Sort sort);
}
