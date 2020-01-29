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
	
	// UC-011
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
	
	// UC-012
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
	
	// UC-013
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
	
	// UC-014
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
	
	// UC-015
	@GetMapping(value = "/list/name/{name}")
	public List<Circuit> findByNameAPI(@PathVariable(required = true) String name) {
		List<Circuit> results;
		
		try {
			results = this.circuitService.findByNameAPI(name);
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
	
}
