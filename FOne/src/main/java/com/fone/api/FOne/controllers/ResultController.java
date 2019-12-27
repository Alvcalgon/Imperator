package com.fone.api.FOne.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fone.api.FOne.domain.Result;
import com.fone.api.FOne.services.ResultService;

@RestController
@RequestMapping("/result")
public class ResultController {

	@Autowired
	private ResultService resultService;
	
	public ResultController() {
		super();
	}
	
	@GetMapping(value = "/list")
	public List<Result> findAll() {
		return this.resultService.findAll();
	}
	
	@GetMapping(value = "/list/{id}")
	public Result findOne(@PathVariable String id) {
		return this.resultService.findOne(id);
	}
	
}
