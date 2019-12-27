package com.fone.api.FOne.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fone.api.FOne.domain.Driver;
import com.fone.api.FOne.services.DriverService;

@RestController
@RequestMapping("/driver")
public class DriverController {

	@Autowired
	private DriverService driverService;
	
	public DriverController() {
		super();
	}
	
	@GetMapping(value = "/list")
	public List<Driver> findAll() {
		return this.driverService.findAllAPI();
	}
	
	@GetMapping(value = "/list/{id}")
	public Driver findOne(@PathVariable String id) {
		return this.driverService.findOneAPI(id);
	}
	
}
