package com.fone.api.FOne;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fone.api.FOne.services.CircuitService;
import com.fone.api.FOne.services.ConstructorService;
import com.fone.api.FOne.services.DriverService;
import com.fone.api.FOne.services.FastestLapService;
import com.fone.api.FOne.services.RaceService;
import com.fone.api.FOne.services.ResultService;

@Component
public class LoadDatabase implements CommandLineRunner {

private static final Log log = LogFactory.getLog(LoadDatabase.class);
	
	private static final boolean MODE = true;
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private CircuitService circuitService;
	
	@Autowired
	private ConstructorService constructorService;
	
	@Autowired
	private RaceService raceService;
	
	@Autowired
	private FastestLapService fastestLapService;
	
	@Autowired
	private ResultService resultService;
	
	@Override
	public void run(String... args) throws Exception {
		
		if (MODE) {
			//this.circuitService.loadCircuits();
			//this.constructorService.loadConstructors();
			//this.driverService.loadDrivers(); // 851 pilotos
			
			this.raceService.loadRacesAndResults();
			
			log.info("Condicion verdadera");
		} else {
			
			log.info("condicion falsa");
		}
		
		log.info("Database loaded");
	}

}