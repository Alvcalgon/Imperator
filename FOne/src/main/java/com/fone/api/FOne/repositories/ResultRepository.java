package com.fone.api.FOne.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fone.api.FOne.domain.Result;

@Repository
public interface ResultRepository extends MongoRepository<Result, String> {

	// Consultas que aparecen en la API -----------
	@Query("{\"constructor.name\": ?0}")
	List<Result> findDriversByConstructorAPI(String constructorName);

	@Query("{\"driver.fullname\": ?0}")
	List<Result> findConstructorsByDriverAPI(String driverFullname);

	@Query("{\"driver.fullname\": {$regex: ?0, $options: 'i'}, position: ?1}")
	List<Result> findResultsByPositionAndDriverAPI(String driverFullname, String position);

	@Query("{\"driver.fullname\": {$regex: ?0, $options: 'i'}, grid: ?1}")
	List<Result> findResultsByGridAndDriverAPI(String driverFullname, String grid);

	@Query("{\"constructor.name\": {$regex: ?0, $options: 'i'}, position: ?1}")
	List<Result> findResultsByPositionAndConstructorAPI(String constructorName, String position);

	@Query("{\"constructor.name\": {$regex: ?0, $options: 'i'}, grid: ?1}")
	List<Result> findResultsByGridAndConstructorAPI(String constructorName, String grid);
}
