package com.fone.api.FOne.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fone.api.FOne.domain.ConstructorStanding;
import com.fone.api.FOne.services.ConstructorStandingService;

@RestController
@RequestMapping("/constructor-standing")
public class ConstructorStandingController {

	@Autowired
	private ConstructorStandingService constructorStandingService;

	public ConstructorStandingController() {
		super();
	}

	// UC-030
	@GetMapping(value = "/list/season/{season}")
	public List<ConstructorStanding> findBySeasonAPI(@PathVariable(required = true) String season) {
		List<ConstructorStanding> results;

		try {
			results = this.constructorStandingService.findBySeasonAPI(season);
		} catch (Exception e) {
			results = new ArrayList<ConstructorStanding>();
		}

		return results;
	}

	// UC-031
	@GetMapping(value = "/list/position/{position}")
	public List<ConstructorStanding> findByPositionAPI(@PathVariable(required = true) String position) {
		List<ConstructorStanding> results;

		try {
			results = this.constructorStandingService.findByPositionAPI(position);
		} catch (Exception e) {
			results = new ArrayList<ConstructorStanding>();
		}

		return results;
	}

	// UC-032
	@GetMapping(value = "/list/constructor/{constructor}")
	public List<ConstructorStanding> findByConstructorAPI(@PathVariable(required = true) String constructor) {
		List<ConstructorStanding> results;

		try {
			results = this.constructorStandingService.findByConstructorAPI(constructor);
		} catch (Exception e) {
			results = new ArrayList<ConstructorStanding>();
		}

		return results;
	}

}
