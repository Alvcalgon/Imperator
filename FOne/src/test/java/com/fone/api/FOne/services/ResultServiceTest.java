package com.fone.api.FOne.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

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
	
	// Suite test --------------------------
	@Test
	public void positiveTest_findDriversByConstructorAPI() {
		String constructor;
		List<Result> results;
		
		constructor = "Ferrari";
		results = this.resultService.findDriversByConstructorAPI(constructor);
		
		assertNotNull(results);
		assertTrue(results.size() > 0);
	}
	
}
