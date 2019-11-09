package com.formulaOne.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.formulaOne.api.services.CircuitService;
import com.formulaOne.api.services.ConstructorService;
import com.formulaOne.api.services.DriverService;

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
	
	
	@Override
	public void run(String... args) throws Exception {
		
		
		if (MODE) {
			//this.circuitService.loadCircuits();
			//this.constructorService.loadConstructors();
			//this.driverService.loadDrivers(); // 851 pilotos
		}
		
		log.info("Database loaded");
	}

}
