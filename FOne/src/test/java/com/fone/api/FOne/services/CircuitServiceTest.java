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

	@Test
	public void test_findAll() {
		Page<Circuit> all;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		all = this.circuitService.findAllAPI(pageable);

		assertNotNull(all);
		assertTrue(all.hasContent());
	}
	
	// Un usuario introduce un pais como localizacion
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

	// Un usuario introduce un pais como localizacion
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

	// Un usuario introduce una ciudad como localizacion
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

	// Un usuario introduce una ciudad como localizacion
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

	// A la localizacion le falta un acento en 'Sao'
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

	// Un usuario introduce un pais como localizacion
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

	@Test
	public void positiveTestUno_findByNameAPI() {
		Page<Circuit> circuits;
		String name;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		name = "Baku";
		circuits = this.circuitService.findByNameAPI(name, pageable);

		assertNotNull(circuits);
		assertTrue(circuits.hasContent());
	}

	@Test
	public void positiveTestDos_findByNameAPI() {
		Page<Circuit> circuits;
		String name;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		name = "zeltweg";
		circuits = this.circuitService.findByNameAPI(name, pageable);

		assertNotNull(circuits);
		assertTrue(circuits.hasContent());
	}

	@Test
	public void negativeTest_findByNameAPI() {
		Page<Circuit> circuits;
		String name;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		name = "Nombre incorrecto";
		circuits = this.circuitService.findByNameAPI(name, pageable);

		assertNotNull(circuits);
		assertTrue(!circuits.hasContent());
	}

	@Test
	public void positiveTest_findByNameAPI2() {
		Circuit circuit;
		String name;
		
		name = "Albert Park Circuit";
		circuit = this.circuitService.findByNameAPI2(name);
		
		assertNotNull(circuit);
	}
	
	@Test
	public void negativeTest_findByNameAPI2() {
		Circuit circuit;
		String name;
		
		name = "desconocido";
		circuit = this.circuitService.findByNameAPI2(name);
		
		assertTrue(circuit == null);
	}
	
}
