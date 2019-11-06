package com.formulaOne.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formulaOne.api.domain.Race;
import com.formulaOne.api.services.RaceService;

@RestController
@RequestMapping("/race")
public class RaceController {

	@Autowired
	private RaceService raceService;
	
	public RaceController() {
		super();
	}
	
	@GetMapping(value = "/list")
	public List<Race> findAll() {
		return this.raceService.findAll();
	}
	
	@GetMapping(value = "/list/{id}")
	public Race findOne(@PathVariable String id) {
		return this.raceService.findOne(id);
	}
	
}
