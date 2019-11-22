package com.fone.api.FOne.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fone.api.FOne.domain.Result;
import com.fone.api.FOne.services.utilities.AbstractTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResultServiceTest extends AbstractTest {

	@Autowired
	private ResultService resultService;
	
	@Autowired
	private UtilityService utilityService;
	
	
	// Suite test --------------------------
	@Test
	public void test_loadResultsByRace() {
		String enlace;
		Document doc;
		List<Result> results;
		
		enlace = "https://www.f1-fansite.com/f1-result/results-1950-formula-1-grand-prix-of-great-britain/";
		doc = this.utilityService.getDocument(enlace);
		
		this.resultService.loadResultsByRace(doc);
		
		results = this.resultService.findAll();
		
		assertNotNull(results);
		assertTrue(results.size() > 0);
	}
	
}
