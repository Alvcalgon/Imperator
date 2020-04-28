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

import com.fone.api.FOne.domain.Driver;
import com.fone.api.FOne.services.utilities.AbstractTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DriverServiceTest extends AbstractTest {

	// Servicio bajo testeo -------------
	@Autowired
	private DriverService driverService;
	
	// Servicio de apoyo
	@Autowired
	private UtilityService utilityService;
	
	// Suite test -----------------------
	@Test
	public void test_findAllAPI() {
		Page<Driver> drivers;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		drivers = this.driverService.findAllAPI(pageable);

		assertTrue(drivers.hasContent());
	}

	@Test
	public void positiveTestUno_findByFullnameAPI() {
		Page<Driver> drivers;
		String fullname;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		
		fullname = "Fernando Alonso";
		drivers = this.driverService.findByFullnameAPI(fullname, pageable);

		assertNotNull(drivers);
		assertTrue(drivers.hasContent());
	}
	@Test
	public void positiveTestDos_findByFullnameAPI() {
		Page<Driver> drivers;
		String fullname;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		
		fullname = "Fernando";
		drivers = this.driverService.findByFullnameAPI(fullname, pageable);

		assertNotNull(drivers);
		assertTrue(drivers.hasContent());
	}

	
	@Test
	public void positiveTestTres_findByFullnameAPI() {
		Page<Driver> drivers;
		String fullname;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		fullname = "Alonso";
		drivers = this.driverService.findByFullnameAPI(fullname, pageable);

		assertNotNull(drivers);
		assertTrue(drivers.hasContent());
	}

	
	@Test
	public void positiveTestCuatro_findByFullnameAPI() {
		Page<Driver> drivers;
		String fullname;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		fullname = "FeRNAnDo AlOnSO";
		drivers = this.driverService.findByFullnameAPI(fullname, pageable);

		assertNotNull(drivers);
		assertTrue(drivers.hasContent());
	}

	
	@Test
	public void negativeTest_findDriverByFullnameAPI() {
		Page<Driver> drivers;
		String fullname;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		fullname = "Nombre completo erroneo";
		drivers = this.driverService.findByFullnameAPI(fullname, pageable);

		assertNotNull(drivers);
		assertTrue(!drivers.hasContent());
	}

	@Test
	public void positiveTest_findByFullname2API() {
		Driver driver;
		String fullname;
		
		fullname = "Fernando Alonso";
		driver = this.driverService.findByFullname2(fullname);
		
		assertNotNull(driver);
		assertTrue(driver.getFullname().equals(fullname));
	}
	
	@Test
	public void negativeTest_findByFullname2API() {
		Driver driver;
		String fullname;
		
		fullname = "nombre desconocido";
		driver = this.driverService.findByFullname2(fullname);
		
		assertTrue(driver == null);
	}
	
	@Test
	public void positiveTestUno_findByNacionalityAPI() {
		Page<Driver> drivers;
		String country;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		country = "Spanish";
		drivers = this.driverService.findByNacionalityAPI(country, pageable);

		assertNotNull(drivers);
		assertTrue(drivers.hasContent());
	}

	
	@Test
	public void positiveTestDos_findByNacionalityAPI() {
		Page<Driver> drivers;
		String country;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		country = "Brazilian";
		drivers = this.driverService.findByNacionalityAPI(country, pageable);

		assertNotNull(drivers);
		assertTrue(drivers.hasContent());
	}

	
	@Test
	public void positiveTestTres_findByNacionalityAPI() {
		Page<Driver> drivers;
		String country;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		country = "German";
		drivers = this.driverService.findByNacionalityAPI(country, pageable);

		assertNotNull(drivers);
		assertTrue(drivers.hasContent());
	}

	
	@Test
	public void negativeTest_findByNacionalityAPI() {
		Page<Driver> drivers;
		String country;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		country = "Nacionalidad erroneo";
		drivers = this.driverService.findByNacionalityAPI(country, pageable);

		assertNotNull(drivers);
		assertTrue(!drivers.hasContent());
	}

	@Test
	public void positiveTest_findByParametersAPI() {
		Page<Driver> drivers;
		String fullname;
		String country;
		Pageable pageable;
		
		fullname = "alonso";
		country = "spanish";
		pageable = this.utilityService.getPageable(10, 0);
		
		drivers = this.driverService.findByParametersAPI(fullname, country, pageable);
		
		assertNotNull(drivers);
		assertTrue(drivers.hasContent());
	}
	
	@Test
	public void negativeTest_findByParametersAPI() {
		Page<Driver> drivers;
		String fullname;
		String country;
		Pageable pageable;
		
		fullname = "alonso";
		country = "france";
		pageable = this.utilityService.getPageable(10, 0);
		
		drivers = this.driverService.findByParametersAPI(fullname, country, pageable);
		
		assertNotNull(drivers);
		assertTrue(!drivers.hasContent());
	}
	
}
