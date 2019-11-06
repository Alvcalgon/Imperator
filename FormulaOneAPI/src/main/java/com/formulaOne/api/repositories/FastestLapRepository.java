package com.formulaOne.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.formulaOne.api.domain.FastestLap;

@Repository
public interface FastestLapRepository extends MongoRepository<FastestLap, String> {

}
