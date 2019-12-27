package com.fone.api.FOne.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fone.api.FOne.domain.DriverStanding;
import com.fone.api.FOne.services.DriverStandingService;

@RestController
@RequestMapping("/driverStanding")
public class DriverStandingController {

	@Autowired
	private DriverStandingService driverStandingService;
	
	public DriverStandingController() {
		super();
	}
	
	@GetMapping(value = "/list/{season}")
	public List<DriverStanding> findAll(@PathVariable String season) {
		return this.driverStandingService.findBySeason(season);
	}
	
	@GetMapping(value = "/list/{id}")
	public DriverStanding findOne(@PathVariable String id) {
		return this.driverStandingService.findOne(id);
	}
	
}
