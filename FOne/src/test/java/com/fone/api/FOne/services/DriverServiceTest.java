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

	// Services under testing -------------
	@Autowired
	private DriverService driverService;

	@Autowired
	private UtilityService utilityService;

	// Suite test -----------------------

//	@Test
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
	public void test_list() {
		List<Driver> drivers;

		drivers = this.driverService.findAll();

		assertTrue(drivers.size() > 0);
	}

	@Test
	public void test_findById() {
		Driver driver;
		String id;

		id = "5dc696a0f6e188527b2baabf";
		driver = this.driverService.findOne(id);

		assertNotNull(driver);
	}

	@Test
	public void positiveTest_findDriverByName() {
		Driver driver;
		String fullname;

		fullname = "Fernando Alonso";
		driver = this.driverService.findByFullname(fullname);

		assertNotNull(driver);
	}

	@Test
	public void negativeTest_findDriverByName() {
		Driver driver;
		String fullname;

		fullname = "Nombre completo erroneo";
		driver = this.driverService.findByFullname(fullname);

		assertTrue(driver == null);
	}

	@Test
	public void positiveTest_findDriverByCountry() {
		List<Driver> drivers;
		String country;

		country = "Spain";
		drivers = this.driverService.findByCountry(country);

		assertNotNull(drivers);
		assertTrue(drivers.size() > 0);
	}

	@Test
	public void negativeTest_findDriverByCountry() {
		List<Driver> drivers;
		String country;

		country = "Nombre completo erroneo";
		drivers = this.driverService.findByCountry(country);

		assertNotNull(drivers);
		assertTrue(drivers.size() == 0);
	}

}
