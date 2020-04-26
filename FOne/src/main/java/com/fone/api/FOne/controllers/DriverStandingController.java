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

import com.fone.api.FOne.domain.DriverStanding;
import com.fone.api.FOne.exception.ApiRequestException;
import com.fone.api.FOne.services.DriverStandingService;
import com.fone.api.FOne.services.UtilityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/driver-standing")
@Api(tags = "Clasificación general de pilotos")
public class DriverStandingController {

	private static final Log log = LogFactory.getLog(DriverStandingController.class);
	
	@Autowired
	private DriverStandingService driverStandingService;

	@Autowired
	private UtilityService utilityService;
	
	public DriverStandingController() {
		super();
	}

	// UC-038
	@GetMapping(value = "/list/season/{season}")
	@ApiOperation(value = "Operación findBySeason",
    			  notes = "Devuelve la clasificación general de pilotos de una determinada temporada")
	public Page<DriverStanding> findBySeasonAPI(@PathVariable(required = true) String season,
							@RequestParam(defaultValue = "0", required = false) Integer offset,
							@RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<DriverStanding> results;
		Pageable pageable;
		Sort sort;

		try {
			sort = Sort.by(Direction.DESC, "points");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.driverStandingService.findBySeasonAPI(season, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("No se pudo recuperar la clasificación general de"
					+ " pilotos para la temporada solicitada", e);
		}

		return results;
	}

	// UC-039
	@GetMapping(value = "/list/position/{position}")
	@ApiOperation(value = "Operación findByPosition",
    			  notes = "Devuelve la clasificación general de aquellos pilotos que finalización"
    			  		+ " el campeoanto en una cierta posición")
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
			
			throw new ApiRequestException("No se pudo recuperar la clasificación general de"
					+ " los pilotos según la posición", e);
		}

		return results;
	}

	// UC-040
	@GetMapping(value = "/list/driver/{driver}")
	@ApiOperation(value = "Operación findByDriver",
    			  notes = "Devuelve la clasificación general de un piloto a largo de toda su trayectoria")
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
			
			throw new ApiRequestException("No se pudo recuperar la clasificación general de un"
					+ " piloto en cada uno de los campeonatos en los que parcipó", e);
		}

		return results;
	}

	@GetMapping(value = "/count/driver/{driver}")
	@ApiOperation(value = "Operación findCountByDriver",
    			  notes = "Devuelve el número de campeonatos en los que ha participado el piloto en toda su trayectoria")
	public Integer findCountByDriverAPI(@PathVariable(required = true) String driver) {
		Integer result;
		
		try {
			result = this.driverStandingService.findCountByDriverAPI(driver);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("No se pudo recuperar el número de campeonatos para el piloto", e);
		}
		
		return result;
	}
	
	@GetMapping(value = "/count/driver/{driver}/position/{position}")
	@ApiOperation(value = "Operación findCountByDriver",
    			  notes = "Devuelve el número de campeonatos en los que el piloto finalizó en cierta posición")
	public Integer findCountDriverAndPositionAPI(@PathVariable(required = true) String driver,
												 @PathVariable(required = true) String position) {
		Integer result;
		
		try {
			result = this.driverStandingService.findCountDriverAndPositionAPI(driver, position);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("No se pudo recuperar el número de campeonatos en los que un piloto finalizó en cierta posición", e);
		}
		
		return result;
	}
}
