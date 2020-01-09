package com.fone.api.FOne.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.fone.api.FOne.domain.Constructor;
import com.fone.api.FOne.domain.Driver;
import com.fone.api.FOne.domain.Result;
import com.fone.api.FOne.services.utilities.AbstractTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResultServiceTest extends AbstractTest {

	@Autowired
	private ResultService resultService;
	
	@Autowired
	private UtilityService utilityService;
	
	// Suite test --------------------------

	// Test de UC-004
	@Test
	public void positiveTestUno_findDriversByConstructor() {
		String constructor;
		Set<Driver> drivers;

		constructor = "Ferrari";
		drivers = this.resultService.findDriversByConstructorAPI(constructor);

		assertNotNull(drivers);
		assertTrue(drivers.size() > 0);
	}

	// Test de UC-004
	@Test
	public void positiveTestDos_findDriversByConstructor() {
		String constructor;
		Set<Driver> results;

		constructor = "Brawn";
		results = this.resultService.findDriversByConstructorAPI(constructor);

		assertNotNull(results);
		assertTrue(results.size() > 0);
	}

	// Test de UC-004
	@Test
	public void negativeTest_findDriversByConstructor() {
		String constructor;
		Set<Driver> results;

		constructor = "Nombre de escuderia erroneo";
		results = this.resultService.findDriversByConstructorAPI(constructor);

		assertNotNull(results);
		assertTrue(results.size() == 0);
	}

	// Test de UC-009
	@Test
	public void positiveTestUno_findConstructorsByDriver() {
		String driver;
		Set<Constructor> results;

		driver = "Fernando Alonso";
		results = this.resultService.findConstructorsByDriverAPI(driver);

		assertNotNull(results);
		assertTrue(results.size() > 0);
	}

	// Test de UC-009
	@Test
	public void positiveTestDos_findConstructorsByDriver() {
		String driver;
		Set<Constructor> results;

		driver = "Fernando Alonso";
		results = this.resultService.findConstructorsByDriverAPI(driver);

		assertNotNull(results);
		assertTrue(results.size() > 0);
	}

	// Test de UC-009
	@Test
	public void positiveTestTres_findConstructorsByDriver() {
		String driver;
		Set<Constructor> results;

		driver = "Niki Lauda";
		results = this.resultService.findConstructorsByDriverAPI(driver);

		assertNotNull(results);
		assertTrue(results.size() > 0);
	}

	// Test de UC-009
	@Test
	public void negativeTest_findConstructorsByDriver() {
		String driver;
		Set<Constructor> results;

		driver = "Piloto incorrecto";
		results = this.resultService.findConstructorsByDriverAPI(driver);

		assertNotNull(results);
		assertTrue(results.size() == 0);
	}

	// Test de UC-023
	@Test
	public void positiveTestUno_findResultsByPositionAndDriver() {
		Page<Result> results;
		String driver;
		String position;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		position = "1";
		driver = "Lewis Hamilton";

		results = this.resultService.findResultsByPositionAndDriverAPI(driver, position, pageable);

		assertNotNull(results);
		assertTrue(results.hasContent());
	}

	// Test de UC-023
	@Test
	public void positiveTestDos_findResultsByPositionAndDriver() {
		Page<Result> results;
		String driver;
		String position;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		position = "14";
		driver = "glock";

		results = this.resultService.findResultsByPositionAndDriverAPI(driver, position, pageable);

		assertNotNull(results);
		assertTrue(results.hasContent());
	}

	// Test de UC-023
	@Test
	public void negativeTestUno_findResultsByPositionAndDriver() {
		Page<Result> results;
		String driver;
		String position;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		position = "40";
		driver = "Lewis Hamilton";

		results = this.resultService.findResultsByPositionAndDriverAPI(driver, position, pageable);

		assertNotNull(results);
		assertTrue(!results.hasContent());
	}

	// Test de UC-023
	@Test
	public void negativeTestDos_findResultsByPositionAndDriver() {
		Page<Result> results;
		String driver;
		String position;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		position = "10";
		driver = "Piloto desconocido";

		results = this.resultService.findResultsByPositionAndDriverAPI(driver, position, pageable);

		assertNotNull(results);
		assertTrue(!results.hasContent());
	}

	// Test de UC-024
	@Test
	public void positiveTestUno_findResultsByGridAndDriver() {
		Page<Result> results;
		String grid;
		String driver;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		grid = "1";
		driver = "vettel";

		results = this.resultService.findResultsByGridAndDriverAPI(driver, grid, pageable);

		assertNotNull(results);
		assertTrue(results.hasContent());
	}

	// Test de UC-024
	@Test
	public void positiveTestDos_findResultsByGridAndDriver() {
		Page<Result> results;
		String grid;
		String driver;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		grid = "2";
		driver = "Fernando Alonso";

		results = this.resultService.findResultsByGridAndDriverAPI(driver, grid, pageable);

		assertNotNull(results);
		assertTrue(results.hasContent());
	}

	// Test de UC-024
	@Test
	public void negativeTestUno_findResultsByGridAndDriver() {
		Page<Result> results;
		String grid;
		String driver;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		grid = "40";
		driver = "Fernando Alonso";

		results = this.resultService.findResultsByGridAndDriverAPI(driver, grid, pageable);

		assertNotNull(results);
		assertTrue(!results.hasContent());
	}

	// Test de UC-024
	@Test
	public void negativeTestDos_findResultsByGridAndDriver() {
		Page<Result> results;
		String grid;
		String driver;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		grid = "5";
		driver = "Piloto desconocido";

		results = this.resultService.findResultsByGridAndDriverAPI(driver, grid, pageable);

		assertNotNull(results);
		assertTrue(!results.hasContent());
	}

	// Test de UC-025
	@Test
	public void positiveTestUno_findResultsByPositionAndConstructor() {
		Page<Result> results;
		String constructor;
		String position;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		position = "1";
		constructor = "Brawn";

		results = this.resultService.findResultsByPositionAndConstructorAPI(constructor, position, pageable);

		assertNotNull(results);
		assertTrue(results.hasContent());
	}

	// Test de UC-025
	@Test
	public void positiveTestDos_findResultsByPositionAndConstructor() {
		Page<Result> results;
		String constructor;
		String position;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		position = "5";
		constructor = "red bull";

		results = this.resultService.findResultsByPositionAndConstructorAPI(constructor, position, pageable);

		assertNotNull(results);
		assertTrue(results.hasContent());
	}

	// Test de UC-025
	@Test
	public void negativeTestUno_findResultsByPositionAndConstructor() {
		Page<Result> results;
		String constructor;
		String position;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		position = "40";
		constructor = "Ferrari";

		results = this.resultService.findResultsByPositionAndConstructorAPI(constructor, position, pageable);

		assertNotNull(results);
		assertTrue(!results.hasContent());
	}

	// Test de UC-025
	@Test
	public void negativeTestDos_findResultsByPositionAndConstructor() {
		Page<Result> results;
		String constructor;
		String position;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		position = "10";
		constructor = "Escuderia desconocida";

		results = this.resultService.findResultsByPositionAndConstructorAPI(constructor, position, pageable);

		assertNotNull(results);
		assertTrue(!results.hasContent());
	}

	// Test de UC-026
	@Test
	public void positiveTestUno_findResultsByGridAndConstructor() {
		Page<Result> results;
		String grid;
		String constructor;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		grid = "1";
		constructor = "mcLaren";

		results = this.resultService.findResultsByGridAndConstructorAPI(constructor, grid, pageable);

		assertNotNull(results);
		assertTrue(results.hasContent());
	}

	// Test de UC-026
	@Test
	public void positiveTestDos_findResultsByGridAndConstructorAPI() {
		Page<Result> results;
		String grid;
		String constructor;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		grid = "2";
		constructor = "williams";

		results = this.resultService.findResultsByGridAndConstructorAPI(constructor, grid, pageable);

		assertNotNull(results);
		assertTrue(results.hasContent());
	}

	// Test de UC-026
	@Test
	public void negativeTestUno_findResultsByGridAndConstructor() {
		Page<Result> results;
		String grid;
		String constructor;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		grid = "40";
		constructor = "force india";

		results = this.resultService.findResultsByGridAndConstructorAPI(constructor, grid, pageable);

		assertNotNull(results);
		assertTrue(!results.hasContent());
	}

	// Test de UC-026
	@Test
	public void negativeTestDos_findResultsByGridAndConstructor() {
		Page<Result> results;
		String grid;
		String constructor;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		grid = "5";
		constructor = "Escuderia desconocida";

		results = this.resultService.findResultsByGridAndConstructorAPI(constructor, grid, pageable);

		assertNotNull(results);
		assertTrue(!results.hasContent());
	}

}
