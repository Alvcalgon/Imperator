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

	@Test
	public void positiveTestUno_findDriversByConstructor() {
		String constructor;
		Set<Driver> drivers;

		constructor = "Ferrari";
		drivers = this.resultService.findDriversByConstructorAPI(constructor);

		assertNotNull(drivers);
		assertTrue(drivers.size() > 0);
	}

	@Test
	public void positiveTestDos_findDriversByConstructor() {
		String constructor;
		Set<Driver> results;

		constructor = "Brawn";
		results = this.resultService.findDriversByConstructorAPI(constructor);

		assertNotNull(results);
		assertTrue(results.size() > 0);
	}

	@Test
	public void negativeTest_findDriversByConstructor() {
		String constructor;
		Set<Driver> results;

		constructor = "Nombre de escuderia erroneo";
		results = this.resultService.findDriversByConstructorAPI(constructor);

		assertNotNull(results);
		assertTrue(results.size() == 0);
	}

	@Test
	public void positiveTestUno_findConstructorsByDriver() {
		String driver;
		Set<Constructor> results;

		driver = "Fernando Alonso";
		results = this.resultService.findConstructorsByDriverAPI(driver);

		assertNotNull(results);
		assertTrue(results.size() > 0);
	}

	@Test
	public void positiveTestDos_findConstructorsByDriver() {
		String driver;
		Set<Constructor> results;

		driver = "Fernando Alonso";
		results = this.resultService.findConstructorsByDriverAPI(driver);

		assertNotNull(results);
		assertTrue(results.size() > 0);
	}

	@Test
	public void positiveTestTres_findConstructorsByDriver() {
		String driver;
		Set<Constructor> results;

		driver = "Niki Lauda";
		results = this.resultService.findConstructorsByDriverAPI(driver);

		assertNotNull(results);
		assertTrue(results.size() > 0);
	}

	@Test
	public void negativeTest_findConstructorsByDriver() {
		String driver;
		Set<Constructor> results;

		driver = "Piloto incorrecto";
		results = this.resultService.findConstructorsByDriverAPI(driver);

		assertNotNull(results);
		assertTrue(results.size() == 0);
	}

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

	@Test
	public void positiveTestDos_findResultsByPositionAndDriver() {
		Page<Result> results;
		String driver;
		String position;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		position = "14";
		driver = "Timo Glock";

		results = this.resultService.findResultsByPositionAndDriverAPI(driver, position, pageable);

		assertNotNull(results);
		assertTrue(results.hasContent());
	}

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

	@Test
	public void positiveTestUno_findResultsByGridAndDriver() {
		Page<Result> results;
		String grid;
		String driver;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		grid = "1";
		driver = "Sebastian Vettel";

		results = this.resultService.findResultsByGridAndDriverAPI(driver, grid, pageable);

		assertNotNull(results);
		assertTrue(results.hasContent());
	}

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

	@Test
	public void positiveTestDos_findResultsByPositionAndConstructor() {
		Page<Result> results;
		String constructor;
		String position;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		position = "5";
		constructor = "Red Bull";

		results = this.resultService.findResultsByPositionAndConstructorAPI(constructor, position, pageable);

		assertNotNull(results);
		assertTrue(results.hasContent());
	}

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

	@Test
	public void positiveTestUno_findResultsByGridAndConstructor() {
		Page<Result> results;
		String grid;
		String constructor;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		grid = "1";
		constructor = "Brawn";

		results = this.resultService.findResultsByGridAndConstructorAPI(constructor, grid, pageable);

		assertNotNull(results);
		assertTrue(results.hasContent());
	}

	@Test
	public void positiveTestDos_findResultsByGridAndConstructorAPI() {
		Page<Result> results;
		String grid;
		String constructor;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		grid = "2";
		constructor = "Williams";

		results = this.resultService.findResultsByGridAndConstructorAPI(constructor, grid, pageable);

		assertNotNull(results);
		assertTrue(results.hasContent());
	}

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

	@Test
	public void positiveTest_findCountByPositionAndDriver() {
		Integer count;
		String driver;
		String position;
		
		driver = "Fernando Alonso";
		position = "1";
		
		count = this.resultService.findCountByPositionAndDriverAPI(driver, position);
		
		assertTrue(count > 0);
	}
	
	@Test
	public void positiveTest_findCountByGridAndDriver() {
		Integer count;
		String driver;
		String grid;
		
		driver = "Fernando Alonso";
		grid = "1";
		
		count = this.resultService.findCountByGridAndDriverAPI(driver, grid);
		
		assertTrue(count > 0);
	}
	
	@Test
	public void positiveTest_findCountByPositionAndConstructor() {
		Integer count;
		String constructor;
		String position;
		
		constructor = "Ferrari";
		position = "1";
		
		count = this.resultService.findCountByPositionAndConstructorAPI(constructor, position);
		
		assertTrue(count > 0);
	}
	
	@Test
	public void positiveTest_findCountByGridAndConstructor() {
		Integer count;
		String constructor;
		String grid;
		
		constructor = "Ferrari";
		grid = "1";
		
		count = this.resultService.findCountByGridAndConstructorAPI(constructor, grid);
		
		assertTrue(count > 0);
	}
	
	@Test
	public void positiveTest_findCountByDriverAPI() {
		Integer count;
		String driver;
		
		driver  = "Fernando Alonso";
		count = this.resultService.findCountByDriverAPI(driver);
		
		assertTrue(count > 0);
	}
	
}
