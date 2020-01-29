package com.fone.api.FOne.controllers;

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

import com.fone.api.FOne.domain.DriverStanding;
import com.fone.api.FOne.exception.ApiRequestException;
import com.fone.api.FOne.services.DriverStandingService;
import com.fone.api.FOne.services.UtilityService;

@RestController
@RequestMapping("/driver-standing")
public class DriverStandingController {

	private static final Log log = LogFactory.getLog(DriverStandingController.class);
	
	@Autowired
	private DriverStandingService driverStandingService;

	@Autowired
	private UtilityService utilityService;
	
	public DriverStandingController() {
		super();
	}

	// UC-027
	@GetMapping(value = "/list/season/{season}")
	public List<DriverStanding> findBySeasonAPI(@PathVariable(required = true) String season) {
		List<DriverStanding> results;

		try {
			results = this.driverStandingService.findBySeasonAPI(season);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve drivers standing list by season", e);
		}

		return results;
	}

	// UC-028
	@GetMapping(value = "/list/position/{position}")
	public Page<DriverStanding> findByPositionAPI(@PathVariable(required = true) String position,
												  @RequestParam(defaultValue = "0", required = false) Integer offset,
												  @RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<DriverStanding> results;
		Sort sort;
		Pageable pageable;
		
		try {	
			sort = Sort.by(Direction.ASC, "season");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			results = this.driverStandingService.findByPositionAPI(position, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve drivers standing list by position", e);
		}

		return results;
	}

	// UC-029
	@GetMapping(value = "/list/driver/{driver}")
	public Page<DriverStanding> findByDriverAPI(@PathVariable(required = true) String driver,
												@RequestParam(defaultValue = "0", required = false) Integer offset,
												@RequestParam(defaultValue = "5", required = false) Integer limit) {
		Page<DriverStanding> results;
		Sort sort;
		Pageable pageable;
		
		try {
			sort = Sort.by(Direction.ASC, "season");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.driverStandingService.findByDriverAPI(driver, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve drivers standing list by driver", e);
		}

		return results;
	}

}
