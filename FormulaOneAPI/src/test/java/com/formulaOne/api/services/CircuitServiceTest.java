package com.formulaOne.api.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.formulaOne.api.domain.Circuit;
import com.formulaOne.api.services.utilities.AbstractTest;

@SpringBootTest
public class CircuitServiceTest extends AbstractTest {

	// Service under testing -------------
	@Autowired
	private CircuitService circuitService;
	
	@Autowired
	private UtilityService utilityService;
	
	// Suite test ------------------------
	@Test
	@DisplayName("Acceder a los datos de un circuito")
	public void test_getCircuit() {
		Circuit circuit;
		Document doc;
		
		doc = this.utilityService.getDocument("https://www.f1-fansite.com/f1-circuits/albert-park-circuit/");
		
		circuit = this.circuitService.getCircuit(doc, "Albert Park Circuit");
		
		assertNotNull(circuit);
	}
	
}
