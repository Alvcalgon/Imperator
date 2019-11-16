package com.formulaOne.api.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

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
	
//	@Test
//	@DisplayName("Acceder a los datos de una escuderia con principal: Ferrari")
//	public void testUno_getConstructor() {
//		Constructor constructor;
//		Document doc;
//		
//		doc = this.utilityService.getDocument("https://www.f1-fansite.com/f1-teams/ferrari-f1-information-statistics/");
//		
//		constructor = this.constructorService.getConstructor(doc);
//		
//		assertNotNull(constructor);
//	}
//	
//	@Test
//	@DisplayName("Acceder a los datos de una escuderia con principal desconocido: Ewing")
//	public void testDos_getConstructor() {
//		Constructor constructor;
//		Document doc;
//		
//		doc = this.utilityService.getDocument("https://www.f1-fansite.com/f1-teams/ewing-f1-team-information-statistics/");
//		
//		constructor = this.constructorService.getConstructor(doc);
//		
//		assertNotNull(constructor);
//	}
	
	@Test
	@DisplayName("Recuperar la lista completa de escuderias")
	public void test_findAll() {
		List<Constructor> all;
		
		all = this.constructorService.findAll();
		
		assertNotNull(all);
		assertTrue(all.size() > 0);
	}
	
	@Test
	@DisplayName("Recuperar una escuderia dado su id")
	public void test_findById() {
		Constructor constructor;
		String id;
		
		// Constructor::name = "Adams"
		id = "5dc68d152d984d7d1639e0fa";
		constructor = this.constructorService.findOne(id);
		
		assertNotNull(constructor);
	}
	
	
	@Test
	@DisplayName("Recuperar con exito una escuderia dado su nombre")
	public void positiveTest_findByName() {
		Constructor constructor;
		String name;
		
		name = "Ferrari";
		constructor = this.constructorService.findByName(name);
		
		assertNotNull(constructor);
	}
	
	@Test
	@DisplayName("Recuperar con exito una escuderia dado su nombre")
	public void negativeTest_findByName() {
		Constructor constructor;
		String name;
		
		name = "Nombre erroneo";
		constructor = this.constructorService.findByName(name);
		
		assertTrue(constructor == null);
	}
	
	@Test
	@DisplayName("Recuperar con exito las escuderias de un pais")
	public void positiveTest_findByCountry() {
		List<Constructor> constructors;
		String country;
		
		country = "Italy";
		constructors = this.constructorService.findByCountry(country);
		
		assertNotNull(constructors);
		assertTrue(constructors.size() > 0);
	}
	
	@Test
	@DisplayName("Recuperar con exito las escuderias de un pais")
	public void negativeTest_findByCountry() {
		List<Constructor> constructors;
		String country;
		
		country = "Pais erroneo";
		constructors = this.constructorService.findByCountry(country);
		
		assertNotNull(constructors);
		assertNotNull(constructors.size() == 0);
	}
	
}
