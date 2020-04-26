package com.fone.api.FOne.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fone.api.FOne.domain.DriverStanding;

@Repository
public interface DriverStandingRepository extends MongoRepository<DriverStanding, String> {

	@Query("{season: ?0}")
	Page<DriverStanding> findBySeasonAPI(String season, Pageable pageable);
	
	@Query("{position: ?0}")
	Page<DriverStanding> findByPositionAPI(String position, Pageable pageable);
	
	@Query("{\"driver.fullname\": ?0}")
	Page<DriverStanding> findByDriverAPI(String driver, Pageable pageable);

	@Query(value = "{\"driver.fullname\": ?0}", count = true)
	Integer findCountByDriverAPI(String driver);
	
	@Query(value = "{\"driver.fullname\": ?0, position: ?1}", count = true)
	Integer findCountDriverAndPositionAPI(String driver, String position);
	
	@Query(value = "{\"constructor.name\": ?0, position: '1'}", count = true)
	Integer findDriversTitlesByConstructorAPI(String constructor);
}
