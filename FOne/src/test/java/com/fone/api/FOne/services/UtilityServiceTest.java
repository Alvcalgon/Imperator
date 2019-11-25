package com.fone.api.FOne.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fone.api.FOne.services.utilities.AbstractTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilityServiceTest extends AbstractTest {

	// Services under testing -----------
		@Autowired
		private UtilityService utilityService;
		
		// Suite test -----------------------
		@Test
		public void test_getUserAgent() {
			String userAgent;
			
			userAgent = this.utilityService.getUserAgent();
			
			assertNotNull(userAgent);
			assertTrue(userAgent.equals("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:70.0) Gecko/20100101 Firefox/70.0") 
					|| userAgent.equals("Chrome/77.0.3865.120 Safari/537.36") 
					|| userAgent.equals("Edge/18.18362"));
		}
		
		@Test
		public void test_getDate() {
			String str_date;
			Date date;
			
			str_date = "Jan 7th 1985 - 34 years old";
			date = this.utilityService.getDate(str_date);
			
			assertNotNull(date);
		}
		
		@Test
		public void testUno_getDateByParameters() {
			String season, monthDay;
			Date date;
			
			season = "1950";
			monthDay = "May 13";
			
			date = this.utilityService.getDateByParameters(season, monthDay);
			
			assertNotNull(date);
		}
	
		@Test
		public void testDos_getDateByParameters() {
			String season, monthDay;
			Date date;
			
			season = "1950";
			monthDay = "13 May";
			
			date = this.utilityService.getDateByParameters(season, monthDay);
			
			assertNotNull(date);
		}
		
}
