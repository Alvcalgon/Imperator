package com.fone.api.FOne.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fone.api.FOne.domain.Constructor;
import com.fone.api.FOne.services.utilities.AbstractTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConstructorServiceTest extends AbstractTest {

	// Service under testing --------
	@Autowired
	private ConstructorService constructorService;

	// Suite test -------------------

	// Test de UC-006
	@Test
	public void test_findAll() {
		List<Constructor> all;

		all = this.constructorService.findAllAPI();

		assertNotNull(all);
		assertTrue(all.size() > 0);
	}

	// Test de UC-007
	@Test
	public void positiveTestUno_findByCountry() {
		List<Constructor> constructors;
		String country;

		country = "Italy";
		constructors = this.constructorService.findByCountryAPI(country);

		assertNotNull(constructors);
		assertTrue(constructors.size() > 0);
	}

	// Test de UC-007
	@Test
	public void positiveTestDos_findByCountry() {
		List<Constructor> constructors;
		String country;

		country = "ITALY";
		constructors = this.constructorService.findByCountryAPI(country);

		assertNotNull(constructors);
		assertTrue(constructors.size() > 0);
	}

	// Test de UC-007
	@Test
	public void positiveTestTres_findByCountry() {
		List<Constructor> constructors;
		String country;

		country = "ItAlY";
		constructors = this.constructorService.findByCountryAPI(country);

		assertNotNull(constructors);
		assertTrue(constructors.size() > 0);
	}

	@Test
	public void negativeTest_findByCountry() {
		List<Constructor> constructors;
		String country;

		country = "Pais erroneo";
		constructors = this.constructorService.findByCountryAPI(country);

		assertNotNull(constructors);
		assertNotNull(constructors.size() == 0);
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
