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

import com.fone.api.FOne.domain.DriverStanding;
import com.fone.api.FOne.services.utilities.AbstractTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DriverStandingServiceTest extends AbstractTest {

	@Autowired
	private DriverStandingService driverStandingService;
	
	@Autowired
	private UtilityService utilityService;
	
	@Test
	public void test_findGetSeasons() {
		Map<String, String> mapa;
		
		mapa = this.driverStandingService.getSeasons(1950, 1960);
		
		assertNotNull(mapa);
		assertTrue(!mapa.isEmpty());
	}
	
	@Test
	public void test_getDriverStanding() {
		String season;
		String url;
		Document document;
		List<DriverStanding> driversStanding;
		
		season = "1950";
		url = "https://www.formula1.com/en/results.html/1950/drivers.html";
		
		document = this.utilityService.getDocument(url);
		
		driversStanding = this.driverStandingService.getDriversStanding(document, season);
		
		assertNotNull(driversStanding);
		assertTrue(driversStanding.size() > 0);
		
	}
	
}
