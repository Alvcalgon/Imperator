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
	
	@Autowired
	private UtilityService utilityService;
	
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
		race1 = this.raceService.findOne("5dda81e0375a5930c47b5e11");
	
		Race race2; // European - 1997
		race2 = this.raceService.findOne("5dda8204375a5930c47b5fed");
	
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
	
	
//	@Test
//	public void test_findOne() {
//		Race race1; // ¿ Autralian ?
//		race1 = this.raceService.findOne("5ddc08e1375a5937909971aa");
//		
//		assertNotNull(race1);
//		assertTrue(race1.getFastestLap() == null);
//		
//		Race race2; // ¿ Malaysian ?
//		race2 = this.raceService.findOne("5ddc08e6375a5937909971ed");
//		assertNotNull(race2);
//		
//		Race race3; // ¿ Chinese ?
//		race3 = this.raceService.findOne("5ddc08ec375a593790997230");
//		
//		Race race4; // ¿ Bahrain ?
//		race4 = this.raceService.findOne("5ddc08f1375a593790997273");
//		
//		Race race5; // ¿ Spanish ?
//		race5 = this.raceService.findOne("5ddc08f6375a5937909972b6");
//		
//		Race race6; // ¿ Monaco ?
//		race6 = this.raceService.findOne("5ddc08fc375a5937909972f9");
//		
//		Race race7; // ¿ Canadian ?
//		race7 = this.raceService.findOne("5ddc0901375a59379099733c");
//		
//		Race race8; // ¿ British ?
//		race8 = this.raceService.findOne("5ddc0906375a59379099737f");
//		
//		Race race9; // ¿ German ?
//		race9 = this.raceService.findOne("5ddc090c375a5937909973c2");
//		
//		Race race10; // ¿ Hungarian ?
//		race10 = this.raceService.findOne("5ddc0912375a593790997405");
//		
//		Race race11; // ¿ Belgian ?
//		race11 = this.raceService.findOne("5ddc0917375a593790997448");
//		
//		Race race12; // ¿ Italian ?
//		race12 = this.raceService.findOne("5ddc091d375a59379099748b");
//		
//		Race race13; // ¿ Singapore ?
//		race13 = this.raceService.findOne("5ddc0923375a5937909974ce");
//		
//		Race race14; // ¿ Korean ?
//		race14 = this.raceService.findOne("5ddc0928375a593790997511");
//		
//		Race race15; // ¿ Japanese ?
//		race15 = this.raceService.findOne("5ddc092d375a593790997554");
//		
//		Race race16; // ¿ Indian ?
//		race16 = this.raceService.findOne("5ddc0933375a593790997597");
//		
//		Race race17; // ¿ Abu Dhabi ?
//		race17 = this.raceService.findOne("5ddc0938375a5937909975da");
//		
//		Race race18; // ¿ United States ?
//		race18 = this.raceService.findOne("5ddc093e375a59379099761d");
//		
//		Race race19; // ¿ Brazilian ?
//		race19 = this.raceService.findOne("5ddc0943375a593790997660");
//		
////		Race race20;
////		race20 = this.raceService.findOne("5dda7564375a5920c0a7bd3d");
//		
////		Race race21;
////		race21 = this.raceService.findOne("5ddc07a6375a5937909964ce");
//		
//	}
	
//	@Test
//	public void test_getAllSeasons() {
//		Map<String, String> seasons;
//		
//		seasons = this.raceService.getAllSeasons();
//		
//		assertTrue(!seasons.isEmpty());
//		assertTrue(seasons.size() == 70);	
//	}
	
//	@Test
//	public void test_loadRacesBySeason() {
//		String enlace;
//		String season;
//		Document doc;
//		List<Race> races;
//		
//		enlace = "https://www.f1-fansite.com/f1-results/1950-f1-championship-standings/";
//		season = "1950";
//		
//		doc = this.utilityService.getDocument(enlace);
//		
//		this.raceService.loadRacesBySeason(doc, season);
//		
//		races = this.raceService.findBySeason(season);
//		
//		assertNotNull(races);
//		assertTrue(races.size() > 0);
//	}
	
}
