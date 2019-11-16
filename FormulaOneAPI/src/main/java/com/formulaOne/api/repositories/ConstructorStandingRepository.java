package com.formulaOne.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.formulaOne.api.domain.ConstructorStanding;

@Repository
public interface ConstructorStandingRepository extends MongoRepository<ConstructorStanding, String> {

}
