package com.formulaOne.api.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.formulaOne.api.domain.Constructor;
import com.formulaOne.api.services.utilities.AbstractTest;

@SpringBootTest
public class ConstructorServiceTest extends AbstractTest {

	// Service under testing --------
	@Autowired
	private ConstructorService constructorService;
	
	@Autowired
	private UtilityService utilityService;
	
	// Suite test -------------------
	
	@Test
	@DisplayName("Acceder a los datos de una escuderia con principal: Ferrari")
	public void testUno_getConstructor() {
		Constructor constructor;
		Document doc;
		
		doc = this.utilityService.getDocument("https://www.f1-fansite.com/f1-teams/ferrari-f1-information-statistics/");
		
		constructor = this.constructorService.getConstructor(doc);
		
		assertNotNull(constructor);
	}
	
	@Test
	@DisplayName("Acceder a los datos de una escuderia con principal desconocido: Ewing")
	public void testDos_getConstructor() {
		Constructor constructor;
		Document doc;
		
		doc = this.utilityService.getDocument("https://www.f1-fansite.com/f1-teams/ewing-f1-team-information-statistics/");
		
		constructor = this.constructorService.getConstructor(doc);
		
		assertNotNull(constructor);
	}
	
	
//	@Test
//	@DisplayName("Acceder al listado de escuderias")
//	public void test_loadConstructors() {
//		int constructors;
//		
//		this.constructorService.loadConstructors();
//		
//		constructors = this.constructorService.findAll().size();
//		
//		assertTrue(constructors > 0);
//	}
	
}
