package com.formulaOne.api.services;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.formulaOne.api.domain.Driver;
import com.formulaOne.api.services.DriverService;
import com.formulaOne.api.services.UtilityService;
import com.formulaOne.api.services.utilities.AbstractTest;

@SpringBootTest
public class DriverServiceTest extends AbstractTest  {
	
	// Services under testing -------------
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private UtilityService utilityService;
	
	// Suite test -----------------------
	
	
	@Test
	@DisplayName("Acceder a los datos de un piloto: Adrian Sutil")
	public void test_getDriver() {
		Driver driver;
		Document doc;
		
		doc = this.utilityService.getDocument("https://www.f1-fansite.com/f1-drivers/adrian-sutil/"); 
			
		driver = this.driverService.getDriver(doc);
				
		assertNotNull(driver);		
	}
	
	@Test
	@DisplayName("Lista de conductores")
	public void test_list() {
		List<Driver> drivers;
		
		drivers = this.driverService.findAll();
		
		assertTrue(drivers.size() >= 2);
	}
	
	
//	@Test
//	@DisplayName("Los pilotos se insertan correctamente")
//	public void test_loadDriver() {
//		this.driverService.loadDriver();
//		
//		String hola = "";
//		
//		assertTrue(hola == "");
//	}
	
}
