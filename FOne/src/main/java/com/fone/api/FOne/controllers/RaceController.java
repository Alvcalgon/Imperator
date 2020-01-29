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

import com.fone.api.FOne.domain.Race;
import com.fone.api.FOne.exception.ApiRequestException;
import com.fone.api.FOne.services.RaceService;
import com.fone.api.FOne.services.UtilityService;

@RestController
@RequestMapping("/race")
public class RaceController {
	
	private static final Log log = LogFactory.getLog(RaceController.class);
	
	@Autowired
	private RaceService raceService;

	@Autowired
	private UtilityService utilityService;
	
	public RaceController() {
		super();
	}

	// UC-016
	@GetMapping(value = "/list/season/{season}")
	public List<Race> findBySeasonAPI(@PathVariable(required = true) String season) {
		List<Race> results;

		try {
			results = this.raceService.findBySeasonAPI(season);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve races list by season", e);
		}

		return results;
	}

	// UC-017
	@GetMapping(value = "/list/circuit/{circuit}")
	public Page<Race> findByCircuitAPI(@PathVariable(required = true) String circuit,
									   @RequestParam(defaultValue = "0", required = false) Integer offset,
									   @RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Race> results;
		Pageable pageable;
		Sort sort;
		
		try {
			sort = Sort.by(Direction.ASC, "raceDate");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.raceService.findByCircuitAPI(circuit, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve races list by circuit", e);
		}

		return results;
	}

	// UC-018
	@GetMapping(value = "/list/driver/{driver}")
	public Page<Race> findByDriverAPI(@PathVariable(required = true) String driver,
									  @RequestParam(defaultValue = "0", required = false) Integer offset,
									  @RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Race> results;
		Sort sort;
		Pageable pageable;
		
		try {
			sort = Sort.by(Direction.ASC, "raceDate");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.raceService.findRacesByDriverAPI(driver, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve races list by driver", e);
		}

		return results;
	}

	// UC-019
	@GetMapping(value = "/list/constructor/{constructor}")
	public Page<Race> findByConstructorAPI(@PathVariable(required = true) String constructor,
										   @RequestParam(defaultValue = "0", required = false) Integer offset,
										   @RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Race> results;
		Sort sort;
		Pageable pageable;
		
		try {
			sort = Sort.by(Direction.ASC, "raceDate");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.raceService.findRacesByConstructorAPI(constructor, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve races list by constructor", e);
		}

		return results;
	}

	// UC-020
	@GetMapping(value = "/list/driver/{driver}/season/{season}")
	public Page<Race> findByDriverAndSeasonAPI(@PathVariable(required = true) String driver,
											   @PathVariable(required = true) String season,
											   @RequestParam(defaultValue = "0", required = false) Integer offset,
											   @RequestParam(defaultValue = "5", required = false) Integer limit) {
		Page<Race> results;
		Sort sort;
		Pageable pageable;
		
		try {
			sort = Sort.by(Direction.ASC, "raceDate");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.raceService.findRacesByDriverAndSeasonAPI(driver, season, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve races list by driver and season", e);
		}

		return results;
	}
	
	// UC-021
	@GetMapping(value = "/list/constructor/{constructor}/season/{season}")
	public Page<Race> findByConstructorAndSeasonAPI(@PathVariable(required = true) String constructor,
													@PathVariable(required = true) String season,
													@RequestParam(defaultValue = "0", required = false) Integer offset,
													@RequestParam(defaultValue = "5", required = false) Integer limit) {
		Page<Race> results;
		Sort sort;
		Pageable pageable;
		
		try {
			sort = Sort.by(Direction.ASC, "raceDate");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.raceService.findRacesByConstructorAndSeasonAPI(constructor, season, pageable);
		} catch (Exception e) {			
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve races list by constructor and season", e);
		}

		return results;
	}
	
	// UC-022
	@GetMapping(value = "/list/season/{season}/event/{event}")
	public List<Race> findBySeasonAndEventAPI(@PathVariable(required = true) String season,
									  @PathVariable(required = true) String event) {
		List<Race> results;

		try {
			results = this.raceService.findRaceBySeasonAndEventAPI(event, season);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve a race", e);
		}

		return results;
	}
	
}
