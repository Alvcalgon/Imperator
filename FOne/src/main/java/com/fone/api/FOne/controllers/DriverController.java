package com.fone.api.FOne.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fone.api.FOne.domain.Driver;
import com.fone.api.FOne.services.DriverService;
import com.fone.api.FOne.services.RaceService;
import com.fone.api.FOne.services.ResultService;

@RestController
@RequestMapping("/driver")
public class DriverController {

	@Autowired
	private DriverService driverService;

	@Autowired
	private RaceService raceService;

	@Autowired
	private ResultService resultService;

	public DriverController() {
		super();
	}

	// UC-001
	@GetMapping(value = "/list")
	public List<Driver> findAllAPI() {
		List<Driver> results;

		try {
			results = this.driverService.findAllAPI();
		} catch (Exception e) {
			results = new ArrayList<Driver>();
		}

		return results;

	}

	// UC-002
	@GetMapping(value = "/list/country/{country}")
	public List<Driver> findByCountryAPI(@PathVariable(required = true) String country) {
		List<Driver> results;

		try {
			results = this.driverService.findByCountryAPI(country);
		} catch (Exception e) {
			results = new ArrayList<Driver>();
		}

		return results;
	}

	// UC-003
	@GetMapping(value = "/list/season/{season}")
	public Set<Driver> findDriversBySeasonAPI(@PathVariable(required = true) String season) {
		Set<Driver> results;

		try {
			results = this.raceService.findDriversBySeasonAPI(season);
		} catch (Exception e) {
			results = new HashSet<Driver>();
		}

		return results;
	}

	// UC-004
	@GetMapping(value = "/list/constructor/{constructor}")
	public Set<Driver> findDriversByConstructorAPI(@PathVariable(required = true) String constructor) {
		Set<Driver> results;

		try {
			results = this.resultService.findDriversByConstructorAPI(constructor);
		} catch (Exception e) {
			results = new HashSet<Driver>();
		}

		return results;
	}

	// UC-005
	@GetMapping(value = "/list/fullname/{fullname}")
	public List<Driver> findByFullnameAPI(@PathVariable(required = true) String fullname) {
		List<Driver> results;

		try {
			results = this.driverService.findByFullnameAPI(fullname);
		} catch (Exception e) {
			results = new ArrayList<Driver>();
		}

		return results;
	}

}
