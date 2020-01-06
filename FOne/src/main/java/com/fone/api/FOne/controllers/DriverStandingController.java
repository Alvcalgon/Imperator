package com.fone.api.FOne.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fone.api.FOne.domain.DriverStanding;
import com.fone.api.FOne.services.DriverStandingService;

@RestController
@RequestMapping("/driver-standing")
public class DriverStandingController {

	@Autowired
	private DriverStandingService driverStandingService;

	public DriverStandingController() {
		super();
	}

	// UC-027
	@GetMapping(value = "/list/season/{season}")
	public List<DriverStanding> findBySeasonAPI(@PathVariable(required = true) String season) {
		List<DriverStanding> results;

		try {
			results = this.driverStandingService.findBySeasonAPI(season);
		} catch (Exception e) {
			results = new ArrayList<DriverStanding>();
		}

		return results;
	}

	// UC-028
	@GetMapping(value = "/list/position/{position}")
	public List<DriverStanding> findByPositionAPI(@PathVariable(required = true) String position) {
		List<DriverStanding> results;

		try {
			results = this.driverStandingService.findByPositionAPI(position);
		} catch (Exception e) {
			results = new ArrayList<DriverStanding>();
		}

		return results;
	}

	// UC-029
	@GetMapping(value = "/list/driver/{driver}")
	public List<DriverStanding> findByDriverAPI(@PathVariable(required = true) String driver) {
		List<DriverStanding> results;

		try {
			results = this.driverStandingService.findByDriverAPI(driver);
		} catch (Exception e) {
			results = new ArrayList<DriverStanding>();
		}

		return results;
	}

}
