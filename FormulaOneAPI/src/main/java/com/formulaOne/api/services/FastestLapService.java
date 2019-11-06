package com.formulaOne.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formulaOne.api.domain.FastestLap;
import com.formulaOne.api.repositories.FastestLapRepository;

@Service
@Transactional
public class FastestLapService {

	@Autowired
	private FastestLapRepository fastestLapRepository;
	
	@Autowired
	private UtilityService utilityService;
	
	
	public FastestLapService() {
		super();
	}
	
	
	public FastestLap findOne(String fastestLapId) {
		FastestLap result;
		
		result = this.fastestLapRepository.findById(fastestLapId).get();
		
		return result;
	}
	
	public List<FastestLap> findAll() {
		List<FastestLap> results;
		
		results = this.fastestLapRepository.findAll();
		
		return results;
	}
	
	public FastestLap save(FastestLap fastestLap) {
		FastestLap result;
		
		result = this.fastestLapRepository.save(fastestLap);
		
		return result;
	}
	
	public void delete(FastestLap fastestLap) {
		this.fastestLapRepository.delete(fastestLap);
	}

	public void loadFastestLaps() {
		
	}
	
	
}
