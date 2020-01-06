package com.fone.api.FOne.controllers;

import java.util.ArrayList;
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

	// UC-023
	@GetMapping(value = "/list/driver/{driver}/position/{position}")
	public List<Result> findResultsByPositionAndDriverAPI(@PathVariable(required = true) String driver,
														  @PathVariable(required = true) String position) {
		List<Result> results;

		try {
			results = this.resultService.findResultsByPositionAndDriverAPI(driver, position);
		} catch (Exception e) {
			results = new ArrayList<Result>();
		}

		return results;
	}

	// UC-024
	@GetMapping(value = "/list/driver/{driver}/grid/{grid}")
	public List<Result> findResultsByGridAndDriverAPI(@PathVariable(required = true) String driver,
													  @PathVariable(required = false) String grid) {
		List<Result> results;

		try {
			results = this.resultService.findResultsByGridAndDriverAPI(driver, grid);
		} catch (Exception e) {
			results = new ArrayList<Result>();
		}

		return results;
	}

	// UC-025
	@GetMapping(value = "/list/constructor/{constructor}/position/{position}")
	public List<Result> findResultsByPositionAndConstructorAPI(@PathVariable(required = true) String constructor,
															   @PathVariable(required = true) String position) {
		List<Result> results;

		try {
			results = this.resultService.findResultsByPositionAndConstructorAPI(constructor, position);
		} catch (Exception e) {
			results = new ArrayList<Result>();
		}

		return results;
	}

	// UC-026
	@GetMapping(value = "/list/constructor/{constructor}/grid/{grid}")
	public List<Result> findResultsByGridAndConstructorAPI(@PathVariable(required = true) String constructor,
														   @PathVariable(required = false) String grid) {
		List<Result> results;

		try {
			results = this.resultService.findResultsByGridAndConstructorAPI(constructor, grid);
		} catch (Exception e) {
			results = new ArrayList<Result>();
		}

		return results;
	}

}
