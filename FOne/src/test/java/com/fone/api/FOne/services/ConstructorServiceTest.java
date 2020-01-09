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

	// Test de UC-006
	@Test
	public void test_findAll() {
		Page<Constructor> all;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		all = this.constructorService.findAllAPI(pageable);

		assertNotNull(all);
		assertTrue(all.hasContent());
	}

	// Test de UC-007
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

	// Test de UC-007
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

	// Test de UC-007
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

	// Test de UC-010
	@Test
	public void positiveTestUno_findByName() {
		List<Constructor> constructors;
		String name;

		name = "Ferrari";
		constructors = this.constructorService.findByNameAPI(name);

		assertNotNull(constructors);
		assertTrue(constructors.size() > 0);
	}

	// Test de UC-010
	@Test
	public void positiveTestDos_findByName() {
		List<Constructor> constructors;
		String name;

		name = "ferrari";
		constructors = this.constructorService.findByNameAPI(name);

		assertNotNull(constructors);
		assertTrue(constructors.size() > 0);
	}

	// Test de UC-010
	@Test
	public void positiveTestTres_findByName() {
		List<Constructor> constructors;
		String name;

		name = "feRrArI";
		constructors = this.constructorService.findByNameAPI(name);

		assertNotNull(constructors);
		assertTrue(constructors.size() > 0);
	}

	// Test de UC-010
	@Test
	public void negativeTest_findByName() {
		List<Constructor> constructors;
		String name;

		name = "Escuderia erroneo";
		constructors = this.constructorService.findByNameAPI(name);

		assertNotNull(constructors);
		assertTrue(constructors.size() == 0);
	}

}
