package com.fone.api.FOne.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

		constructor = "ferrari";
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

		driver = "fernando alonso";
		results = this.resultService.findConstructorsByDriverAPI(driver);

		assertNotNull(results);
		assertTrue(results.size() > 0);
	}

	// Test de UC-009
	@Test
	public void positiveTestTres_findConstructorsByDriver() {
		String driver;
		Set<Constructor> results;

		driver = "Alonso";
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
		List<Result> results;
		String driver;
		String position;

		position = "1";
		driver = "Lewis Hamilton";

		results = this.resultService.findResultsByPositionAndDriverAPI(driver, position);

		assertNotNull(results);
		assertTrue(results.size() > 0);
	}

	// Test de UC-023
	@Test
	public void positiveTestDos_findResultsByPositionAndDriver() {
		List<Result> results;
		String driver;
		String position;

		position = "14";
		driver = "glock";

		results = this.resultService.findResultsByPositionAndDriverAPI(driver, position);

		assertNotNull(results);
		assertTrue(results.size() > 0);
	}

	// Test de UC-023
	@Test
	public void negativeTestUno_findResultsByPositionAndDriver() {
		List<Result> results;
		String driver;
		String position;

		position = "40";
		driver = "Lewis Hamilton";

		results = this.resultService.findResultsByPositionAndDriverAPI(driver, position);

		assertNotNull(results);
		assertTrue(results.size() == 0);
	}

	// Test de UC-023
	@Test
	public void negativeTestDos_findResultsByPositionAndDriver() {
		List<Result> results;
		String driver;
		String position;

		position = "10";
		driver = "Piloto desconocido";

		results = this.resultService.findResultsByPositionAndDriverAPI(driver, position);

		assertNotNull(results);
		assertTrue(results.size() == 0);
	}

	// Test de UC-024
	@Test
	public void positiveTestUno_findResultsByGridAndDriver() {
		List<Result> results;
		String grid;
		String driver;

		grid = "1";
		driver = "vettel";

		results = this.resultService.findResultsByGridAndDriverAPI(driver, grid);

		assertNotNull(results);
		assertTrue(results.size() > 0);
	}

	// Test de UC-024
	@Test
	public void positiveTestDos_findResultsByGridAndDriver() {
		List<Result> results;
		String grid;
		String driver;

		grid = "2";
		driver = "Fernando Alonso";

		results = this.resultService.findResultsByGridAndDriverAPI(driver, grid);

		assertNotNull(results);
		assertTrue(results.size() > 0);
	}

	// Test de UC-024
	@Test
	public void negativeTestUno_findResultsByGridAndDriver() {
		List<Result> results;
		String grid;
		String driver;

		grid = "40";
		driver = "Fernando Alonso";

		results = this.resultService.findResultsByGridAndDriverAPI(driver, grid);

		assertNotNull(results);
		assertTrue(results.size() == 0);
	}

	// Test de UC-024
	@Test
	public void negativeTestDos_findResultsByGridAndDriver() {
		List<Result> results;
		String grid;
		String driver;

		grid = "5";
		driver = "Piloto desconocido";

		results = this.resultService.findResultsByGridAndDriverAPI(driver, grid);

		assertNotNull(results);
		assertTrue(results.size() == 0);
	}

	// Test de UC-025
	@Test
	public void positiveTestUno_findResultsByPositionAndConstructor() {
		List<Result> results;
		String constructor;
		String position;

		position = "1";
		constructor = "Brawn";

		results = this.resultService.findResultsByPositionAndConstructorAPI(constructor, position);

		assertNotNull(results);
		assertTrue(results.size() > 0);
	}

	// Test de UC-025
	@Test
	public void positiveTestDos_findResultsByPositionAndConstructor() {
		List<Result> results;
		String constructor;
		String position;

		position = "5";
		constructor = "red bull";

		results = this.resultService.findResultsByPositionAndConstructorAPI(constructor, position);

		assertNotNull(results);
		assertTrue(results.size() > 0);
	}

	// Test de UC-025
	@Test
	public void negativeTestUno_findResultsByPositionAndConstructor() {
		List<Result> results;
		String constructor;
		String position;

		position = "40";
		constructor = "Ferrari";

		results = this.resultService.findResultsByPositionAndConstructorAPI(constructor, position);

		assertNotNull(results);
		assertTrue(results.size() == 0);
	}

	// Test de UC-025
	@Test
	public void negativeTestDos_findResultsByPositionAndConstructor() {
		List<Result> results;
		String constructor;
		String position;

		position = "10";
		constructor = "Escuderia desconocida";

		results = this.resultService.findResultsByPositionAndConstructorAPI(constructor, position);

		assertNotNull(results);
		assertTrue(results.size() == 0);
	}

	// Test de UC-026
	@Test
	public void positiveTestUno_findResultsByGridAndConstructor() {
		List<Result> results;
		String grid;
		String constructor;

		grid = "1";
		constructor = "mcLaren";

		results = this.resultService.findResultsByGridAndConstructorAPI(constructor, grid);

		assertNotNull(results);
		assertTrue(results.size() > 0);
	}

	// Test de UC-026
	@Test
	public void positiveTestDos_findResultsByGridAndConstructorAPI() {
		List<Result> results;
		String grid;
		String constructor;

		grid = "2";
		constructor = "williams";

		results = this.resultService.findResultsByGridAndConstructorAPI(constructor, grid);

		assertNotNull(results);
		assertTrue(results.size() > 0);
	}

	// Test de UC-026
	@Test
	public void negativeTestUno_findResultsByGridAndConstructor() {
		List<Result> results;
		String grid;
		String constructor;

		grid = "40";
		constructor = "force india";

		results = this.resultService.findResultsByGridAndConstructorAPI(constructor, grid);

		assertNotNull(results);
		assertTrue(results.size() == 0);
	}

	// Test de UC-026
	@Test
	public void negativeTestDos_findResultsByGridAndConstructor() {
		List<Result> results;
		String grid;
		String constructor;

		grid = "5";
		constructor = "Escuderia desconocida";

		results = this.resultService.findResultsByGridAndConstructorAPI(constructor, grid);

		assertNotNull(results);
		assertTrue(results.size() == 0);
	}

}
