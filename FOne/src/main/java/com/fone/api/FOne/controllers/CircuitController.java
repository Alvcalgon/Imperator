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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/circuit")
@Api(tags = "Circuito")
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
	@ApiOperation(value = "Operación findAll",
	              notes = "Devuelve la lista completa de circuitos")
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
		
			throw new ApiRequestException("No se pudo recuperar la lista completa de circuitos", e);
		}
		
		return results;
	}
	
	// UC-017
	@GetMapping(value = "/list/location/{location}")
	@ApiOperation(value = "Operación findByLocation",
	              notes = "Devuelve la lista de circuitos filtrada por localización")
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
			
			throw new ApiRequestException("No se pudo recuperar la lista de circuitos por localización", e);
		}
		
		return results;
	}
	
	// UC-018
	@GetMapping(value = "/list/season/{season}")
	@ApiOperation(value = "Operación findBySeason",
	       notes = "Devuelve la lista de circuitos en las que tuvo lugar una carrera para cierta temporada")
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
			
			throw new ApiRequestException("No se pudo recuperar la lista de circuitos por temporada", e);
		}
		
		return results;
	}
	
	
	// UC-019
	@GetMapping(value = "/list/name/{name}")
	@ApiOperation(value = "Operación findByName",
	              notes = "Devuelve la lista de circuitos cuya keyword coincide parcial o"
	              		+ " completamente con el nombre de cada circuito")
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
			
			throw new ApiRequestException("No se pudo recuperar la lista de circuitos por nombre",
										  e);
		}
		
		return results;
	}
	
	// UC-020
	@GetMapping(value = "/display/{name}")
	@ApiOperation(value = "Operación findByName2",
	              notes = "Devuelve el circuito cuyo nombre coincide exactamente con el nombre"
	              		+ " introducido como parámetro de búsqueda")
	public Circuit findByName2API(@PathVariable(required = true) String name) {
		Circuit result;
		
		try {
			
			result = this.circuitService.findByNameAPI2(name);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("No se pudo recuperar el circuito requerido", e);
		}
		
		return result;
	}
		
	
	// UC-024
	@GetMapping(value = "/list/location/{location}/name/{name}")
	@ApiOperation(value = "Operación findByAllParameters",
	              notes = "Devuelve la lista de circuitos filtradas por todo los parámetros posibles")
	public Page<Circuit> findByAllParametersAPI(@PathVariable(required = true) String location,
								   @PathVariable(required = true) String name,
							       @RequestParam(defaultValue = "0", required = false) Integer offset,
							       @RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Circuit> results;
		Pageable pageable;
		Sort sort;
		
		try {
			sort = Sort.by(Direction.ASC, "name");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.circuitService.findByAllParametersAPI(location, name, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("No se pudo recuperar la lista de circuitos por"
					+ " todos los parámetros", e);
		}
		
		return results;
	}
	
}
