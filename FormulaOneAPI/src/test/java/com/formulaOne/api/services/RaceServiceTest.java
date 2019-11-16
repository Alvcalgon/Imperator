package com.formulaOne.api.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.formulaOne.api.domain.Circuit;
import com.formulaOne.api.domain.FastestLap;
import com.formulaOne.api.domain.Race;
import com.formulaOne.api.domain.Result;
import com.formulaOne.api.services.utilities.AbstractTest;

@SpringBootTest
public class RaceServiceTest extends AbstractTest {

	// Service under testing --------------
	@Autowired
	private RaceService raceService;
	
	@Autowired
	private UtilityService utilityService;
	
	
	@Test
	@DisplayName("Lista completa de carreras")
	public void test_findAll() {
		List<Race> all;
		
		all = this.raceService.findAll();
		
		assertNotNull(all);
		assertTrue(all.size() > 0);
	}
	
	@Test
	@DisplayName("Carrera segun el id")
	public void test_findById() {
		Race race;
		FastestLap lap;
		Set<Result> results;
		Circuit circuit;
		String id;
		
		id = "5dcfee54d547e80b244cbc5f";
		race = this.raceService.findOne(id);
		
		circuit = race.getCircuit();
		lap = race.getFastestLap();
		results = race.getResults();
		
		assertNotNull(race);
		assertNotNull(circuit);
		assertNotNull(lap);
		assertNotNull(results);
	}
	
	
	@Test
	@DisplayName("Todas las carreras disputadas en un circuito")
	public void test_findByCircuit() {
		List<Race> races;
		String circuitId;
		
		circuitId = "5dc6890e42478c1928f0605f";
		races = this.raceService.findByCircuit(circuitId);
		System.out.println("Numero de carreras: " + races.size());
		
		assertNotNull(races);
	}
	
}
