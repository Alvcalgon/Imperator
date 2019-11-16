package com.formulaOne.api.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.formulaOne.api.domain.Driver;

@Repository
public interface DriverRepository extends MongoRepository<Driver, String>  {
	
	@Query("{fullname: ?0}")
	Driver findByFullname(String fullname);
	
	@Query("{country: ?0}")
	List<Driver> findByCountry(String country);
	
}
