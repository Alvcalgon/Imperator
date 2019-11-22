package com.fone.api.FOne.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fone.api.FOne.domain.Race;

@Repository
public interface RaceRepository extends MongoRepository<Race, String> {

	@Query("{\"circuit.circuitId\": ?0}")
	List<Race> findByCircuit(String circuitId);
	
	@Query("{season: ?0}")
	List<Race> findBySeason(String season);	
}
