package com.fone.api.FOne.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Autowired
	private UtilityService utilityService;
	
	// Test suite -------------------	
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

		// No existen datos de la temporada 2020
		season = "2020";
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

		season = "2020";
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
		Page<Race> races;
		String season;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(5, 0);
		
		season = "1977";
		races = this.raceService.findBySeasonAPI(season, pageable);

		assertNotNull(season);
		assertTrue(races.hasContent());
	}

	// Test de UC-016
	@Test
	public void negativeTest_findSeason() {
		Page<Race> races;
		String season;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(5, 0);
		season = "3000";
		races = this.raceService.findBySeasonAPI(season, pageable);

		assertNotNull(season);
		assertTrue(!races.hasContent());
	}

	// Test de UC-017
	@Test
	public void positiveTestUno_findByCircuit() {
		Page<Race> races;
		String circuitName;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		circuitName = "Spa";
		races = this.raceService.findByCircuitAPI(circuitName, pageable);

		assertNotNull(races);
		assertTrue(races.hasContent());
	}

	// Test de UC-017
	@Test
	public void positiveTestDos_findByCircuit() {
		Page<Race> races;
		String circuitName;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		circuitName = "adelaide";
		races = this.raceService.findByCircuitAPI(circuitName, pageable);

		assertNotNull(races);
		assertTrue(races.hasContent());
	}

	// Test de UC-017
	@Test
	public void negativeTest_findByCircuit() {
		Page<Race> races;
		String circuitName;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(10, 0);
		circuitName = "Circuito incorrecto";
		races = this.raceService.findByCircuitAPI(circuitName, pageable);

		assertNotNull(races);
		assertTrue(!races.hasContent());
	}

	// Test de UC-018
	@Test
	public void positiveTestUno_findRacesByDriver() {
		Page<Race> races;
		String driver;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		driver = "Carlos Sainz";
		races = this.raceService.findRacesByDriverAPI(driver, pageable);

		assertNotNull(races);
		assertTrue(races.hasContent());
	}

	// Test de UC-018
	@Test
	public void positiveTestDos_findRacesByDriver() {
		Page<Race> races;
		String driver;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		driver = "Lewis Hamilton";
		races = this.raceService.findRacesByDriverAPI(driver, pageable);

		assertNotNull(races);
		assertTrue(races.hasContent());
	}

	// Test de UC-018
	@Test
	public void negativeTest_findRacesByDriver() {
		Page<Race> races;
		String driver;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		driver = "Piloto desconocido";
		races = this.raceService.findRacesByDriverAPI(driver, pageable);

		assertNotNull(races);
		assertTrue(!races.hasContent());
	}

	// Test de UC-019
	@Test
	public void positiveTestUno_findRacesByConstructor() {
		Page<Race> races;
		String constructor;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		constructor = "Renault";
		races = this.raceService.findRacesByConstructorAPI(constructor, pageable);

		assertNotNull(races);
		assertTrue(races.hasContent());
	}

	// Test de UC-019
	@Test
	public void positiveTestDos_findRacesByConstructor() {
		Page<Race> races;
		String constructor;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		constructor = "Vanwall";
		races = this.raceService.findRacesByConstructorAPI(constructor, pageable);

		assertNotNull(races);
		assertTrue(races.hasContent());
	}

	// Test de UC-019
	@Test
	public void negativeTest_findRacesByConstructor() {
		Page<Race> races;
		String constructor;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		constructor = "Escuderia incorrecta";
		races = this.raceService.findRacesByConstructorAPI(constructor, pageable);

		assertNotNull(races);
		assertTrue(!races.hasContent());
	}

	// Test de UC-020
	@Test
	public void positiveTestUno_findRacesByDriverAndSeason() {
		Page<Race> races;
		String season, driver;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		season = "2018";
		driver = "Carlos Sainz";

		races = this.raceService.findRacesByDriverAndSeasonAPI(driver, season, pageable);

		assertNotNull(races);
		assertTrue(races.hasContent());
	}

	// Test de UC-020
	@Test
	public void positiveTestDos_findRacesByDriverAndSeason() {
		Page<Race> races;
		String season, driver;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		season = "2013";
		driver = "vettel";

		races = this.raceService.findRacesByDriverAndSeasonAPI(driver, season, pageable);

		assertNotNull(races);
		assertTrue(races.hasContent());
	}

	// Test de UC-020
	@Test
	public void negativeTest_findRacesByDriverAndSeason() {
		Page<Race> races;
		String season, driver;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		season = "1951";
		driver = "Carlos Sainz";

		races = this.raceService.findRacesByDriverAndSeasonAPI(driver, season, pageable);

		assertNotNull(races);
		assertTrue(!races.hasContent());
	}

	// Test de UC-021
	@Test
	public void positiveTestUno_findRacesByConstructorAndSeason() {
		Page<Race> races;
		String season, constructor;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		season = "2011";
		constructor = "Ferrari";

		races = this.raceService.findRacesByConstructorAndSeasonAPI(constructor, season, pageable);

		assertNotNull(races);
		assertTrue(races.hasContent());
	}

	// Test de UC-021
	@Test
	public void positiveTestDos_findRacesByConstructorAndSeason() {
		Page<Race> races;
		String season, constructor;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		season = "2015";
		constructor = "mercedes";

		races = this.raceService.findRacesByConstructorAndSeasonAPI(constructor, season, pageable);

		assertNotNull(races);
		assertTrue(races.hasContent());
	}

	// Test de UC-021
	@Test
	public void negativeTest_findRacesByConstructorAndSeason() {
		Page<Race> races;
		String season, constructor;
		Pageable pageable;

		pageable = this.utilityService.getPageable(10, 0);
		season = "1950";
		constructor = "brawn";

		races = this.raceService.findRacesByConstructorAndSeasonAPI(constructor, season, pageable);

		assertNotNull(races);
		assertTrue(!races.hasContent());
	}

	// Test de UC-022
	@Test
	public void positiveTestUno_findRacesBySeasonAndEvent() {
		Page<Race> race;
		String season, event;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(5, 0);
		
		season = "1980";
		event = "Dutch Grand Prix";

		race = this.raceService.findRaceBySeasonAndEventAPI(event, season, pageable);

		assertNotNull(race);
		assertTrue(race.hasContent());
	}

	// Test de UC-022
	@Test
	public void positiveTestDos_findRacesBySeasonAndEvent() {
		Page<Race> race;
		String season, event;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(5, 0);

		season = "1980";
		event = "United States";

		race = this.raceService.findRaceBySeasonAndEventAPI(event, season, pageable);

		assertNotNull(race);
		assertTrue(race.hasContent());
	}

	// Test de UC-022
	@Test
	public void positiveTestTres_findRacesBySeasonAndEvent() {
		Page<Race> race;
		String season, event;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(5, 0);
		
		season = "1980";
		event = "british grand prix";

		race = this.raceService.findRaceBySeasonAndEventAPI(event, season, pageable);

		assertNotNull(race);
		assertTrue(race.hasContent());
	}

	// Test de UC-022
	@Test
	public void negativeTestUno_findRacesBySeasonAndEvent() {
		Page<Race> race;
		String season, event;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(5, 0);
		
		season = "1980";
		event = "Evento incorrecto";

		race = this.raceService.findRaceBySeasonAndEventAPI(event, season, pageable);

		assertNotNull(race);
		assertTrue(!race.hasContent());
	}

	// Test de UC-022: Para el evento solicitado no existe una carrera de la
	// temporada 1950
	@Test
	public void negativeTestDos_findRacesBySeasonAndEvent() {
		Page<Race> race;
		String season, event;
		Pageable pageable;
		
		pageable = this.utilityService.getPageable(5, 0);
		
		season = "1950";
		event = "Dutch Grand Prix";

		race = this.raceService.findRaceBySeasonAndEventAPI(event, season, pageable);

		assertNotNull(race);
		assertTrue(!race.hasContent());
	}

}
