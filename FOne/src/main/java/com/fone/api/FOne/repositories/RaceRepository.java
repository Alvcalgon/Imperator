package com.fone.api.FOne.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fone.api.FOne.domain.Race;

@Repository
public interface RaceRepository extends MongoRepository<Race, String> {

	// Consultas disponibles en la API ---------------------------
	@Query("{\"circuit.name\": {$regex: ?0, $options: 'i'}}")
	List<Race> findByCircuitAPI(String name, Sort sort);
	
	@Query("{season: ?0}")
	List<Race> findBySeasonAPI(String season, Sort sort);	
	
	@Query("{\"results.driver.fullname\": ?0}")
	List<Race> findRacesByDriverAPI(String driverFullname, Sort sort);
	
	@Query("{\"results.constructor.name\": ?0}")
	List<Race> findRacesByConstructorAPI(String constructorName, Sort sort);
	
	@Query("{\"results.driver.fullname\": {$regex: ?0, $options: 'i'}, season: ?1}")
	List<Race> findRacesByDriverAndSeasonAPI(String driverFullname, String season, Sort sort);
	
	@Query("{\"results.constructor.name\": {$regex: ?0, $options: 'i'}, season: ?1}")
	List<Race> findRacesByConstructorAndSeasonAPI(String constructorName, String season, Sort sort);
	
	@Query("{season: ?1, event: {$regex: ?0, $options: 'i'}}")
	List<Race> findRaceBySeasonAndEventAPI(String event, String season, Sort sort);
}
