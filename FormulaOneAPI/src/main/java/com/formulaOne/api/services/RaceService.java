package com.formulaOne.api.services;

import java.util.List;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formulaOne.api.domain.Race;
import com.formulaOne.api.repositories.RaceRepository;

@Service
@Transactional
public class RaceService {

	@Autowired
	private RaceRepository raceRepository;
	
	@Autowired
	private UtilityService utilityService;
	
	
	public RaceService() {
		super();
	}
	
	public Race findOne(String raceId) {
		Race result;
		
		result = this.raceRepository.findById(raceId).get();
		
		return result;
	}
	
	public List<Race> findAll() {
		List<Race> results;
		
		results = this.raceRepository.findAll();
		
		return results;
	}
	
	public Race save(Race race) {
		Race result;
		
		result = this.raceRepository.save(race);
		
		return result;
	}
	
	public void delete(Race race) {
		this.raceRepository.delete(race);
	}
	
	public void loadRace() {
		
	}
	
	protected Race getRace(Document doc) {
		Race result;
		
		result = null;
		
		return result;
	}
	
	
	public List<Race> findByCircuit(String circuitId) {
		List<Race> results;
		
		results = this.raceRepository.findByCircuit(circuitId);
		
		return results;
	}
	
}
