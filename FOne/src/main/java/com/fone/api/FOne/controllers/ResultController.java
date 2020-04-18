package com.fone.api.FOne.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fone.api.FOne.domain.Result;
import com.fone.api.FOne.exception.ApiRequestException;
import com.fone.api.FOne.services.ResultService;
import com.fone.api.FOne.services.UtilityService;

@RestController
@RequestMapping("/result")
public class ResultController {

	private static final Log log = LogFactory.getLog(ResultController.class);
	
	@Autowired
	private ResultService resultService;

	@Autowired
	private UtilityService utilityService;
	
	public ResultController() {
		super();
	}

	// UC-034
	@GetMapping(value = "/list/driver/{driver}/position/{position}")
	public Page<Result> findResultsByPositionAndDriverAPI(@PathVariable(required = true) String driver,
														  @PathVariable(required = true) String position,
														  @RequestParam(defaultValue = "0", required = false) Integer offset,
														  @RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Result> results;
		Pageable pageable;
		
		try {
			pageable = this.utilityService.getPageable(limit, offset);
			
			results = this.resultService.findResultsByPositionAndDriverAPI(driver, position, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve results list by position and driver", e);
		}

		return results;
	}

	// UC-035
	@GetMapping(value = "/list/driver/{driver}/grid/{grid}")
	public Page<Result> findResultsByGridAndDriverAPI(@PathVariable(required = true) String driver,
													  @PathVariable(required = false) String grid,
													  @RequestParam(defaultValue = "0", required = false) Integer offset,
													  @RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Result> results;
		Pageable pageable;
		
		try {
			pageable = this.utilityService.getPageable(limit, offset);
			
			results = this.resultService.findResultsByGridAndDriverAPI(driver, grid, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve results list by driver and grid", e);
		}

		return results;
	}

	// UC-036
	@GetMapping(value = "/list/constructor/{constructor}/position/{position}")
	public Page<Result> findResultsByPositionAndConstructorAPI(@PathVariable(required = true) String constructor,
															   @PathVariable(required = true) String position,
															   @RequestParam(defaultValue = "0", required = false) Integer offset,
															   @RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Result> results;
		Pageable pageable;
		
		try {
			pageable = this.utilityService.getPageable(limit, offset);
			results = this.resultService.findResultsByPositionAndConstructorAPI(constructor, position, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve results list by position and constructor", e);
		}

		return results;
	}

	// UC-037
	@GetMapping(value = "/list/constructor/{constructor}/grid/{grid}")
	public Page<Result> findResultsByGridAndConstructorAPI(@PathVariable(required = true) String constructor,
														   @PathVariable(required = false) String grid,
														   @RequestParam(defaultValue = "0", required = false) Integer offset,
														   @RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Result> results;
		Pageable pageable;
		
		try {
			pageable = this.utilityService.getPageable(limit, offset);
			
			results = this.resultService.findResultsByGridAndConstructorAPI(constructor, grid, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve constructors list by grid and constructor", e);
		}

		return results;
	}

}
