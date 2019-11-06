package com.formulaOne.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.formulaOne.api.domain.Driver;
import com.formulaOne.api.repositories.DriverRepository;
import com.formulaOne.api.services.DriverService;
import com.formulaOne.api.services.UtilityService;

@Component
public class LoadDatabase implements CommandLineRunner {

	private static final Log log = LogFactory.getLog(LoadDatabase.class);
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private UtilityService utilityService;
	
	@Override
	public void run(String... args) throws Exception {
		this.driverService.deleteAll();
		
		List<Driver> ls_drivers = new ArrayList<Driver>();
		Driver driver_uno, driver_dos;

		driver_uno = new Driver("Nombre completo test - ls",
							"Los palacios,Sevilla",
							"Spain",
							this.utilityService.getDate("Jan 7th 1985"));
		
		driver_dos = new Driver("Fullname Test",
							"Sidney",
							"Australia",
							this.utilityService.getDate("Dec 17th 1954"));
		
		ls_drivers.add(driver_uno);
		ls_drivers.add(driver_dos);
		
		this.driverRepository.saveAll(ls_drivers);
	
		log.info("Database loaded");
	}

}
