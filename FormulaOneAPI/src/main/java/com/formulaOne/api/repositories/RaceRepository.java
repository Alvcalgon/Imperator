package com.formulaOne.api.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.formulaOne.api.domain.Race;

@Repository
public interface RaceRepository extends MongoRepository<Race, String> {

	@Query("{\"circuit.circuitId\": ?0}")
	List<Race> findByCircuit(String circuitId);
	
}
