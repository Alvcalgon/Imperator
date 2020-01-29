package com.fone.api.FOne.controllers;

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

import com.fone.api.FOne.domain.Constructor;
import com.fone.api.FOne.exception.ApiRequestException;
import com.fone.api.FOne.services.ConstructorService;
import com.fone.api.FOne.services.RaceService;
import com.fone.api.FOne.services.ResultService;
import com.fone.api.FOne.services.UtilityService;

@RestController
@RequestMapping("/constructor")
public class ConstructorController {

	private static final Log log = LogFactory.getLog(ConstructorController.class);
	
	@Autowired
	private ConstructorService constructorService;
	
	@Autowired
	private RaceService raceService;

	@Autowired
	private ResultService resultService;
	
	@Autowired
	private UtilityService utilityService;
	
	public ConstructorController() {
		super();
	}
	
	// UC-006
	@GetMapping(value = "/list")
	public Page<Constructor> findAllAPI(@RequestParam(defaultValue = "0", required = false) Integer offset,
			   							@RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Constructor> results;
		Sort sort;
		Pageable pageable;
		
		try {
			sort = Sort.by(Direction.ASC, "name");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.constructorService.findAllAPI(pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve constructors list", e);
		}
		
		return results;
	}
	
	// UC-007
	@GetMapping(value = "/list/country/{country}")
	public Page<Constructor> findByCountryAPI(@PathVariable(required = true) String country,
											  @RequestParam(defaultValue = "1", required = false) Integer offset,
											  @RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Constructor> results;
		Sort sort;
		Pageable pageable;
		
		try {
			sort = Sort.by(Direction.ASC, "name");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.constructorService.findByCountryAPI(country, pageable);
		} catch (Exception e) {			
			results = this.findAllAPI(offset, limit);
			
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
		
			throw new ApiRequestException("It cannot retrieve constructors list by country", e);
		}

		return results;
	}
	
	// UC-008
	@GetMapping(value = "/list/season/{season}")
	public Set<Constructor> findConstructorsBySeasonAPI(@PathVariable(required = true) String season) {
		Set<Constructor> results;

		try {
			results = this.raceService.findConstructorsBySeasonAPI(season);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve constructors list by season", e);
		}

		return results;
	}
	
	// UC-009
	@GetMapping(value = "/list/driver/{driver}")
	public Set<Constructor> findConstructorsByDriverAPI(@PathVariable(required = true) String driver) {
		Set<Constructor> results;
		
		try {
			results = this.resultService.findConstructorsByDriverAPI(driver);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve constructors list by driver", e);
		}
		
		return results;
	}
	
	// UC-010
	@GetMapping(value = "/list/name/{name}")
	public List<Constructor> findByNameAPI(@PathVariable(required = true) String name) {
		List<Constructor> results;
		
		try {
			results = this.constructorService.findByNameAPI(name);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("It cannot retrieve a constructor", e);
		}
		
		return results;
	}
	
}
