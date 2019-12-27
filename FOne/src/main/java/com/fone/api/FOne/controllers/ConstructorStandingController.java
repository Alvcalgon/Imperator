package com.fone.api.FOne.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fone.api.FOne.domain.ConstructorStanding;
import com.fone.api.FOne.services.ConstructorStandingService;

@RestController
@RequestMapping("/constructorStanding")
public class ConstructorStandingController {

	@Autowired
	private ConstructorStandingService constructorStandingService;
	
	public ConstructorStandingController() {
		super();
	}
	
	@GetMapping(value = "/list")
	public List<ConstructorStanding> findAll() {
		return this.constructorStandingService.findAll();
	}
	
	@GetMapping(value = "/list/{id}")
	public ConstructorStanding findOne(@PathVariable String id) {
		return this.constructorStandingService.findOne(id);
	}
	
}
