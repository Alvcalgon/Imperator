package com.fone.api.FOne.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fone.api.FOne.domain.Race;
import com.fone.api.FOne.services.RaceService;

@RestController
@RequestMapping("/race")
public class RaceController {

	@Autowired
	private RaceService raceService;

	public RaceController() {
		super();
	}

	// UC-016
	@GetMapping(value = "/list/season/{season}")
	public List<Race> findBySeasonAPI(@PathVariable(required = true) String season) {
		List<Race> results;

		try {
			results = this.raceService.findBySeasonAPI(season);
		} catch (Exception e) {
			results = new ArrayList<Race>();
		}

		return results;
	}

	// UC-017
	@GetMapping(value = "/list/circuit/{circuit}")
	public List<Race> findByCircuitAPI(@PathVariable(required = true) String circuit) {
		List<Race> results;

		try {
			results = this.raceService.findByCircuitAPI(circuit);
		} catch (Exception e) {
			results = new ArrayList<Race>();
		}

		return results;
	}

	// UC-018
	@GetMapping(value = "/list/driver/{driver}")
	public List<Race> findByDriverAPI(@PathVariable(required = true) String driver) {
		List<Race> results;

		try {
			results = this.raceService.findRacesByDriverAPI(driver);
		} catch (Exception e) {
			results = new ArrayList<Race>();
		}

		return results;
	}

	// UC-019
	@GetMapping(value = "/list/constructor/{constructor}")
	public List<Race> findByConstructorAPI(@PathVariable(required = true) String constructor) {
		List<Race> results;

		try {
			results = this.raceService.findRacesByConstructorAPI(constructor);
		} catch (Exception e) {
			results = new ArrayList<Race>();
		}

		return results;
	}

	// UC-020
	@GetMapping(value = "/list/driver/{driver}/season/{season}")
	public List<Race> findByDriverAndSeasonAPI(@PathVariable(required = true) String driver,
											   @PathVariable(required = true) String season) {
		List<Race> results;

		try {
			results = this.raceService.findRacesByDriverAndSeasonAPI(driver, season);
		} catch (Exception e) {
			results = new ArrayList<Race>();
		}

		return results;
	}
	
	// UC-021
	@GetMapping(value = "/list/constructor/{constructor}/season/{season}")
	public List<Race> findByConstructorAndSeasonAPI(@PathVariable(required = true) String constructor,
													@PathVariable(required = true) String season) {
		List<Race> results;

		try {
			results = this.raceService.findRacesByConstructorAndSeasonAPI(constructor, season);
		} catch (Exception e) {
			results = new ArrayList<Race>();
		}

		return results;
	}
	
	// UC-022
	@GetMapping(value = "/list/season/{season}/event/{event}")
	public List<Race> findBySeasonAndEventAPI(@PathVariable(required = true) String season,
									  @PathVariable(required = true) String event) {
		List<Race> results;

		try {
			results = this.raceService.findRaceBySeasonAndEventAPI(event, season);
		} catch (Exception e) {
			results = new ArrayList<Race>();
		}

		return results;
	}
	
}
