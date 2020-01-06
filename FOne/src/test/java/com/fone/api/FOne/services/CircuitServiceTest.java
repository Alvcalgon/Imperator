package com.fone.api.FOne.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fone.api.FOne.domain.Circuit;
import com.fone.api.FOne.services.utilities.AbstractTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CircuitServiceTest extends AbstractTest {

	// Servicio bajo pruebas -------------
	@Autowired
	private CircuitService circuitService;

	// Suite test ------------------------

	// Test de UC-11
	@Test
	public void test_findAll() {
		List<Circuit> all;

		all = this.circuitService.findAllAPI();

		assertNotNull(all);
		assertTrue(all.size() > 0);
	}

	// Test de UC-012
	@Test
	public void positiveTest_findByType() {
		List<Circuit> circuits;
		String type;

		type = "Street Circuit";
		circuits = this.circuitService.findByTypeAPI(type);

		assertNotNull(circuits);
		assertTrue(circuits.size() > 0);
	}

	// Test de UC-012
	@Test
	public void negativeTest_findByType() {
		List<Circuit> circuits;
		String type;

		type = "Tipo erroneo";
		circuits = this.circuitService.findByTypeAPI(type);

		assertNotNull(circuits);
		assertTrue(circuits.size() == 0);
	}

	// Test de UC-013: un usuario introduce un pais como localizacion
	@Test
	public void positiveTestUno_findByLocation() {
		List<Circuit> circuits;
		String location;

		location = "Italy";
		circuits = this.circuitService.findByLocationAPI(location);

		assertNotNull(circuits);
		assertTrue(circuits.size() > 0);
	}

	// Test de UC-013: un usuario introduce un pais como localizacion
	@Test
	public void positiveTestDos_findByLocation() {
		List<Circuit> circuits;
		String location;

		location = "spain";
		circuits = this.circuitService.findByLocationAPI(location);

		assertNotNull(circuits);
		assertTrue(circuits.size() > 0);
	}

	// Test de UC-013: un usuario introduce una ciudad como localizacion
	@Test
	public void positiveTestTres_findByLocation() {
		List<Circuit> circuits;
		String location;

		location = "São Paulo";
		circuits = this.circuitService.findByLocationAPI(location);

		assertNotNull(circuits);
		assertTrue(circuits.size() > 0);
	}

	// Test de UC-013: un usuario introduce una ciudad como localizacion
	@Test
	public void positiveTestCuatro_findByLocation() {
		List<Circuit> circuits;
		String location;

		location = "rio de janeiro";
		circuits = this.circuitService.findByLocationAPI(location);

		assertNotNull(circuits);
		assertTrue(circuits.size() > 0);
	}

	// Test de UC-013: a la localizacion le falta un acento en 'Sao'
	@Test
	public void negativeTestUno_findByLocation() {
		List<Circuit> circuits;
		String location;

		location = "Sao Paulo";
		circuits = this.circuitService.findByLocationAPI(location);

		assertNotNull(circuits);
		assertTrue(circuits.size() == 0);
	}

	// Test de UC-013: un usuario introduce un pais como localizacion
	@Test
	public void negativeTestDos_findByLocation() {
		List<Circuit> circuits;
		String location;

		location = "Localizacion incorrecta";
		circuits = this.circuitService.findByLocationAPI(location);

		assertNotNull(circuits);
		assertTrue(circuits.size() == 0);
	}

	// Test de UC-015
	@Test
	public void positiveTestUno_findByNameAPI() {
		List<Circuit> circuits;
		String name;

		name = "Baku";
		circuits = this.circuitService.findByNameAPI(name);

		assertNotNull(circuits);
		assertTrue(circuits.size() > 0);
	}

	// Test de UC-015
	@Test
	public void positiveTestDos_findByNameAPI() {
		List<Circuit> circuits;
		String name;

		name = "zeltweg airfield";
		circuits = this.circuitService.findByNameAPI(name);

		assertNotNull(circuits);
		assertTrue(circuits.size() > 0);
	}

	// Test de UC-015
	@Test
	public void negativeTest_findByNameAPI() {
		List<Circuit> circuits;
		String name;

		name = "Nombre incorrecto";
		circuits = this.circuitService.findByNameAPI(name);

		assertNotNull(circuits);
		assertTrue(circuits.size() == 0);
	}

}
