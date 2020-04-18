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

import com.fone.api.FOne.domain.Circuit;
import com.fone.api.FOne.exception.ApiRequestException;
import com.fone.api.FOne.services.CircuitService;
import com.fone.api.FOne.services.RaceService;
import com.fone.api.FOne.services.UtilityService;

@RestController
@RequestMapping("/circuit")
public class CircuitController {

	private static final Log log = LogFactory.getLog(CircuitController.class);
	
	@Autowired
	private CircuitService circuitService;
	
	@Autowired
	private RaceService raceService;
	
	@Autowired
	private UtilityService utilityService;
	
	public CircuitController() {
		super();
	}
	
	// UC-015
	@GetMapping(value = "/list")
	public Page<Circuit> findAllAPI(@RequestParam(defaultValue = "0", required = false) Integer offset,
			   						@RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Circuit> results;
		Pageable pageable;
		Sort sort;
		
		try {
			sort = Sort.by(Direction.ASC, "name");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.circuitService.findAllAPI(pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
		
			throw new ApiRequestException("It cannot retrieve circuit list", e);
		}
		
		return results;
	}
	
	// UC-016
	@GetMapping(value = "/list/type/{type}")
	public Page<Circuit> findByType(@PathVariable(required = true) String type,
									@RequestParam(defaultValue = "0", required = false) Integer offset,
									@RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Circuit> results;
		Pageable pageable;
		Sort sort;
		
		try {
			sort = Sort.by(Direction.ASC, "name");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.circuitService.findByTypeAPI(type, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve circuit list by type", e);
		}
		
		return results;
	}
	
	// UC-017
	@GetMapping(value = "/list/location/{location}")
	public Page<Circuit> findByLocation(@PathVariable(required = true) String location,
										@RequestParam(defaultValue = "0", required = false) Integer offset,
										@RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Circuit> results;
		Pageable pageable;
		Sort sort;
		
		try {
			sort = Sort.by(Direction.ASC, "name");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.circuitService.findByLocationAPI(location, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve circuits list by location", e);
		}
		
		return results;
	}
	
	// UC-018
	@GetMapping(value = "/list/season/{season}")
	public List<Circuit> findBySeasonAPI(@PathVariable(required = true) String season) {
		List<Circuit> results;
		
		try {
			
			results = this.raceService.findCircuitsBySeasonAPI(season);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve circuits list by season", e);
		}
		
		return results;
	}
	
	
	// UC-019
	@GetMapping(value = "/list/name/{name}")
	public Page<Circuit> findByNameAPI(@PathVariable(required = true) String name,
				@RequestParam(defaultValue = "0", required = false) Integer offset,
				@RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Circuit> results;
		Pageable pageable;
		Sort sort;
		
		try {
			sort = Sort.by(Direction.ASC, "name");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.circuitService.findByNameAPI(name, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve a circuit", e);
		}
		
		return results;
	}
	
	// UC-020
	@GetMapping(value = "/display/{name}")
	public Circuit findByNameAPI2(@PathVariable(required = true) String name) {
		Circuit result;
		
		try {
			
			result = this.circuitService.findByNameAPI2(name);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve a circuit", e);
		}
		
		return result;
	}
	
	// UC-021
	@GetMapping(value = "/list/location/{location}/type/{type}")
	public Page<Circuit> findByLocationAndType(@PathVariable(required = true) String location,
								   @PathVariable(required = true) String type,
							       @RequestParam(defaultValue = "0", required = false) Integer offset,
							       @RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Circuit> results;
		Pageable pageable;
		Sort sort;
		
		try {
			sort = Sort.by(Direction.ASC, "name");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.circuitService.findByLocationAndTypeAPI(location, type, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve circuits list by location and type", e);
		}
		
		return results;
	}
	
	// UC-022
	@GetMapping(value = "/list/type/{type}/name/{name}")
	public Page<Circuit> findByTypeAndName(@PathVariable(required = true) String type,
								   @PathVariable(required = true) String name,
							       @RequestParam(defaultValue = "0", required = false) Integer offset,
							       @RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Circuit> results;
		Pageable pageable;
		Sort sort;
		
		try {
			sort = Sort.by(Direction.ASC, "name");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.circuitService.findByTypeAndNameAPI(type, name, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve circuits list by location and type", e);
		}
		
		return results;
	}
	
	// UC-023
	@GetMapping(value = "/list/location/{location}/name/{name}")
	public Page<Circuit> findByLocationAndName(@PathVariable(required = true) String location,
								   @PathVariable(required = true) String name,
							       @RequestParam(defaultValue = "0", required = false) Integer offset,
							       @RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Circuit> results;
		Pageable pageable;
		Sort sort;
		
		try {
			sort = Sort.by(Direction.ASC, "name");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.circuitService.findByLocationAndNameAPI(location, name, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve circuits list by location and name", e);
		}
		
		return results;
	}
	
	// UC-024
	@GetMapping(value = "/list/location/{location}/type/{type}/name/{name}")
	public Page<Circuit> findByAllParameters(@PathVariable(required = true) String location,
								   @PathVariable(required = true) String type,
								   @PathVariable(required = true) String name,
							       @RequestParam(defaultValue = "0", required = false) Integer offset,
							       @RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Circuit> results;
		Pageable pageable;
		Sort sort;
		
		try {
			sort = Sort.by(Direction.ASC, "name");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.circuitService.findByAllParametersAPI(location, type, name, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve circuits list by location, type and name", e);
		}
		
		return results;
	}
	
}
