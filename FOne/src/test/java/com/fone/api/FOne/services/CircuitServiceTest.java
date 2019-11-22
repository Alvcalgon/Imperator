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

	// Service under testing -------------
		@Autowired
		private CircuitService circuitService;
		
		@Autowired
		private UtilityService utilityService;
		
		// Suite test ------------------------
//		@Test
//		@DisplayName("Acceder a los datos de un circuito")
//		public void test_getCircuit() {
//			Circuit circuit;
//			Document doc;
//			
//			doc = this.utilityService.getDocument("https://www.f1-fansite.com/f1-circuits/albert-park-circuit/");
//			
//			circuit = this.circuitService.getCircuit(doc, "Albert Park Circuit");
//			
//			assertNotNull(circuit);
//		}
		
		@Test
		public void test_findAll() {
			List<Circuit> all;
			
			all = this.circuitService.findAll();
			
			assertNotNull(all);
			assertTrue(all.size() > 0);
		}
		
		
		@Test
		public void test_findById() {
			Circuit circuit;
			String id;
			
			id = "5dc688d842478c1928f06056";
			circuit = this.circuitService.findOne(id);
			
			assertNotNull(circuit);
		}
		
		@Test
		public void positiveTest_findByName() {
			Circuit circuit;
			String name;
			
			name = "Adelaide Street Circuit";
			circuit = this.circuitService.findByName(name);
			
			assertNotNull(circuit);
		}
		
		@Test
		public void negativeTest_findByName() {
			Circuit circuit;
			String name;
		
			name = "Nombre erroneo";
			circuit = this.circuitService.findByName(name);
		
			assertTrue(circuit == null);
		}
		
		@Test
		public void positiveTest_findByType() {
			List<Circuit> circuits;
			String type;
			
			type = "Street Circuit";
			circuits = this.circuitService.findByType(type);
			
			assertNotNull(circuits);
			assertTrue(circuits.size() > 0);
		}
		
		@Test
		public void negativeTest_findByType() {
			List<Circuit> circuits;
			String type;
		
			type = "Tipo erroneo";
			circuits = this.circuitService.findByType(type);
		
			assertNotNull(circuits);
			assertTrue(circuits.size() == 0);
		}
	
}