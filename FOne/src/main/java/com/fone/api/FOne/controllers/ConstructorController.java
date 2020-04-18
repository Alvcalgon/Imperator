package com.fone.api.FOne.controllers;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/constructor")
@Api(tags = "Escudería")
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
	
	// UC-008
	@GetMapping(value = "/list")
	@ApiOperation(value = "Operación findAll",
    			  notes = "Devuelve la lista completa de escuderías")
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
			
			throw new ApiRequestException("No se pudo recuperar la lista completa de escuderías", e);
		}
		
		return results;
	}
	
	// UC-009
	@GetMapping(value = "/list/country/{country}")
	@ApiOperation(value = "Operación findByCountry",
    			  notes = "Devuelve la lista de escuderías filtradas por país")
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
		
			throw new ApiRequestException("No se pudo recuperar la lista de escuderías por país", e);
		}

		return results;
	}
	
	// UC-010
	@GetMapping(value = "/list/season/{season}")
	@ApiOperation(value = "Operación findBySeason",
    	          notes = "Devuelve la lista de escuderías que participaron en cierta temporada")
	public Set<Constructor> findBySeasonAPI(@PathVariable(required = true) String season) {
		Set<Constructor> results;

		try {
			results = this.raceService.findConstructorsBySeasonAPI(season);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("No se pudo recuperar la lista de escuderías por temporada", e);
		}

		return results;
	}
	
	// UC-011
	@GetMapping(value = "/list/driver/{driver}")
	@ApiOperation(value = "Operación findByDriver",
    		      notes = "Devuelve la lista de escuderías en las que un piloto estuvo enrolado")
	public Set<Constructor> findByDriverAPI(@PathVariable(required = true) String driver) {
		Set<Constructor> results;
		
		try {
			results = this.resultService.findConstructorsByDriverAPI(driver);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("No se pudo recuperar la lista de escuderías por piloto", e);
		}
		
		return results;
	}
	
	// UC-012
	@GetMapping(value = "/list/name/{name}")
	@ApiOperation(value = "Operación findByName",
    			 notes = "Devuelve la lista de escuderías cuyo nombre puede coincidir"
    			 		+ " parcial o completamente con la keyword")
	public Page<Constructor> findByNameAPI(@PathVariable(required = true) String name,
										   @RequestParam(defaultValue = "0", required = false) Integer offset,
										   @RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Constructor> results;
		Pageable pageable;
		Sort sort;
		
		try {
			sort = Sort.by(Direction.ASC, "name");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.constructorService.findByNameAPI(name, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("No se pudo recuperar la lista de escuderías por nombre", e);
		}
		
		return results;
	}
	
	// UC-013
	@GetMapping(value = "/display/{name}")
	@ApiOperation(value = "Operación findByName2",
    			  notes = "Devuelve la escudería cuyo nombre coincide completamente con la keyword")
	public Constructor findByName2API(@PathVariable(required = true) String name) {
		Constructor result;

		try {
			
			result = this.constructorService.findByNameAPI2(name);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error en ::findByNameAPI2: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error en ::findByNameAPI2: " + e.getMessage());
			}
			
			throw new ApiRequestException("No se pudo recuperar la escudería según el nombre", e);
		}
		
		return result;
	}
	
	// UC-014
	@GetMapping(value = "/list/country/{country}/name/{name}")
	@ApiOperation(value = "Operación findByAllParameters",
    			  notes = "Devuelve la lista de escuderías filtradas por parámetros")
	public Page<Constructor> findByAllParametersAPI(@PathVariable(required = true) String name,
										   @PathVariable(required = true) String country,
										   @RequestParam(defaultValue = "0", required = false) Integer offset,
										   @RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Constructor> results;
		Pageable pageable;
		Sort sort;
		
		try {
			sort = Sort.by(Direction.ASC, "name");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.constructorService.findByParametersAPI(name, country, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("No se pudo recuperar la lista de escuderías"
					+ " según los parámetros", e);
		}
		
		return results;
	}
	
}
