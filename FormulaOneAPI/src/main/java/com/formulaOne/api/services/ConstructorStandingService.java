package com.formulaOne.api.services;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formulaOne.api.domain.ConstructorStanding;
import com.formulaOne.api.repositories.ConstructorStandingRepository;

@Service
@Transactional
public class ConstructorStandingService {

	@Autowired
	private ConstructorStandingRepository constructorStandingRepository;
	
	@Autowired
	private UtilityService utilityService;
	
	
	public ConstructorStandingService() {
		super();
	}
	
	public void loadConstructorStandings() {
		
	}
	
	protected ConstructorStanding getConstructorStanding(Document doc) {
		ConstructorStanding result;
		
		return null;
		
		
	}
	
}
