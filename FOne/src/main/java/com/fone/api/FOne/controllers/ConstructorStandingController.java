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

import com.fone.api.FOne.domain.ConstructorStanding;
import com.fone.api.FOne.domain.ConstructorTitle;
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

	// UC-045
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

	// UC-046
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

	// UC-047
	@GetMapping(value = "/list/constructor/{escuderia}")
	@ApiOperation(value = "Operación findByConstructor",
	  notes = "Devuelve todas las clasificaciones registradas para cierta escudería")
	public Page<ConstructorStanding> findByConstructorAPI(@PathVariable(required = true) String escuderia,
														  @RequestParam(defaultValue = "0", required = false) Integer offset,
														  @RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<ConstructorStanding> results;
		Sort sort;
		Pageable pageable;
		
		try {
			sort = Sort.by(Direction.DESC, "season");
			pageable = this.utilityService.getPageable(limit, offset, sort);
			
			results = this.constructorStandingService.findByConstructorAPI(escuderia, pageable);
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

	// UC-048
	@GetMapping(value = "/count/constructor/{escuderia}")
	@ApiOperation(value = "Operación findCountByConstructor",
    			  notes = "Devuelve el número de campeonatos en los que la escudería ha participado")
	public Integer findCountByConstructorAPI(@PathVariable(required = true) String escuderia) {
		Integer result;
		
		try {
			result = this.constructorStandingService.findCountByConstructorAPI(escuderia);
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
	
	// UC-049
	@GetMapping(value = "/count/constructor/{escuderia}/position/{position}")
	@ApiOperation(value = "Operación findCountByConstructorAndPosition",
    			  notes = "Devuelve el número de campeonatos en los que la escudería finalizó en cierta posición")
	public Integer findCountByConstructorAndPositionAPI(@PathVariable(required = true) String escuderia,
												 		@PathVariable(required = true) String position) {
		Integer result;
		
		try {
			result = this.constructorStandingService.findCountByConstructorAndPositionAPI(escuderia, position);
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

	// UC-050
	@GetMapping(value = "/drivers-titles/{escuderia}")
	@ApiOperation(value = "Operación findDriversTitlesByConstructor",
    			  notes = "Devuelve el número de titulos de pilotos conseguidos por la escudería")
	public Integer findDriversTitlesByConstructorAPI(@PathVariable(required = true) String escuderia) {
		Integer result;
		
		try {
			result = this.driverStandingService.findDriversTitlesByConstructorAPI(escuderia);
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
	
	// UC-052
	@GetMapping(value = "/list/winners")
	@ApiOperation(value = "Operación findConstructorsTitle()",
    			  notes = "Devuelve aquellas escuderías con más titulos")
	public List<ConstructorTitle> findConstructorsTitle() {
		List<ConstructorTitle> results;
		
		try {

			results = this.constructorStandingService.findConstructorsTitle();
		
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}
			
			throw new ApiRequestException("No se pudo recuperar los ganadores", e);
		}

		return results;
	}
	
}
