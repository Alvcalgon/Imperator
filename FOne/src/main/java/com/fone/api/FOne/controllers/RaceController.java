package com.fone.api.FOne.controllers;

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
	
	
	@GetMapping(value = "/list{id}")
	public Race findOne(@PathVariable String id) {
		return this.raceService.findOneAPI(id);
	}
	
}
