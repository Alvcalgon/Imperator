package com.formulaOne.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.formulaOne.api.domain.Result;

@Repository
public interface ResultRepository extends MongoRepository<Result, String> {

}
