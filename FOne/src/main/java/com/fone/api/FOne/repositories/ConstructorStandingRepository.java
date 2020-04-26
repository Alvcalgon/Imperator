package com.fone.api.FOne.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fone.api.FOne.domain.ConstructorStanding;

@Repository
public interface ConstructorStandingRepository extends MongoRepository<ConstructorStanding, String> {

	@Query("{season: ?0}")
	Page<ConstructorStanding> findBySeasonAPI(String season, Pageable pageable);
	
	@Query("{position: ?0}")
	Page<ConstructorStanding> findByPositionAPI(String position, Pageable pageable);
	
	@Query("{\"constructor.name\": ?0}")
	Page<ConstructorStanding> findByConstructorAPI(String constructor, Pageable pageable);
	
	@Query(value = "{\"constructor.name\": ?0}", count = true)
	Integer findCountByConstructorAPI(String constructor);
	
	@Query(value = "{\"constructor.name\": ?0, position: ?1}", count = true)
	Integer findCountByConstructorAndPositionAPI(String constructor, String position);
}
