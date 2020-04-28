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

import com.fone.api.FOne.domain.ConstructorStanding;
import com.fone.api.FOne.exception.ApiRequestException;
import com.fone.api.FOne.services.ConstructorStandingService;
import com.fone.api.FOne.services.DriverStandingService;
import com.fone.api.FOne.services.UtilityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/constructor-standing")
@Api(tags = "Clasificación general de escuderías")
public class ConstructorStandingController {

	private static final Log log = LogFactory.getLog(ConstructorStandingController.class);
	
	@Autowired
	private ConstructorStandingService constructorStandingService;

	@Autowired
	private DriverStandingService driverStandingService;
	
	@Autowired
	private UtilityService utilityService;
	
	public ConstructorStandingController() {
		super();
	}

	// UC-041
	@GetMapping(value = "/list/season/{season}")
	@ApiOperation(value = "Operación findBySeason",
    			  notes = "Devuelve la clasificación general de escuderías de una cierta temporada")
	public Page<ConstructorStanding> findBySeasonAPI(@PathVariable(required = true) String season,
					@RequestParam(defaultValue = "0", required = false) Integer offset,
					@RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<ConstructorStanding> results;
		Pageable pageable;
		Sort sort;
		
		try {
			sort = Sort.by(Direction.DESC, "points");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.constructorStandingService.findBySeasonAPI(season, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("No se pudo recuperar la clasificación general de"
					+ " escuderías para la temporada solicitada", e);
		}

		return results;
	}

	// UC-042
	@GetMapping(value = "/list/position/{position}")
	@ApiOperation(value = "Operación findByPosition",
	  			  notes = "Devuelve la clasificación general de las escuderías"
	  			  		+ " que quedaron en una posición determinada al finalizar el campeonato")
	public Page<ConstructorStanding> findByPositionAPI(@PathVariable(required = true) String position,
													   @RequestParam(defaultValue = "0", required = false) Integer offset,
													   @RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<ConstructorStanding> results;
		Sort sort;
		Pageable pageable;
		
		try {
			sort = Sort.by(Direction.DESC, "season");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.constructorStandingService.findByPositionAPI(position, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("No se pudo recuperar la clasificación general"
					+ " de escuderías para cierta posición", e);
		}

		return results;
	}

	// UC-043
	@GetMapping(value = "/list/constructor/{constructor}")
	@ApiOperation(value = "Operación findByConstructor",
	  notes = "Devuelve todas las clasificaciones registradas para cierta escudería")
	public Page<ConstructorStanding> findByConstructorAPI(@PathVariable(required = true) String constructor,
														  @RequestParam(defaultValue = "0", required = false) Integer offset,
														  @RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<ConstructorStanding> results;
		Sort sort;
		Pageable pageable;
		
		try {
			sort = Sort.by(Direction.DESC, "season");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.constructorStandingService.findByConstructorAPI(constructor, pageable);
		} catch (Exception e) {	
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("No se pudo recuperar las clasificaciones para una determinada escudería", e);
		}

		return results;
	}

	@GetMapping(value = "/count/constructor/{constructor}")
	@ApiOperation(value = "Operación findCountByConstructor",
    			  notes = "Devuelve el número de campeonatos en los que la escudería ha participado")
	public Integer findCountByConstructorAndPositionAPI(@PathVariable(required = true) String constructor) {
		Integer result;
		
		try {
			result = this.constructorStandingService.findCountByConstructorAPI(constructor);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("No se pudo recuperar el número de campeonatos en los que una escudería participó", e);
		}
		
		return result;
	}
	
	@GetMapping(value = "/count/constructor/{constructor}/position/{position}")
	@ApiOperation(value = "Operación findCountByConstructorAndPosition",
    			  notes = "Devuelve el número de campeonatos en los que la escudería finalizó en cierta posición")
	public Integer findCountByConstructorAndPositionAPI(@PathVariable(required = true) String constructor,
												 		@PathVariable(required = true) String position) {
		Integer result;
		
		try {
			result = this.constructorStandingService.findCountByConstructorAndPositionAPI(constructor, position);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("No se pudo recuperar el número de campeonatos en los que una escudería finalizó en cierta posición", e);
		}
		
		return result;
	}

	@GetMapping(value = "/drivers-titles/{constructor}")
	@ApiOperation(value = "Operación findDriversTitlesByConstructor",
    			  notes = "Devuelve el número de titulos de pilotos conseguidos por la escudería")
	public Integer findDriversTitlesByConstructorAPI(@PathVariable(required = true) String constructor) {
		Integer result;
		
		try {
			result = this.driverStandingService.findDriversTitlesByConstructorAPI(constructor);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("No se pudo recuperar el número de títulos de pilotos para una escudería", e);
		}
		
		return result;
	}
	
}
