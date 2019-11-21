package com.fone.api.FOne.services;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fone.api.FOne.domain.DriverStanding;
import com.fone.api.FOne.repositories.DriverStandingRepository;

@Service
@Transactional
public class DriverStandingService {

	@Autowired
	private DriverStandingRepository driverStandingRepository;
	
	@Autowired
	private UtilityService utilityService;
	
	
	public DriverStandingService() {
		super();
	}
	
	
	public void loadDriverStandings() {
		
	}
	
	protected DriverStanding getDriverStanding(Document document) {
		DriverStanding result;
		
		return null;
	}
	
}
