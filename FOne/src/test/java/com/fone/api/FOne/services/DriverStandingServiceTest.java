package com.fone.api.FOne.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fone.api.FOne.domain.DriverStanding;
import com.fone.api.FOne.services.utilities.AbstractTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DriverStandingServiceTest extends AbstractTest {

	// Servicio bajo pruebas unitarias ----------
	@Autowired
	private DriverStandingService driverStandingService;

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
		List<DriverStanding> driversStanding;
		String position;

		position = "9";
		driversStanding = this.driverStandingService.findByPositionAPI(position);

		assertNotNull(driversStanding);
		assertTrue(driversStanding.size() > 0);
	}

	// Test de UC-028
	@Test
	public void negativeTest_findByPositionAndDriver() {
		List<DriverStanding> driversStanding;
		String position;

		position = "50";
		driversStanding = this.driverStandingService.findByPositionAPI(position);

		assertNotNull(driversStanding);
		assertTrue(driversStanding.size() == 0);
	}

	// Test de UC-029
	@Test
	public void positiveTestUno_findByDriver() {
		List<DriverStanding> driversStanding;
		String driver;

		driver = "Niki Lauda";
		driversStanding = this.driverStandingService.findByDriverAPI(driver);

		assertNotNull(driversStanding);
		assertTrue(driversStanding.size() > 0);
	}

	// Test de UC-029
	@Test
	public void positiveTestDos_findByDriver() {
		List<DriverStanding> driversStanding;
		String driver;

		driver = "pastor maldonado";
		driversStanding = this.driverStandingService.findByDriverAPI(driver);

		assertNotNull(driversStanding);
		assertTrue(driversStanding.size() > 0);
	}

	// Test de UC-029
	@Test
	public void positiveTestTres_findByDriver() {
		List<DriverStanding> driversStanding;
		String driver;

		driver = "alonso";
		driversStanding = this.driverStandingService.findByDriverAPI(driver);

		assertNotNull(driversStanding);
		assertTrue(driversStanding.size() > 0);
	}

	// Test de UC-029
	@Test
	public void negativeTest_findByDriver() {
		List<DriverStanding> driversStanding;
		String driver;

		driver = "Piloto desconocido";
		driversStanding = this.driverStandingService.findByDriverAPI(driver);

		assertNotNull(driversStanding);
		assertTrue(driversStanding.size() == 0);
	}

}
