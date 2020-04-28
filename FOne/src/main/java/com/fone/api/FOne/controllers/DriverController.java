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

import com.fone.api.FOne.domain.Driver;
import com.fone.api.FOne.exception.ApiRequestException;
import com.fone.api.FOne.services.DriverService;
import com.fone.api.FOne.services.RaceService;
import com.fone.api.FOne.services.ResultService;
import com.fone.api.FOne.services.UtilityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/driver")
@Api(tags = "Piloto")
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
	@ApiOperation(value = "Operación findAll",
    		      notes = "Devuelve la lista completa de pilotos")
	public Page<Driver> findAllAPI(@RequestParam(defaultValue = "0", required = false) Integer offset,
								   @RequestParam(defaultValue = "10", required = false) Integer limit) {
		
		Page<Driver> results = null;
		Pageable pageable;
		Sort sort;
		
		try {
			sort = Sort.by(Direction.DESC, "dateOfBirth");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.driverService.findAllAPI(pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("No se pudo recuperar la colección completa de pilotos", e);
		}

		return results;
	}

	// UC-002
	@GetMapping(value = "/list/nacionality/{nacionality}")
	@ApiOperation(value = "Operación findByNacionality",
    			  notes = "Devuelve la lista de pilotos filtrada por nacionalidad")
	public Page<Driver> findByNacionalityAPI(@PathVariable(required = true) String nacionality,
										 @RequestParam(defaultValue = "0", required = false) Integer offset,
										 @RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Driver> results;
		Pageable pageable;
		Sort sort;
		
		try {
			sort = Sort.by(Direction.DESC, "dateOfBirth");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.driverService.findByNacionalityAPI(nacionality, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("No se pudo recuperar la lista de pilotos por país", e);
		}

		return results;
	}

	// UC-003
	@GetMapping(value = "/list/season/{season}")
	@ApiOperation(value = "Operación findBySeason",
    			  notes = "Devuelve la lista de pilotos por temporada")
	public Set<Driver> findBySeasonAPI(@PathVariable(required = true) String season) {
		Set<Driver> results;

		try {
			results = this.raceService.findDriversBySeasonAPI(season);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("No se pudo recuperar la lista de pilotos por temporada", e);
		}

		return results;
	}

	// UC-004
	@GetMapping(value = "/list/constructor/{constructor}")
	@ApiOperation(value = "Operación findByConstructor",
    			  notes = "Devuelve la lista de pilotos que estuvieron enrolados en una escudería concreta")
	public Set<Driver> findByConstructorAPI(@PathVariable(required = true) String constructor) {
		Set<Driver> results;

		try {
			results = this.resultService.findDriversByConstructorAPI(constructor);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("No se pudo recuperar la lista de pilotos por escudería", e);
		}

		return results;
	}

	// UC-005
	@GetMapping(value = "/list/fullname/{fullname}")
	@ApiOperation(value = "Operación findByFullname",
    			  notes = "Devuelve la lista de pilotos cuya keyword coincide parcial o"
    			  		+ " completamente para el nombre completo de cada piloto")
	public Page<Driver> findByFullnameAPI(@PathVariable(required = true) String fullname,
										  @RequestParam(defaultValue = "0", required = false) Integer offset,
			                              @RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Driver> results;
		Pageable pageable;
		Sort sort;
	
		try {
			sort = Sort.by(Direction.DESC, "dateOfBirth");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.driverService.findByFullnameAPI(fullname, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("No se pudo recuperar la lista de pilotos"
					+ " según el nombre completo", e);
		}

		return results;
	}

	// UC-006
	@GetMapping(value = "/display/{name}")
	@ApiOperation(value = "Operación findByFullname2",
    			  notes = "Devuelve el piloto cuyo nombre completo coincide exactamente con la keyword")
	public Driver findByFullname2API(@PathVariable(required = true) String name) {
		Driver result;

		try {
			result = this.driverService.findByFullname2(name);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("No se pudo recuperar el piloto", e);
		}

		return result;
	}
	
	// UC-007
	@GetMapping(value = "/list/nacionality/{nacionality}/fullname/{fullname}")
	@ApiOperation(value = "Operación findByParameters",
    			  notes = "Devuelve la lista de pilotos filtrada por los paramátros nombre completo y país")
	public Page<Driver> findByParametersAPI(@PathVariable(required = true) String fullname,
											@PathVariable(required = true) String nacionality,
										    @RequestParam(defaultValue = "0", required = false) Integer offset,
			                                @RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Driver> results;
		Pageable pageable;
		Sort sort;
	
		try {
			sort = Sort.by(Direction.DESC, "dateOfBirth");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.driverService.findByParametersAPI(fullname, nacionality, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("No se pudo recuperar la lista de pilotos"
					+ " según los parámetros nombre completo y país", e);
		}

		return results;
	}
	
}
