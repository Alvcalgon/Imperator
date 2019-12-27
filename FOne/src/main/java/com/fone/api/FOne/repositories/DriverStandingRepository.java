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
	List<DriverStanding> findBySeason(String season, Sort sort);
}
