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

	@Test
	public void positiveTest_getLinks() {
		List<String> urls;
		
		urls = this.constructorService.getLinks();
		
		assertTrue(urls.size() == 3);
	}
	
	@Test
	public void test_findAll() {
		Page<Constructor> all;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		all = this.constructorService.findAllAPI(pageable);

		assertNotNull(all);
		assertTrue(all.hasContent());
	}

	
	@Test
	public void positiveTestUno_findByNationality() {
		Page<Constructor> constructors;
		String nationality;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		nationality = "Italian";
		constructors = this.constructorService.findByNationalityAPI(nationality, pageable);

		assertNotNull(constructors);
		assertTrue(constructors.hasContent());
	}

	
	@Test
	public void positiveTestDos_findByNationality() {
		Page<Constructor> constructors;
		String nationality;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		nationality = "british";
		constructors = this.constructorService.findByNationalityAPI(nationality, pageable);

		assertNotNull(constructors);
		assertTrue(constructors.hasContent());
	}

	
	@Test
	public void positiveTestTres_findByNationality() {
		Page<Constructor> constructors;
		String nationality;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		nationality = "FRenCh";
		constructors = this.constructorService.findByNationalityAPI(nationality, pageable);

		assertNotNull(constructors);
		assertTrue(constructors.hasContent());
	}

	@Test
	public void negativeTest_findByNationality() {
		Page<Constructor> constructors;
		String nationality;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		nationality = "Nacionalidad erronea";
		constructors = this.constructorService.findByNationalityAPI(nationality, pageable);

		assertNotNull(constructors);
		assertNotNull(!constructors.hasContent());
	}

	
	@Test
	public void positiveTestUno_findByName() {
		Page<Constructor> constructors;
		String name;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		name = "Ferrari";
		constructors = this.constructorService.findByNameAPI(name, pageable);

		assertNotNull(constructors);
		assertTrue(constructors.hasContent());
	}

	
	@Test
	public void positiveTestDos_findByName() {
		Page<Constructor> constructors;
		String name;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		name = "ferrari";
		constructors = this.constructorService.findByNameAPI(name, pageable);

		assertNotNull(constructors);
		assertTrue(constructors.hasContent());
	}

	
	@Test
	public void positiveTestTres_findByName() {
		Page<Constructor> constructors;
		String name;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		name = "feRrArI";
		constructors = this.constructorService.findByNameAPI(name, pageable);

		assertNotNull(constructors);
		assertTrue(constructors.hasContent());
	}

	
	@Test
	public void negativeTest_findByName() {
		Page<Constructor> constructors;
		String name;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		name = "Escuderia erroneo";
		constructors = this.constructorService.findByNameAPI(name, pageable);

		assertNotNull(constructors);
		assertTrue(!constructors.hasContent());
	}

	@Test
	public void positiveTest_findByNameAPI2() {
		Constructor constructor;
		String name;
		
		name = "Ferrari";
		constructor = this.constructorService.findByNameAPI2(name);
		
		assertNotNull(constructor);
		assertTrue(constructor.getName().equals(name));
	}
	
	@Test
	public void negativeTest_findByNameAPI2() {
		Constructor constructor;
		String name;
		
		name = "escuderia ferrari";
		constructor = this.constructorService.findByNameAPI2(name);
		
		assertTrue(constructor == null);
	}
	
	@Test
	public void positiveTest_findByParametersAPI() {
		Page<Constructor> constructors;
		String name;
		String nationality;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		name = "Ferrari";
		nationality = "Italian";
		
		constructors = this.constructorService.findByParametersAPI(name, nationality, pageable);
		
		assertNotNull(constructors);
		assertTrue(constructors.hasContent());
	}
	
	
	@Test
	public void negativeTest_findByParametersAPI() {
		Page<Constructor> constructors;
		String name;
		String nationality;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		name = "Ferrari";
		nationality = "Spanish";
		
		constructors = this.constructorService.findByParametersAPI(name, nationality, pageable);
		
		assertNotNull(constructors);
		assertTrue(!constructors.hasContent());
	}
}
