package com.fone.api.FOne.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fone.api.FOne.domain.Race;

@Repository
public interface RaceRepository extends MongoRepository<Race, String> {

	// Consultas internas del sistema ---------------------------
	@Query(value = "{season: ?0}", fields = "{results:1}")
	List<Race> findBySeason(String season);
	
	@Query("{season: ?0}")
	List<Race> findBySeason2(String season);
	
	// Consultas disponibles en la API ---------------------------
	@Query("{\"circuit.name\": {$regex: ?0, $options: 'i'}}")
	Page<Race> findByCircuitAPI(String name, Pageable pageable);
	
	@Query("{season: ?0}")
	Page<Race> findBySeasonAPI(String season, Pageable pageable);	
	
	@Query("{\"results.driver.fullname\": ?0}")
	Page<Race> findRacesByDriverAPI(String driverFullname, Pageable pageable);
	
	@Query("{\"results.constructor.name\": ?0}")
	Page<Race> findRacesByConstructorAPI(String constructorName, Pageable pageable);
	
	@Query("{\"results.driver.fullname\": {$regex: ?0, $options: 'i'}, season: ?1}")
	Page<Race> findRacesByDriverAndSeasonAPI(String driverFullname, String season, Pageable pageable);
	
	@Query("{\"results.constructor.name\": {$regex: ?0, $options: 'i'}, season: ?1}")
	Page<Race> findRacesByConstructorAndSeasonAPI(String constructorName, String season, Pageable pageable);
	
	@Query("{season: ?1, event: {$regex: ?0, $options: 'i'}}")
	Page<Race> findRaceBySeasonAndEventAPI(String event, String season, Pageable pageable);
	
	@Query("{event: {$regex: ?0, $options: 'i'}}")
	Page<Race> findRaceByEventAPI(String event, Pageable pageable);
	
	@Query("{season: ?1, event: ?0}")
    Race findOneBySeasonAndEventAPI(String event, String season);

	@Query(value = "{\"results.constructor.name\": ?0}", count = true)
	Integer findCountByConstructorAPI(String constructor);		
	
	// Ambas ---------------------------
	
}
