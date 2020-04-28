package com.fone.api.FOne;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fone.api.FOne.services.CircuitService;
import com.fone.api.FOne.services.ConstructorService;
import com.fone.api.FOne.services.ConstructorStandingService;
import com.fone.api.FOne.services.DriverService;
import com.fone.api.FOne.services.DriverStandingService;
import com.fone.api.FOne.services.RaceService;

@Component
public class LoadDatabase implements CommandLineRunner {

	private static final Log log = LogFactory.getLog(LoadDatabase.class);

	private static final boolean MODE = Boolean.FALSE;
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private ConstructorService constructorService;
	
	@Autowired
	private CircuitService circuitService;
	
	@Autowired
	private RaceService raceService;
	
	@Autowired
	private DriverStandingService driverStandingService;
	
	@Autowired
	private ConstructorStandingService constructorStandingService;
	
	@Override
	public void run(String... args) throws Exception {

		if (MODE) {
			this.driverService.loadDrivers();
			this.constructorService.loadConstructors();
			this.circuitService.loadCircuits();
			this.raceService.loadRacesAndResults();
			this.driverStandingService.loadDriverStandings();
			this.constructorStandingService.loadConstructorStandings();
			
			log.info("BD poblada exitosamente");
		} else {
			log.info("BD poblada previamente");
		}


	}

}