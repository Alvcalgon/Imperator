package com.formulaOne.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.formulaOne.api.domain.Driver;
import com.formulaOne.api.repositories.DriverRepository;
import com.formulaOne.api.services.UtilityService;

@Component
public class LoadDatabase implements CommandLineRunner {

	private static final Log log = LogFactory.getLog(LoadDatabase.class);
	
	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private UtilityService utilityService;
	
	@Override
	public void run(String... args) throws Exception {
		this.driverRepository.deleteAll();
		
		Driver driver;

		driver = new Driver();
		driver.setFullname("Nombre completo test");
		driver.setDateOfBirth(this.utilityService.getDate("Jan 7th 1985"));
		driver.setDriverId("1001");;
		
		this.driverRepository.save(driver);
	
		driver = new Driver();
		driver.setFullname("Fullname Test");
		driver.setDateOfBirth(this.utilityService.getDate("Dec 17th 1954"));
		driver.setDriverId("1002");
		
		this.driverRepository.save(driver);
	
		log.info("Database loaded");
	}

}
