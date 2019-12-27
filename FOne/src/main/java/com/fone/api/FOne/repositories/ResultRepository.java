package com.fone.api.FOne.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fone.api.FOne.domain.Result;

@Repository
public interface ResultRepository extends MongoRepository<Result, String> {

	// Consultas que aparecen en la API -----------
	
	@Query(value = "{\"constructor.name\": ?0}")
	List<Result> findDriversByConstructorAPI(String constructorName);
}
