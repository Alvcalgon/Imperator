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

import com.fone.api.FOne.domain.Constructor;
import com.fone.api.FOne.services.ConstructorService;
import com.fone.api.FOne.services.RaceService;
import com.fone.api.FOne.services.ResultService;

@RestController
@RequestMapping("/constructor")
public class ConstructorController {

	@Autowired
	private ConstructorService constructorService;
	
	@Autowired
	private RaceService raceService;

	@Autowired
	private ResultService resultService;
	
	public ConstructorController() {
		super();
	}
	
	// UC-006
	@GetMapping(value = "/list")
	public List<Constructor> findAllAPI() {
		List<Constructor> results;
		
		try {
			results = this.constructorService.findAllAPI();
		} catch (Exception e) {
			results = new ArrayList<Constructor>();
		}
		
		return results;
	}
	
	// UC-007
	@GetMapping(value = "/list/country/{country}")
	public List<Constructor> findByCountryAPI(@PathVariable(required = true) String country) {
		List<Constructor> results;

		try {
			results = this.constructorService.findByCountryAPI(country);
		} catch (Exception e) {
			results = new ArrayList<Constructor>();
		}

		return results;
	}
	
	// UC-008
	@GetMapping(value = "/list/season/{season}")
	public Set<Constructor> findConstructorsBySeasonAPI(@PathVariable(required = true) String season) {
		Set<Constructor> results;

		try {
			results = this.raceService.findConstructorsBySeasonAPI(season);
		} catch (Exception e) {
			results = new HashSet<Constructor>();
		}

		return results;
	}
	
	// UC-009
	@GetMapping(value = "/list/driver/{driver}")
	public Set<Constructor> findConstructorsByDriverAPI(@PathVariable(required = true) String driver) {
		Set<Constructor> results;
		
		try {
			results = this.resultService.findConstructorsByDriverAPI(driver);
		} catch (Exception e) {
			results = new HashSet<Constructor>();
		}
		
		return results;
	}
	
	// UC-010
	@GetMapping(value = "/list/name/{name}")
	public List<Constructor> findByNameAPI(@PathVariable(required = true) String name) {
		List<Constructor> results;
		
		try {
			results = this.constructorService.findByNameAPI(name);
		} catch (Exception e) {
			results = new ArrayList<Constructor>();
		}
		
		return results;
	}
	
}
