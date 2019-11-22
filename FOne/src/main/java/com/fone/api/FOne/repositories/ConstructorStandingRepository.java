package com.fone.api.FOne.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fone.api.FOne.domain.ConstructorStanding;

@Repository
public interface ConstructorStandingRepository extends MongoRepository<ConstructorStanding, String> {

}