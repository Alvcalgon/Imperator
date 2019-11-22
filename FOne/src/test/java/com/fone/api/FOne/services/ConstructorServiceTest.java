package com.fone.api.FOne.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fone.api.FOne.domain.Constructor;
import com.fone.api.FOne.services.utilities.AbstractTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConstructorServiceTest extends AbstractTest {

	// Service under testing --------
		@Autowired
		private ConstructorService constructorService;
		
		@Autowired
		private UtilityService utilityService;
		
		// Suite test -------------------
		
//		@Test
//		@DisplayName("Acceder a los datos de una escuderia con principal: Ferrari")
//		public void testUno_getConstructor() {
//			Constructor constructor;
//			Document doc;
//			
//			doc = this.utilityService.getDocument("https://www.f1-fansite.com/f1-teams/ferrari-f1-information-statistics/");
//			
//			constructor = this.constructorService.getConstructor(doc);
//			
//			assertNotNull(constructor);
//		}
	//	
//		@Test
//		@DisplayName("Acceder a los datos de una escuderia con principal desconocido: Ewing")
//		public void testDos_getConstructor() {
//			Constructor constructor;
//			Document doc;
//			
//			doc = this.utilityService.getDocument("https://www.f1-fansite.com/f1-teams/ewing-f1-team-information-statistics/");
//			
//			constructor = this.constructorService.getConstructor(doc);
//			
//			assertNotNull(constructor);
//		}
		
		@Test
		public void test_findAll() {
			List<Constructor> all;
			
			all = this.constructorService.findAll();
			
			assertNotNull(all);
			assertTrue(all.size() > 0);
		}
		
		@Test
		public void test_findById() {
			Constructor constructor;
			String id;
			
			// Constructor::name = "Adams"
			id = "5dc68d152d984d7d1639e0fa";
			constructor = this.constructorService.findOne(id);
			
			assertNotNull(constructor);
		}
		
		
		@Test
		public void positiveTest_findByName() {
			Constructor constructor;
			String name;
			
			name = "Ferrari";
			constructor = this.constructorService.findByName(name);
			
			assertNotNull(constructor);
		}
		
		@Test
		public void negativeTest_findByName() {
			Constructor constructor;
			String name;
			
			name = "Nombre erroneo";
			constructor = this.constructorService.findByName(name);
			
			assertTrue(constructor == null);
		}
		
		@Test
		public void positiveTest_findByCountry() {
			List<Constructor> constructors;
			String country;
			
			country = "Italy";
			constructors = this.constructorService.findByCountry(country);
			
			assertNotNull(constructors);
			assertTrue(constructors.size() > 0);
		}
		
		@Test
		public void negativeTest_findByCountry() {
			List<Constructor> constructors;
			String country;
			
			country = "Pais erroneo";
			constructors = this.constructorService.findByCountry(country);
			
			assertNotNull(constructors);
			assertNotNull(constructors.size() == 0);
		}
	
}
