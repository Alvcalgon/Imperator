package com.formulaOne.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formulaOne.api.domain.Result;
import com.formulaOne.api.repositories.ResultRepository;

@Service
@Transactional
public class ResultService {

	@Autowired
	private ResultRepository resultRepository;
	
	@Autowired
	private UtilityService utilityService;
	
	
	public ResultService() {
		super();
	}
	
	public Result findOne(String resultId) {
		Result result;
		
		result = this.resultRepository.findById(resultId).get();
		
		return result;
	}
	
	public List<Result> findAll() {
		List<Result> results;
		
		results = this.resultRepository.findAll();
		
		return results;
	}
	
	public Result save(Result result) {
		Result res;
		
		res = this.resultRepository.save(result);
		
		return res;
	}
	
	public void delete(Result result) {
		this.resultRepository.delete(result);
	}
	
	public void loadResults() {
		
	}
	
}
