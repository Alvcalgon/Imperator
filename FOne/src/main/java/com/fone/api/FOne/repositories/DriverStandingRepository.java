package com.fone.api.FOne.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	Page<DriverStanding> findByPositionAPI(String position, Pageable pageable);
	
	@Query("{\"driver.fullname\": {$regex: ?0, $options: 'i'}}")
	Page<DriverStanding> findByDriverAPI(String driver, Pageable pageable);
}
