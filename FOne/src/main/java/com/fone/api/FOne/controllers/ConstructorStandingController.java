package com.fone.api.FOne.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fone.api.FOne.domain.ConstructorStanding;
import com.fone.api.FOne.exception.ApiRequestException;
import com.fone.api.FOne.services.ConstructorStandingService;
import com.fone.api.FOne.services.UtilityService;

@RestController
@RequestMapping("/constructor-standing")
public class ConstructorStandingController {

	private static final Log log = LogFactory.getLog(ConstructorStandingController.class);
	
	@Autowired
	private ConstructorStandingService constructorStandingService;

	@Autowired
	private UtilityService utilityService;
	
	public ConstructorStandingController() {
		super();
	}

	// UC-030
	@GetMapping(value = "/list/season/{season}")
	public Page<ConstructorStanding> findBySeasonAPI(@PathVariable(required = true) String season,
					@RequestParam(defaultValue = "0", required = false) Integer offset,
					@RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<ConstructorStanding> results;
		Pageable pageable;
		Sort sort;
		
		try {
			sort = Sort.by(Direction.DESC, "points");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.constructorStandingService.findBySeasonAPI(season, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve constructors standing list by season", e);
		}

		return results;
	}

	// UC-031
	@GetMapping(value = "/list/position/{position}")
	public Page<ConstructorStanding> findByPositionAPI(@PathVariable(required = true) String position,
													   @RequestParam(defaultValue = "0", required = false) Integer offset,
													   @RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<ConstructorStanding> results;
		Sort sort;
		Pageable pageable;
		
		try {
			sort = Sort.by(Direction.ASC, "season");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.constructorStandingService.findByPositionAPI(position, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve constructors standing list by position", e);
		}

		return results;
	}

	// UC-032
	@GetMapping(value = "/list/constructor/{constructor}")
	public Page<ConstructorStanding> findByConstructorAPI(@PathVariable(required = true) String constructor,
														  @RequestParam(defaultValue = "0", required = false) Integer offset,
														  @RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<ConstructorStanding> results;
		Sort sort;
		Pageable pageable;
		
		try {
			sort = Sort.by(Direction.ASC, "season");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.constructorStandingService.findByConstructorAPI(constructor, pageable);
		} catch (Exception e) {	
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve constructor standing list by constructor", e);
		}

		return results;
	}

}
