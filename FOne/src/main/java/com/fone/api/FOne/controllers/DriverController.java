package com.fone.api.FOne.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.fone.api.FOne.domain.Driver;
import com.fone.api.FOne.services.DriverService;
import com.fone.api.FOne.services.RaceService;
import com.fone.api.FOne.services.ResultService;
import com.fone.api.FOne.services.UtilityService;

@RestController
@RequestMapping("/driver")
public class DriverController {

	private static final Log log = LogFactory.getLog(DriverController.class);
	
	@Autowired
	private DriverService driverService;

	@Autowired
	private RaceService raceService;

	@Autowired
	private ResultService resultService;

	@Autowired
	private UtilityService utilityService;
	
	public DriverController() {
		super();
	}

	// UC-001
	@GetMapping(value = "/list")
	public Page<Driver> findAllAPI(@RequestParam(defaultValue = "0", required = false) Integer offset,
								   @RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Driver> results = null;
		Pageable pageable;
		Sort sort;
		
		try {
			sort = Sort.by(Direction.ASC, "dateOfBirth");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.driverService.findAllAPI(pageable);
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

	// UC-002
	@GetMapping(value = "/list/country/{country}")
	public Page<Driver> findByCountryAPI(@PathVariable(required = true) String country,
										 @RequestParam(defaultValue = "0", required = false) Integer offset,
										 @RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Driver> results;
		Pageable pageable;
		Sort sort;
		
		try {
			sort = Sort.by(Direction.ASC, "dateOfBirth");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.driverService.findByCountryAPI(country, pageable);
		} catch (Exception e) {
			results = this.findAllAPI(offset, limit);
			
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
		}

		return results;
	}

	// UC-003
	@GetMapping(value = "/list/season/{season}")
	public Set<Driver> findDriversBySeasonAPI(@PathVariable(required = true) String season) {
		Set<Driver> results;

		try {
			results = this.raceService.findDriversBySeasonAPI(season);
		} catch (Exception e) {
			results = new HashSet<Driver>();
			
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
		}

		return results;
	}

	// UC-004
	@GetMapping(value = "/list/constructor/{constructor}")
	public Set<Driver> findDriversByConstructorAPI(@PathVariable(required = true) String constructor) {
		Set<Driver> results;

		try {
			results = this.resultService.findDriversByConstructorAPI(constructor);
		} catch (Exception e) {
			results = new HashSet<Driver>();
			
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
		}

		return results;
	}

	// UC-005
	@GetMapping(value = "/list/fullname/{fullname}")
	public List<Driver> findByFullnameAPI(@PathVariable(required = true) String fullname) {
		List<Driver> results;

		try {
			results = this.driverService.findByFullnameAPI(fullname);
		} catch (Exception e) {
			results = new ArrayList<Driver>();
			
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
		}

		return results;
	}

}
