package com.fone.api.FOne.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		return this.driverService.findAll();
	}
	
	@GetMapping(value = "/list/{id}")
	public Driver findOne(@PathVariable String id) {
		return this.driverService.findOne(id);
	}
	
	@PostMapping(value = "/save")
	public Driver save(@RequestBody Driver driver) {
		return this.driverService.save(driver);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public String delete(@PathVariable String id) {
		Driver driver;
		
		driver = this.driverService.findOne(id);
		
		this.driverService.delete(driver);
		
		return "Driver borrado correctamente";
	}
	
}
