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

import com.fone.api.FOne.domain.ConstructorStanding;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConstructorStandingServiceTest {

	// Servicio bajo testeo -------------------
	@Autowired
	private ConstructorStandingService contructorStandingService;

	@Autowired
	private UtilityService utilityService;
	
	// Suite test -----------------------------

	// Test de UC-030
	@Test
	public void positiveTest_findBySeason() {
		Page<ConstructorStanding> standing;
		String season;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		season = "1966";
		standing = this.contructorStandingService.findBySeasonAPI(season, pageable);

		assertNotNull(standing);
		assertTrue(standing.hasContent());
	}

	// Test de UC-030
	@Test
	public void negativeTest_findBySeason() {
		Page<ConstructorStanding> standing;
		String season;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		
		season = "1957";
		standing = this.contructorStandingService.findBySeasonAPI(season, pageable);

		assertNotNull(standing);
		assertTrue(!standing.hasContent());
	}

	// Test de UC-031
	@Test
	public void positiveTest_findByPosition() {
		Page<ConstructorStanding> standing;
		String position;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		position = "10";
		standing = this.contructorStandingService.findByPositionAPI(position, pageable);

		assertNotNull(standing);
		assertTrue(standing.hasContent());
	}

	// Test de UC-031
	@Test
	public void negativeTest_findByPosition() {
		Page<ConstructorStanding> standing;
		String position;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		position = "40";
		standing = this.contructorStandingService.findByPositionAPI(position, pageable);

		assertNotNull(standing);
		assertTrue(!standing.hasContent());
	}

	// Test de UC-032
	@Test
	public void positiveTestUno_findByConstructor() {
		Page<ConstructorStanding> standing;
		String constructor;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		constructor = "Renault";
		standing = this.contructorStandingService.findByConstructorAPI(constructor, pageable);

		assertNotNull(standing);
		assertTrue(standing.hasContent());
	}

	// Test de UC-032
	@Test
	public void positiveTestDos_findByConstructor() {
		Page<ConstructorStanding> standing;
		String constructor;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		constructor = "alfa romeo";
		standing = this.contructorStandingService.findByConstructorAPI(constructor, pageable);

		assertNotNull(standing);
		assertTrue(standing.hasContent());
	}

	// Test de UC-032
	@Test
	public void negativeTestUno_findByConstructor() {
		Page<ConstructorStanding> standing;
		String constructor;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		constructor = "Escuderia desconocida";
		standing = this.contructorStandingService.findByConstructorAPI(constructor, pageable);

		assertNotNull(standing);
		assertTrue(!standing.hasContent());
	}

}
