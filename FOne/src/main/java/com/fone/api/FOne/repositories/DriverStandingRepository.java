package com.fone.api.FOne.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fone.api.FOne.domain.DriverStanding;

@Repository
public interface DriverStandingRepository extends MongoRepository<DriverStanding, String> {

	@Query("{season: ?0}")
	List<DriverStanding> findBySeasonAPI(String season, Sort sort);
	
	@Query("{position: ?0}")
	List<DriverStanding> findByPositionAPI(String position, Sort sort);
	
	@Query("{\"driver.fullname\": {$regex: ?0, $options: 'i'}}")
	List<DriverStanding> findByDriverAPI(String driver, Sort sort);
}
