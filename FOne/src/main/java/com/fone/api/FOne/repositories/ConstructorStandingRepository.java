package com.fone.api.FOne.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fone.api.FOne.domain.ConstructorStanding;

@Repository
public interface ConstructorStandingRepository extends MongoRepository<ConstructorStanding, String> {

	@Query("{season: ?0}")
	List<ConstructorStanding> findBySeasonAPI(String season, Sort sort);
	
	@Query("{position: ?0}")
	Page<ConstructorStanding> findByPositionAPI(String position, Pageable pageable);
	
	@Query("{\"constructor.name\": {$regex: ?0, $options: 'i'}}")
	Page<ConstructorStanding> findByConstructorAPI(String constructor, Pageable pageable);
}
