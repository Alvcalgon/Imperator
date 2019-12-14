package com.fone.api.FOne;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fone.api.FOne.services.DriverStandingService;

@Component
public class LoadDatabase implements CommandLineRunner {

	private static final Log log = LogFactory.getLog(LoadDatabase.class);

	private static final boolean MODE = true;

//	@Autowired
//	private DriverService driverService;
//
//	@Autowired
//	private CircuitService circuitService;
//
//	@Autowired
//	private ConstructorService constructorService;
//
//	@Autowired
//	private RaceService raceService;
//	@Autowired
//	private ResultService resultService;

	@Autowired
	private DriverStandingService driverStandingService;
	
	@Override
	public void run(String... args) throws Exception {

		if (MODE) {
			this.driverStandingService.loadDriverStandings();
			
			log.info("Condicion verdadera");
		} else {
			this.driverStandingService.deleteAll();
			
			log.info("condicion falsa");
		}

		log.info("Database loaded");
	}

}