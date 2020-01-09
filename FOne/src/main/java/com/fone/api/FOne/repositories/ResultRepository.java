package com.fone.api.FOne.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	Page<Result> findResultsByPositionAndDriverAPI(String driverFullname, String position, Pageable pageable);

	@Query("{\"driver.fullname\": {$regex: ?0, $options: 'i'}, grid: ?1}")
	Page<Result> findResultsByGridAndDriverAPI(String driverFullname, String grid, Pageable pageable);

	@Query("{\"constructor.name\": {$regex: ?0, $options: 'i'}, position: ?1}")
	Page<Result> findResultsByPositionAndConstructorAPI(String constructorName, String position, Pageable pageable);

	@Query("{\"constructor.name\": {$regex: ?0, $options: 'i'}, grid: ?1}")
	Page<Result> findResultsByGridAndConstructorAPI(String constructorName, String grid, Pageable pageable);
}
