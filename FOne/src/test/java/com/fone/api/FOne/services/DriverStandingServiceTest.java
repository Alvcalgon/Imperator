package com.fone.api.FOne.services;

import static org.junit.Assert.assertNotNull;

import java.util.List;

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
	
	
	@Test
	public void test_getDriverStanding() {
		DriverStanding driverStanding;
		String driverStandingId;
		
		driverStandingId = "5df50306052230392c690eb8";
		driverStanding = this.driverStandingService.findOne(driverStandingId);
		
		assertNotNull(driverStanding);
		
	}
	
	@Test
	public void test_findBySeason() {
		List<DriverStanding> allSeason;
		String season = "1951";
		allSeason = this.driverStandingService.findBySeason(season);
		
		System.out.println("Driver Standing - " + season);
		
		for (DriverStanding ds: allSeason) {
			System.out.println(ds.toString());
		}
		
	}
	
}
