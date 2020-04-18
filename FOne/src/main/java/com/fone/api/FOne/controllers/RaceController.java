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

	// UC-025
	@GetMapping(value = "/list/season/{season}")
	public Page<Race> findBySeasonAPI(@PathVariable(required = true) String season,
			   						  @RequestParam(defaultValue = "0", required = false) Integer offset,
			   						  @RequestParam(defaultValue = "5", required = false) Integer limit) {
		Page<Race> results;
		Pageable pageable;
		Sort sort;

		try {
			sort = Sort.by(Direction.ASC, "raceDate");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.raceService.findBySeasonAPI(season, pageable);
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

	// UC-026
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

	// UC-027
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

	// UC-028
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

	// UC-029
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
	
	// UC-030
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
	
	// UC-031
	@GetMapping(value = "/list/event/{event}")
	public Page<Race> findByEventAPI(@PathVariable(required = true) String event,
									 @RequestParam(defaultValue = "0", required = false) Integer offset,
					   				 @RequestParam(defaultValue = "5", required = false) Integer limit) {
		Page<Race> results;
		Pageable pageable;
		Sort sort;
		
		try {
			sort = Sort.by(Direction.ASC, "raceDate");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.raceService.findRaceByEventAPI(event, pageable);
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
	
	// UC-032
	@GetMapping(value = "/list/season/{season}/event/{event}")
	public Page<Race> findBySeasonAndEventAPI(@PathVariable(required = true) String season,
									  		  @PathVariable(required = true) String event,
									  		  @RequestParam(defaultValue = "0", required = false) Integer offset,
					   						  @RequestParam(defaultValue = "5", required = false) Integer limit) {
		Page<Race> results;
		Pageable pageable;
		Sort sort;
		
		try {
			sort = Sort.by(Direction.ASC, "raceDate");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.raceService.findRaceBySeasonAndEventAPI(event, season, pageable);
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
	
	// UC-033
	@GetMapping(value = "/display/season/{season}/event/{event}")
	public Race findOneBySeasonAndEventAPI(@PathVariable(required = true) String season,
									       @PathVariable(required = true) String event) {
		Race result;

		try {
			result = this.raceService.findOneBySeasonAndEventAPI(event, season);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve a race", e);
		}

		return result;
	}
}
