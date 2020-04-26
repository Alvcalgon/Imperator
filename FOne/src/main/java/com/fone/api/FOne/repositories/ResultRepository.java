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

	@Query("{\"driver.fullname\": ?0, position: ?1}")
	Page<Result> findResultsByPositionAndDriverAPI(String driverFullname, String position, Pageable pageable);

	@Query("{\"driver.fullname\": ?0, grid: ?1}")
	Page<Result> findResultsByGridAndDriverAPI(String driverFullname, String grid, Pageable pageable);

	@Query("{\"constructor.name\": ?0, position: ?1}")
	Page<Result> findResultsByPositionAndConstructorAPI(String constructorName, String position, Pageable pageable);

	@Query("{\"constructor.name\": ?0, grid: ?1}")
	Page<Result> findResultsByGridAndConstructorAPI(String constructorName, String grid, Pageable pageable);
	
	@Query(value = "{\"driver.fullname\": ?0, position: ?1}", count = true)
	Integer findCountByPositionAndDriverAPI(String driverFullname, String position);
	
	@Query(value = "{\"driver.fullname\": ?0, grid: ?1}", count = true)
	Integer findCountByGridAndDriverAPI(String driverFullname, String grid);
	
	@Query(value = "{\"constructor.name\": ?0, position: ?1}", count = true)
	Integer findCountByPositionAndConstructorAPI(String constructorName, String position);

	@Query(value = "{\"constructor.name\": ?0, grid: ?1}", count = true)
	Integer findCountByGridAndConstructorAPI(String constructorName, String grid);

	@Query(value = "{\"driver.fullname\": ?0}", count = true)
	Integer findCountByDriverAPI(String driver);
}
