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

import com.fone.api.FOne.domain.Constructor;
import com.fone.api.FOne.services.utilities.AbstractTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConstructorServiceTest extends AbstractTest {

	// Service under testing --------
	@Autowired
	private ConstructorService constructorService;

	@Autowired
	private UtilityService utilityService;
	
	// Suite test -------------------

	@Test
	public void test_findAll() {
		Page<Constructor> all;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		all = this.constructorService.findAllAPI(pageable);

		assertNotNull(all);
		assertTrue(all.hasContent());
	}

	
	@Test
	public void positiveTestUno_findByCountry() {
		Page<Constructor> constructors;
		String country;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		country = "Italy";
		constructors = this.constructorService.findByCountryAPI(country, pageable);

		assertNotNull(constructors);
		assertTrue(constructors.hasContent());
	}

	
	@Test
	public void positiveTestDos_findByCountry() {
		Page<Constructor> constructors;
		String country;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		country = "ITALY";
		constructors = this.constructorService.findByCountryAPI(country, pageable);

		assertNotNull(constructors);
		assertTrue(constructors.hasContent());
	}

	
	@Test
	public void positiveTestTres_findByCountry() {
		Page<Constructor> constructors;
		String country;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		country = "ItAlY";
		constructors = this.constructorService.findByCountryAPI(country, pageable);

		assertNotNull(constructors);
		assertTrue(constructors.hasContent());
	}

	@Test
	public void negativeTest_findByCountry() {
		Page<Constructor> constructors;
		String country;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		country = "Pais erroneo";
		constructors = this.constructorService.findByCountryAPI(country, pageable);

		assertNotNull(constructors);
		assertNotNull(!constructors.hasContent());
	}

	
	@Test
	public void positiveTestUno_findByName() {
		Page<Constructor> constructors;
		String name;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		name = "Ferrari";
		constructors = this.constructorService.findByNameAPI(name, pageable);

		assertNotNull(constructors);
		assertTrue(constructors.hasContent());
	}

	
	@Test
	public void positiveTestDos_findByName() {
		Page<Constructor> constructors;
		String name;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		name = "ferrari";
		constructors = this.constructorService.findByNameAPI(name, pageable);

		assertNotNull(constructors);
		assertTrue(constructors.hasContent());
	}

	
	@Test
	public void positiveTestTres_findByName() {
		Page<Constructor> constructors;
		String name;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		name = "feRrArI";
		constructors = this.constructorService.findByNameAPI(name, pageable);

		assertNotNull(constructors);
		assertTrue(constructors.hasContent());
	}

	
	@Test
	public void negativeTest_findByName() {
		Page<Constructor> constructors;
		String name;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		name = "Escuderia erroneo";
		constructors = this.constructorService.findByNameAPI(name, pageable);

		assertNotNull(constructors);
		assertTrue(!constructors.hasContent());
	}

	@Test
	public void positiveTest_findByNameAPI2() {
		Constructor constructor;
		String name;
		
		name = "Ferrari";
		constructor = this.constructorService.findByNameAPI2(name);
		
		assertNotNull(constructor);
		assertTrue(constructor.getName().equals(name));
	}
	
	@Test
	public void negativeTest_findByNameAPI2() {
		Constructor constructor;
		String name;
		
		name = "escuderia ferrari";
		constructor = this.constructorService.findByNameAPI2(name);
		
		assertTrue(constructor == null);
	}
	
	@Test
	public void positiveTest_findByParametersAPI() {
		Page<Constructor> constructors;
		String name;
		String country;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		name = "Ferrari";
		country = "Italy";
		
		constructors = this.constructorService.findByParametersAPI(name, country, pageable);
		
		assertNotNull(constructors);
		assertTrue(constructors.hasContent());
	}
	
	
	@Test
	public void negativeTest_findByParametersAPI() {
		Page<Constructor> constructors;
		String name;
		String country;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		name = "Ferrari";
		country = "Spain";
		
		constructors = this.constructorService.findByParametersAPI(name, country, pageable);
		
		assertNotNull(constructors);
		assertTrue(!constructors.hasContent());
	}
}
