package com.fone.api.FOne.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fone.api.FOne.domain.Race;
import com.fone.api.FOne.services.utilities.AbstractTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RaceServiceTest extends AbstractTest {

	@Autowired
	private RaceService raceService;
	
	
	// Test suite -------------------

	@Test
	public void test_findByCircuit() {
		List<Race> races;
		
		// Istanbul park
		races = this.raceService.findByCircuit("5dc688fc42478c1928f0605c");
		assertNotNull(races);
		assertTrue(races.size() >= 0);
				
	}
	
	@Test
	public void test_findOne() {
		Race race1; // European - 1997
		race1 = this.raceService.findOneAPI("5dda81e0375a5930c47b5e11");
	
		Race race2; // European - 1997
		race2 = this.raceService.findOneAPI("5dda8204375a5930c47b5fed");
	
		assertNotNull(race1);
		assertNotNull(race2);
	}
	
	@Test
	public void test_findBySeason() {
		List<Race> races;
		
		races = this.raceService.findBySeason("2017");
		assertNotNull(races);
		
		for (Race r: races) {
			System.out.println(r.getEvent() + ": " + r.getRaceDate());
		}
		
	}
	
}
