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

import com.fone.api.FOne.domain.Circuit;
import com.fone.api.FOne.services.utilities.AbstractTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CircuitServiceTest extends AbstractTest {

	// Servicio bajo pruebas -------------
	@Autowired
	private CircuitService circuitService;

	@Autowired
	private UtilityService utilityService;
	
	// Suite test ------------------------

	// Test de UC-11
	@Test
	public void test_findAll() {
		Page<Circuit> all;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		all = this.circuitService.findAllAPI(pageable);

		assertNotNull(all);
		assertTrue(all.hasContent());
	}

	// Test de UC-012
	@Test
	public void positiveTest_findByType() {
		Page<Circuit> circuits;
		String type;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		type = "Street Circuit";
		circuits = this.circuitService.findByTypeAPI(type, pageable);

		assertNotNull(circuits);
		assertTrue(circuits.hasContent());
	}

	// Test de UC-012
	@Test
	public void negativeTest_findByType() {
		Page<Circuit> circuits;
		String type;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		type = "Tipo erroneo";
		circuits = this.circuitService.findByTypeAPI(type, pageable);

		assertNotNull(circuits);
		assertTrue(!circuits.hasContent());
	}

	// Test de UC-013: un usuario introduce un pais como localizacion
	@Test
	public void positiveTestUno_findByLocation() {
		Page<Circuit> circuits;
		String location;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		location = "Italy";
		circuits = this.circuitService.findByLocationAPI(location, pageable);

		assertNotNull(circuits);
		assertTrue(circuits.hasContent());
	}

	// Test de UC-013: un usuario introduce un pais como localizacion
	@Test
	public void positiveTestDos_findByLocation() {
		Page<Circuit> circuits;
		String location;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		location = "spain";
		circuits = this.circuitService.findByLocationAPI(location, pageable);

		assertNotNull(circuits);
		assertTrue(circuits.hasContent());
	}

	// Test de UC-013: un usuario introduce una ciudad como localizacion
	@Test
	public void positiveTestTres_findByLocation() {
		Page<Circuit> circuits;
		String location;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		location = "São Paulo";
		circuits = this.circuitService.findByLocationAPI(location, pageable);

		assertNotNull(circuits);
		assertTrue(circuits.hasContent());
	}

	// Test de UC-013: un usuario introduce una ciudad como localizacion
	@Test
	public void positiveTestCuatro_findByLocation() {
		Page<Circuit> circuits;
		String location;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		location = "rio de janeiro";
		circuits = this.circuitService.findByLocationAPI(location, pageable);

		assertNotNull(circuits);
		assertTrue(circuits.hasContent());
	}

	// Test de UC-013: a la localizacion le falta un acento en 'Sao'
	@Test
	public void negativeTestUno_findByLocation() {
		Page<Circuit> circuits;
		String location;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		location = "Sao Paulo";
		circuits = this.circuitService.findByLocationAPI(location, pageable);

		assertNotNull(circuits);
		assertTrue(!circuits.hasContent());
	}

	// Test de UC-013: un usuario introduce un pais como localizacion
	@Test
	public void negativeTestDos_findByLocation() {
		Page<Circuit> circuits;
		String location;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		location = "Localizacion incorrecta";
		circuits = this.circuitService.findByLocationAPI(location, pageable);

		assertNotNull(circuits);
		assertTrue(!circuits.hasContent());
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
