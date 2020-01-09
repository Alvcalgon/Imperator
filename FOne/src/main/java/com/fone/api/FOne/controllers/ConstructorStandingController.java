package com.fone.api.FOne.controllers;

import java.util.ArrayList;
import java.util.List;

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
	public List<ConstructorStanding> findBySeasonAPI(@PathVariable(required = true) String season) {
		List<ConstructorStanding> results;

		try {
			results = this.constructorStandingService.findBySeasonAPI(season);
		} catch (Exception e) {
			results = new ArrayList<ConstructorStanding>();
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
			results = null;
			
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
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
