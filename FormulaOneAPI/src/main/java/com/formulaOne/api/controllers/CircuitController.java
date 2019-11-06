package com.formulaOne.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formulaOne.api.domain.Circuit;
import com.formulaOne.api.services.CircuitService;

@RestController
@RequestMapping("/circuit")
public class CircuitController {

	@Autowired
	private CircuitService circuitService;
	
	
	public CircuitController() {
		super();
	}
	
	@GetMapping(value = "/list")
	public List<Circuit> findAll() {
		return this.circuitService.findAll();
	}
	
	@GetMapping(value = "/list/{id}")
	public Circuit findOne(@PathVariable String id) {
		return this.circuitService.findOne(id);
	}
	
}
