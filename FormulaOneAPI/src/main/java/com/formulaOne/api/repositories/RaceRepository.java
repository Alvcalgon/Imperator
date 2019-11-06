package com.formulaOne.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.formulaOne.api.domain.Race;

@Repository
public interface RaceRepository extends MongoRepository<Race, String> {

}
