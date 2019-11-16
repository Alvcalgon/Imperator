package com.formulaOne.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.formulaOne.api.domain.DriverStanding;

@Repository
public interface DriverStandingRepository extends MongoRepository<DriverStanding, String> {

}
