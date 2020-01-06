package com.fone.api.FOne.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fone.api.FOne.domain.ConstructorStanding;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConstructorStandingServiceTest {

	// Servicio bajo testeo -------------------
	@Autowired
	private ConstructorStandingService contructorStandingService;

	// Suite test -----------------------------

	// Test de UC-030
	@Test
	public void positiveTest_findBySeason() {
		List<ConstructorStanding> standing;
		String season;

		season = "1966";
		standing = this.contructorStandingService.findBySeasonAPI(season);

		assertNotNull(standing);
		assertTrue(standing.size() > 0);
	}

	// Test de UC-030
	@Test
	public void negativeTest_findBySeason() {
		List<ConstructorStanding> standing;
		String season;

		season = "1957";
		standing = this.contructorStandingService.findBySeasonAPI(season);

		assertNotNull(standing);
		assertTrue(standing.size() == 0);
	}

	// Test de UC-031
	@Test
	public void positiveTest_findByPosition() {
		List<ConstructorStanding> standing;
		String position;

		position = "10";
		standing = this.contructorStandingService.findByPositionAPI(position);

		assertNotNull(standing);
		assertTrue(standing.size() > 0);
	}

	// Test de UC-031
	@Test
	public void negativeTest_findByPosition() {
		List<ConstructorStanding> standing;
		String position;

		position = "40";
		standing = this.contructorStandingService.findByPositionAPI(position);

		assertNotNull(standing);
		assertTrue(standing.size() == 0);
	}

	// Test de UC-032
	@Test
	public void positiveTestUno_findByConstructor() {
		List<ConstructorStanding> standing;
		String constructor;

		constructor = "Renault";
		standing = this.contructorStandingService.findByConstructorAPI(constructor);

		assertNotNull(standing);
		assertTrue(standing.size() > 0);
	}

	// Test de UC-032
	@Test
	public void positiveTestDos_findByConstructor() {
		List<ConstructorStanding> standing;
		String constructor;

		constructor = "alfa romeo";
		standing = this.contructorStandingService.findByConstructorAPI(constructor);

		assertNotNull(standing);
		assertTrue(standing.size() > 0);
	}

	// Test de UC-032
	@Test
	public void negativeTestUno_findByConstructor() {
		List<ConstructorStanding> standing;
		String constructor;

		constructor = "Escuderia desconocida";
		standing = this.contructorStandingService.findByConstructorAPI(constructor);

		assertNotNull(standing);
		assertTrue(standing.size() == 0);
	}

}
