package com.fone.api.FOne.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fone.api.FOne.domain.Driver;
import com.fone.api.FOne.services.utilities.AbstractTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DriverServiceTest extends AbstractTest {

	// Servicio bajo testeo -------------
	@Autowired
	private DriverService driverService;

	// Suite test -----------------------

	// Test de UC-001
	@Test
	public void test_findAllAPI() {
		List<Driver> drivers;

		drivers = this.driverService.findAllAPI();

		assertTrue(drivers.size() > 0);
	}

	@Test
	public void test_findOneAPI() {
		Driver driver;
		String id;

		id = "5dc696a0f6e188527b2baabf";
		driver = this.driverService.findOneAPI(id);

		assertNotNull(driver);
	}

	// Test UC-005
	@Test
	public void positiveTestUno_findByFullnameAPI() {
		List<Driver> drivers;
		String fullname;

		fullname = "Fernando Alonso";
		drivers = this.driverService.findByFullnameAPI(fullname);

		assertNotNull(drivers);
		assertTrue(drivers.size() > 0);
	}

	// Test UC-005
	@Test
	public void positiveTestDos_findByFullnameAPI() {
		List<Driver> drivers;
		String fullname;

		fullname = "Fernando";
		drivers = this.driverService.findByFullnameAPI(fullname);

		assertNotNull(drivers);
		assertTrue(drivers.size() > 0);
	}

	// Test UC-005
	@Test
	public void positiveTestTres_findByFullnameAPI() {
		List<Driver> drivers;
		String fullname;

		fullname = "Alonso";
		drivers = this.driverService.findByFullnameAPI(fullname);

		assertNotNull(drivers);
		assertTrue(drivers.size() > 0);
	}

	// Test UC-005
	@Test
	public void positiveTestCuatro_findByFullnameAPI() {
		List<Driver> drivers;
		String fullname;

		fullname = "FeRNAnDo AlOnSO";
		drivers = this.driverService.findByFullnameAPI(fullname);

		assertNotNull(drivers);
		assertTrue(drivers.size() > 0);
	}

	// Test UC-005
	@Test
	public void negativeTest_findDriverByFullnameAPI() {
		List<Driver> drivers;
		String fullname;

		fullname = "Nombre completo erroneo";
		drivers = this.driverService.findByFullnameAPI(fullname);

		assertNotNull(drivers);
		assertTrue(drivers.size() == 0);
	}

	// Test UC-002
	@Test
	public void positiveTestUno_findByCountryAPI() {
		List<Driver> drivers;
		String country;

		country = "Spain";
		drivers = this.driverService.findByCountryAPI(country);

		assertNotNull(drivers);
		assertTrue(drivers.size() > 0);
	}

	// Test UC-002
	@Test
	public void positiveTestDos_findByCountryAPI() {
		List<Driver> drivers;
		String country;

		country = "spain";
		drivers = this.driverService.findByCountryAPI(country);

		assertNotNull(drivers);
		assertTrue(drivers.size() > 0);
	}

	// Test UC-002
	@Test
	public void positiveTestTres_findByCountryAPI() {
		List<Driver> drivers;
		String country;

		country = "SpAiN";
		drivers = this.driverService.findByCountryAPI(country);

		assertNotNull(drivers);
		assertTrue(drivers.size() > 0);
	}

	// Test UC-002
	@Test
	public void negativeTest_findByCountryAPI() {
		List<Driver> drivers;
		String country;

		country = "Nombre completo erroneo";
		drivers = this.driverService.findByCountryAPI(country);

		assertNotNull(drivers);
		assertTrue(drivers.size() == 0);
	}

}
