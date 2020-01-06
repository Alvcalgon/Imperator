package com.fone.api.FOne.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fone.api.FOne.domain.Circuit;
import com.fone.api.FOne.domain.Constructor;
import com.fone.api.FOne.domain.Driver;
import com.fone.api.FOne.domain.Race;
import com.fone.api.FOne.services.utilities.AbstractTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RaceServiceTest extends AbstractTest {

	// Servicio bajo pruebas unitarias
	@Autowired
	private RaceService raceService;

	// Test suite -------------------

	// Test de UC-003
	@Test
	public void positiveTest_findDriversBySeason() {
		Set<Driver> drivers;
		String season;

		season = "2018";
		drivers = this.raceService.findDriversBySeasonAPI(season);

		assertNotNull(drivers);
		assertTrue(drivers.size() > 0);
	}

	// Test de UC-003
	@Test
	public void negativeTest_findDriversBySeason() {
		Set<Driver> drivers;
		String season;

		// No existen datos de la temporada 2019
		season = "2019";
		drivers = this.raceService.findDriversBySeasonAPI(season);

		assertNotNull(drivers);
		assertTrue(drivers.size() == 0);
	}

	// Test de UC-008
	@Test
	public void positiveTest_findConstructorsBySeason() {
		Set<Constructor> constructors;
		String season;

		season = "2018";
		constructors = this.raceService.findConstructorsBySeasonAPI(season);

		assertNotNull(constructors);
		assertTrue(constructors.size() > 0);
	}

	// Test de UC-008
	@Test
	public void negativeTest_findConstructorsBySeason() {
		Set<Constructor> constructors;
		String season;

		season = "2019";
		constructors = this.raceService.findConstructorsBySeasonAPI(season);

		assertNotNull(constructors);
		assertTrue(constructors.size() == 0);
	}

	// Test de UC-014
	@Test
	public void positiveTest_findCircuitsBySeason() {
		List<Circuit> circuits;
		String season;

		season = "2000";
		circuits = this.raceService.findCircuitsBySeasonAPI(season);

		assertNotNull(circuits);
		assertTrue(circuits.size() > 0);
	}

	// Test de UC-014
	@Test
	public void negativeTest_findCircuitsBySeason() {
		List<Circuit> circuits;
		String season;

		season = "3000";
		circuits = this.raceService.findCircuitsBySeasonAPI(season);

		assertNotNull(circuits);
		assertTrue(circuits.size() == 0);
	}

	// Test de UC-016
	@Test
	public void positiveTest_findSeason() {
		List<Race> races;
		String season;

		season = "1977";
		races = this.raceService.findBySeasonAPI(season);

		assertNotNull(season);
		assertTrue(races.size() > 0);
	}

	// Test de UC-016
	@Test
	public void negativeTest_findSeason() {
		List<Race> races;
		String season;

		season = "3000";
		races = this.raceService.findBySeasonAPI(season);

		assertNotNull(season);
		assertTrue(races.size() == 0);
	}

	// Test de UC-017
	@Test
	public void positiveTestUno_findByCircuit() {
		List<Race> races;
		String circuitName;

		circuitName = "Spa";
		races = this.raceService.findByCircuitAPI(circuitName);

		assertNotNull(races);
		assertTrue(races.size() > 0);
	}

	// Test de UC-017
	@Test
	public void positiveTestDos_findByCircuit() {
		List<Race> races;
		String circuitName;

		circuitName = "adelaide";
		races = this.raceService.findByCircuitAPI(circuitName);

		assertNotNull(races);
		assertTrue(races.size() > 0);
	}

	// Test de UC-017
	@Test
	public void negativeTest_findByCircuit() {
		List<Race> races;
		String circuitName;

		circuitName = "Circuito incorrecto";
		races = this.raceService.findByCircuitAPI(circuitName);

		assertNotNull(races);
		assertTrue(races.size() == 0);
	}

	// Test de UC-018
	@Test
	public void positiveTestUno_findRacesByDriver() {
		List<Race> races;
		String driver;

		driver = "Carlos Sainz";
		races = this.raceService.findRacesByDriverAPI(driver);

		assertNotNull(races);
		assertTrue(races.size() > 0);
	}

	// Test de UC-018
	@Test
	public void positiveTestDos_findRacesByDriver() {
		List<Race> races;
		String driver;

		driver = "lewis hamilton";
		races = this.raceService.findRacesByDriverAPI(driver);

		assertNotNull(races);
		assertTrue(races.size() > 0);
	}

	// Test de UC-018
	@Test
	public void negativeTest_findRacesByDriver() {
		List<Race> races;
		String driver;

		driver = "Piloto desconocido";
		races = this.raceService.findRacesByDriverAPI(driver);

		assertNotNull(races);
		assertTrue(races.size() == 0);
	}

	// Test de UC-019
	@Test
	public void positiveTestUno_findRacesByConstructor() {
		List<Race> races;
		String constructor;

		constructor = "Renault";
		races = this.raceService.findRacesByConstructorAPI(constructor);

		assertNotNull(races);
		assertTrue(races.size() > 0);
	}

	// Test de UC-019
	@Test
	public void positiveTestDos_findRacesByConstructor() {
		List<Race> races;
		String constructor;

		constructor = "vanwall";
		races = this.raceService.findRacesByConstructorAPI(constructor);

		assertNotNull(races);
		assertTrue(races.size() > 0);
	}

	// Test de UC-019
	@Test
	public void negativeTest_findRacesByConstructor() {
		List<Race> races;
		String constructor;

		constructor = "Escuderia incorrecta";
		races = this.raceService.findRacesByConstructorAPI(constructor);

		assertNotNull(races);
		assertTrue(races.size() == 0);
	}

	// Test de UC-020
	@Test
	public void positiveTestUno_findRacesByDriverAndSeason() {
		List<Race> races;
		String season, driver;

		season = "2018";
		driver = "Carlos Sainz";

		races = this.raceService.findRacesByDriverAndSeasonAPI(driver, season);

		assertNotNull(races);
		assertTrue(races.size() > 0);
	}

	// Test de UC-020
	@Test
	public void positiveTestDos_findRacesByDriverAndSeason() {
		List<Race> races;
		String season, driver;

		season = "2013";
		driver = "vettel";

		races = this.raceService.findRacesByDriverAndSeasonAPI(driver, season);

		assertNotNull(races);
		assertTrue(races.size() > 0);
	}

	// Test de UC-020
	@Test
	public void negativeTest_findRacesByDriverAndSeason() {
		List<Race> races;
		String season, driver;

		season = "1951";
		driver = "Carlos Sainz";

		races = this.raceService.findRacesByDriverAndSeasonAPI(driver, season);

		assertNotNull(races);
		assertTrue(races.size() == 0);
	}

	// Test de UC-021
	@Test
	public void positiveTestUno_findRacesByConstructorAndSeason() {
		List<Race> races;
		String season, constructor;

		season = "2011";
		constructor = "Ferrari";

		races = this.raceService.findRacesByConstructorAndSeasonAPI(constructor, season);

		assertNotNull(races);
		assertTrue(races.size() > 0);
	}

	// Test de UC-021
	@Test
	public void positiveTestDos_findRacesByConstructorAndSeason() {
		List<Race> races;
		String season, constructor;

		season = "2015";
		constructor = "mercedes";

		races = this.raceService.findRacesByConstructorAndSeasonAPI(constructor, season);

		assertNotNull(races);
		assertTrue(races.size() > 0);
	}

	// Test de UC-021
	@Test
	public void negativeTest_findRacesByConstructorAndSeason() {
		List<Race> races;
		String season, constructor;

		season = "1950";
		constructor = "brawn";

		races = this.raceService.findRacesByConstructorAndSeasonAPI(constructor, season);

		assertNotNull(races);
		assertTrue(races.size() == 0);
	}

	// Test de UC-022
	@Test
	public void positiveTestUno_findRacesBySeasonAndEvent() {
		List<Race> race;
		String season, event;

		season = "1980";
		event = "Dutch Grand Prix";

		race = this.raceService.findRaceBySeasonAndEventAPI(event, season);

		assertNotNull(race);
		assertTrue(race.size() > 0);
	}

	// Test de UC-022
	@Test
	public void positiveTestDos_findRacesBySeasonAndEvent() {
		List<Race> race;
		String season, event;

		season = "1980";
		event = "us west";

		race = this.raceService.findRaceBySeasonAndEventAPI(event, season);

		assertNotNull(race);
		assertTrue(race.size() > 0);
	}

	// Test de UC-022
	@Test
	public void positiveTestTres_findRacesBySeasonAndEvent() {
		List<Race> race;
		String season, event;

		season = "1980";
		event = "british grand prix";

		race = this.raceService.findRaceBySeasonAndEventAPI(event, season);

		assertNotNull(race);
		assertTrue(race.size() > 0);
	}

	// Test de UC-022
	@Test
	public void negativeTestUno_findRacesBySeasonAndEvent() {
		List<Race> race;
		String season, event;

		season = "1980";
		event = "Evento incorrecto";

		race = this.raceService.findRaceBySeasonAndEventAPI(event, season);

		assertNotNull(race);
		assertTrue(race.size() == 0);
	}

	// Test de UC-022: Para el evento solicitado no existe una carrera de la
	// temporada 1950
	@Test
	public void negativeTestDos_findRacesBySeasonAndEvent() {
		List<Race> race;
		String season, event;

		season = "1950";
		event = "Dutch Grand Prix";

		race = this.raceService.findRaceBySeasonAndEventAPI(event, season);

		assertNotNull(race);
		assertTrue(race.size() == 0);
	}

}
