package com.fone.api.FOne.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fone.api.FOne.domain.Circuit;
import com.fone.api.FOne.services.CircuitService;
import com.fone.api.FOne.services.RaceService;

@RestController
@RequestMapping("/circuit")
public class CircuitController {

	@Autowired
	private CircuitService circuitService;
	
	@Autowired
	private RaceService raceService;
	
	public CircuitController() {
		super();
	}
	
	// UC-011
	@GetMapping(value = "/list")
	public List<Circuit> findAllAPI() {
		List<Circuit> results;
		
		try {
			results = this.circuitService.findAllAPI();
		} catch (Exception e) {
			results = new ArrayList<Circuit>();
		}
		
		return results;
	}
	
	// UC-012
	@GetMapping(value = "/list/type/{type}")
	public List<Circuit> findByType(@PathVariable(required = true) String type) {
		List<Circuit> results;
		
		try {
			results = this.circuitService.findByTypeAPI(type);
		} catch (Exception e) {
			results = this.findAllAPI();
		}
		
		return results;
	}
	
	// UC-013
	@GetMapping(value = "/list/location/{location}")
	public List<Circuit> findByLocation(@PathVariable(required = true) String location) {
		List<Circuit> results;
		
		try {
			results = this.circuitService.findByLocationAPI(location);
		} catch (Exception e) {
			results = this.findAllAPI();
		}
		
		return results;
	}
	
	// UC-014
	@GetMapping(value = "/list/season/{season}")
	public List<Circuit> findBySeasonAPI(@PathVariable(required = true) String season) {
		List<Circuit> results;
		
		try {
			results = this.raceService.findCircuitsBySeasonAPI(season);
		} catch (Exception e) {
			results = this.findAllAPI();
		}
		
		return results;
	}
	
	// UC-015
	@GetMapping(value = "/list/name/{name}")
	public List<Circuit> findByNameAPI(@PathVariable(required = true) String name) {
		List<Circuit> results;
		
		try {
			results = this.circuitService.findByNameAPI(name);
		} catch (Exception e) {
			results = this.findAllAPI();
		}
		
		return results;
	}
	
}
