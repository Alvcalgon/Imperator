package com.fone.api.FOne.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.fone.api.FOne.domain.DriverStanding;
import com.fone.api.FOne.services.utilities.AbstractTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DriverStandingServiceTest extends AbstractTest {

	// Servicio bajo pruebas unitarias ----------
	@Autowired
	private DriverStandingService driverStandingService;

	@Autowired
	private UtilityService utilityService;
	
	// Suite test -------------------------------
	
	@Test
	public void positiveTest_findBySeason() {
		Page<DriverStanding> allSeason;
		String season;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		
		season = "1951";
		allSeason = this.driverStandingService.findBySeasonAPI(season, pageable);

		assertNotNull(allSeason);
		assertTrue(allSeason.hasContent());
	}

	@Test
	public void negativeTest_findBySeason() {
		Page<DriverStanding> allSeason;
		String season;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		season = "1949";
		allSeason = this.driverStandingService.findBySeasonAPI(season, pageable);

		assertNotNull(allSeason);
		assertTrue(!allSeason.hasContent());
	}

	@Test
	public void positiveTest_findByPositionAndDriver() {
		Page<DriverStanding> driversStanding;
		String position;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		position = "9";
		driversStanding = this.driverStandingService.findByPositionAPI(position, pageable);

		assertNotNull(driversStanding);
		assertTrue(driversStanding.hasContent());
	}

	@Test
	public void negativeTest_findByPositionAndDriver() {
		Page<DriverStanding> driversStanding;
		String position;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		position = "50";
		driversStanding = this.driverStandingService.findByPositionAPI(position, pageable);

		assertNotNull(driversStanding);
		assertTrue(!driversStanding.hasContent());
	}

	@Test
	public void positiveTestUno_findByDriver() {
		Page<DriverStanding> driversStanding;
		String driver;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		driver = "Niki Lauda";
		driversStanding = this.driverStandingService.findByDriverAPI(driver, pageable);

		assertNotNull(driversStanding);
		assertTrue(driversStanding.hasContent());
	}

	@Test
	public void positiveTestDos_findByDriver() {
		Page<DriverStanding> driversStanding;
		String driver;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		driver = "Pastor Maldonado";
		driversStanding = this.driverStandingService.findByDriverAPI(driver, pageable);

		assertNotNull(driversStanding);
		assertTrue(driversStanding.hasContent());
	}

	@Test
	public void positiveTestTres_findByDriver() {
		Page<DriverStanding> driversStanding;
		String driver;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		driver = "Fernando Alonso";
		driversStanding = this.driverStandingService.findByDriverAPI(driver, pageable);

		assertNotNull(driversStanding);
		assertTrue(driversStanding.hasContent());
	}

	@Test
	public void negativeTest_findByDriver() {
		Page<DriverStanding> driversStanding;
		String driver;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		driver = "Piloto desconocido";
		driversStanding = this.driverStandingService.findByDriverAPI(driver, pageable);

		assertNotNull(driversStanding);
		assertTrue(!driversStanding.hasContent());
	}

	@Test
	public void positiveTest_findCountDriverAndPositionAPI() {
		Integer count;
		String driver;
		String position;
		
		driver = "Fernando Alonso";
		position = "1";
		
		count = this.driverStandingService.findCountDriverAndPositionAPI(driver, position);
		
		assertTrue(count == 2);
	}
	
	
	@Test
	public void negativeTest_findCountDriverAndPositionAPI() {
		Integer count;
		String driver;
		String position;
		
		driver = "Fernando Alonso";
		position = "30";
		
		count = this.driverStandingService.findCountDriverAndPositionAPI(driver, position);
		
		assertTrue(count == 0);
	}
	
	@Test
	public void positiveTest_findCountDriverAPI() {
		Integer count;
		String driver;
		
		driver = "Fernando Alonso";
		
		count = this.driverStandingService.findCountByDriverAPI(driver);
		
		assertTrue(count > 0);
	}
	
	
	@Test
	public void negativeTest_findCountDriverAPI() {
		Integer count;
		String driver;
		
		driver = "Leo Messi";
		
		count = this.driverStandingService.findCountByDriverAPI(driver);
		
		assertTrue(count == 0);
	}
	
	@Test
	public void positiveTest_findDriversTitlesByConstructorAPI() {
		Integer count;
		String constructor;
		
		constructor = "Ferrari";
		count = this.driverStandingService.findDriversTitlesByConstructorAPI(constructor);
		
		assertTrue(count > 0);
	}
	
}
