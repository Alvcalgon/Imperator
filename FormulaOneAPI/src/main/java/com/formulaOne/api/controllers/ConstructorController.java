package com.formulaOne.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formulaOne.api.domain.Constructor;
import com.formulaOne.api.services.ConstructorService;

@RestController
@RequestMapping("/constructor")
public class ConstructorController {

	@Autowired
	private ConstructorService constructorService;
	
	
	public ConstructorController() {
		super();
	}
	
	
	@GetMapping(value = "/list")
	public List<Constructor> findAll() {
		return this.constructorService.findAll();
	}
	
	@GetMapping(value = "/list/{id}")
	public Constructor findOne(@PathVariable String id) {
		return this.constructorService.findOne(id);
	}
	
}
