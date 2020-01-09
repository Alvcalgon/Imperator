package com.fone.api.FOne.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

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

	// Test de UC-027
	@Test
	public void positiveTest_findBySeason() {
		List<DriverStanding> allSeason;
		String season;

		season = "1951";
		allSeason = this.driverStandingService.findBySeasonAPI(season);

		assertNotNull(allSeason);
		assertTrue(allSeason.size() > 0);
	}

	// Test de UC-027
	@Test
	public void negativeTest_findBySeason() {
		List<DriverStanding> allSeason;
		String season;

		season = "1949";
		allSeason = this.driverStandingService.findBySeasonAPI(season);

		assertNotNull(allSeason);
		assertTrue(allSeason.size() == 0);
	}

	// Test de UC-028
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

	// Test de UC-028
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

	// Test de UC-029
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

	// Test de UC-029
	@Test
	public void positiveTestDos_findByDriver() {
		Page<DriverStanding> driversStanding;
		String driver;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		driver = "pastor maldonado";
		driversStanding = this.driverStandingService.findByDriverAPI(driver, pageable);

		assertNotNull(driversStanding);
		assertTrue(driversStanding.hasContent());
	}

	// Test de UC-029
	@Test
	public void positiveTestTres_findByDriver() {
		Page<DriverStanding> driversStanding;
		String driver;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		driver = "alonso";
		driversStanding = this.driverStandingService.findByDriverAPI(driver, pageable);

		assertNotNull(driversStanding);
		assertTrue(driversStanding.hasContent());
	}

	// Test de UC-029
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

}
