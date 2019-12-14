package com.fone.api.FOne.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fone.api.FOne.domain.Driver;

@Repository
public interface DriverRepository extends MongoRepository<Driver, String>  {
	
	// Consultadas usadas en la aplicación internamente
	@Query("{fullname: ?0}")
	Driver findByFullname(String fullname);
	
	// Consultas disponibles en la API
	@Query("{fullname: {$regex: ?0, $options: 'i'}}")
	List<Driver> findByFullnameAPI(String fullname);
	
	@Query("{country: ?0}")
	List<Driver> findByCountry(String country);

	// Ambas
	@Query("{fullname: {$regex: ?0, $options: 'i'}}")
	Driver findByFullname2(String fullname);
}
