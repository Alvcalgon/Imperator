package com.formulaOne.api.services;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.formulaOne.api.domain.Driver;
import com.formulaOne.api.services.utilities.AbstractTest;

@SpringBootTest
public class DriverServiceTest extends AbstractTest  {
	
	// Services under testing -------------
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private UtilityService utilityService;
	
	// Suite test -----------------------
	
	
//	@Test
//	@DisplayName("Acceder a los datos de un piloto: Adrian Sutil")
//	public void test_getDriver() {
//		Driver driver;
//		Document doc;
//		
//		doc = this.utilityService.getDocument("https://www.f1-fansite.com/f1-drivers/adrian-sutil/"); 
//			
//		driver = this.driverService.getDriver(doc);
//				
//		assertNotNull(driver);		
//	}
	
	@Test
	@DisplayName("Lista completa de pilotos")
	public void test_list() {
		List<Driver> drivers;
		
		drivers = this.driverService.findAll();
		
		assertTrue(drivers.size() > 0);
	}
	
	@Test
	@DisplayName("Recuperar piloto dado su id")
	public void test_findById() {
		Driver driver;
		String id;
		
		id = "5dc696a0f6e188527b2baabf";
		driver = this.driverService.findOne(id);
		
		assertNotNull(driver);
	}
	
	@Test
	@DisplayName("Recuperar con exito un piloto dado su nombre")
	public void positiveTest_findDriverByName() {
		Driver driver;
		String fullname;
		
		fullname = "Fernando Alonso";
		driver = this.driverService.findByFullname(fullname);
		
		assertNotNull(driver);
	}
	
	@Test
	@DisplayName("Recuperar erroneamente un piloto dado su nombre")
	public void negativeTest_findDriverByName() {
		Driver driver;
		String fullname;
		
		fullname = "Nombre completo erroneo";
		driver = this.driverService.findByFullname(fullname);
		
		assertTrue(driver == null);
	}
	
	
	@Test
	@DisplayName("Recuperar con exito los pilotos de un pais")
	public void positiveTest_findDriverByCountry() {
		List<Driver> drivers;
		String country;
		
		country = "Spain";
		drivers = this.driverService.findByCountry(country);
		
		assertNotNull(drivers);
		assertTrue(drivers.size() > 0);
	}
	
	@Test
	@DisplayName("Recuperar erroneamente los pilotos de un pais")
	public void negativeTest_findDriverByCountry() {
		List<Driver> drivers;
		String country;
		
		country = "Nombre completo erroneo";
		drivers = this.driverService.findByCountry(country);
		
		assertNotNull(drivers);
		assertTrue(drivers.size() == 0);
	}
	
}
