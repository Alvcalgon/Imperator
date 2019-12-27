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

import com.fone.api.FOne.domain.ConstructorStanding;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConstructorStandingServiceTest {

	// Servicio bajo testeo --------------
	@Autowired
	private ConstructorStandingService contructorStandingService;
	
	@Autowired
	private UtilityService utilityService;
	
	// Suite test
	@Test
	public void test_getConstructorStanding() {
		String url;
		String season;
		Document document;
		List<ConstructorStanding> constructorsStanding;
		
		season = "1958";
		url = "https://www.formula1.com/en/results.html/1958/team.html";

		document = this.utilityService.getDocument(url);
		
		constructorsStanding = this.contructorStandingService.getConstructorStanding(document, season);
		
		assertNotNull(constructorsStanding);
		assertTrue(constructorsStanding.size() > 0);
	}
	
}
