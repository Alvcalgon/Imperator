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

	// UC-023
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
			results = null;
			
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
		}

		return results;
	}

	// UC-024
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
			results = null;
			
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
		}

		return results;
	}

	// UC-025
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
			results = null;
			
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
		}

		return results;
	}

	// UC-026
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
			results = null;
			
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
		}

		return results;
	}

}
