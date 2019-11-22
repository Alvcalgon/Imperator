package com.fone.api.FOne.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
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
	
	@Autowired
	private UtilityService utilityService;
	
	// Test suite -------------------
	@Test
	public void test_getAllSeasons() {
		Map<String, String> seasons;
		
		seasons = this.raceService.getAllSeasons();
		
		assertTrue(!seasons.isEmpty());
		assertTrue(seasons.size() == 70);
	}
	
	@Test
	public void test_loadRacesBySeason() {
		String enlace;
		String season;
		Document doc;
		List<Race> races;
		
		enlace = "https://www.f1-fansite.com/f1-results/1950-f1-championship-standings/";
		season = "1950";
		
		doc = this.utilityService.getDocument(enlace);
		
		this.raceService.loadRacesBySeason(doc, season);
		
		races = this.raceService.findBySeason(season);
		
		assertNotNull(races);
		assertTrue(races.size() > 0);
	}
	
}
