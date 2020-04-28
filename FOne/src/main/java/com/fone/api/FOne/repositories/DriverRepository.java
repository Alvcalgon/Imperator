package com.fone.api.FOne.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fone.api.FOne.domain.Driver;

@Repository
public interface DriverRepository extends MongoRepository<Driver, String>  {
	
	// Consultas usadas en la aplicación internamente ------------
	
	// Consultas disponibles en la API -----------------------------
	@Query("{fullname: {$regex: ?0, $options: 'i'}}")
	Page<Driver> findByFullnameAPI(String fullname, Pageable pageable);
	
	@Query(value = "{nacionality: {$regex: ?0, $options: 'i'}}")
	Page<Driver> findByNacionalityAPI(String nacionality, Pageable pageable);

	@Query(value = "{nacionality: {$regex: ?1, $options: 'i'}, fullname: {$regex: ?0, $options: 'i'}}")
	Page<Driver> findByParametersAPI(String fullname, String nacionality, Pageable pageable);
	
	// Ambas -------------------------------------------------------
	@Query("{fullname: ?0}")
	Driver findByFullname(String fullname);
	
}
