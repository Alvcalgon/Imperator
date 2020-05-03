package com.fone.api.FOne.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fone.api.FOne.domain.Result;
import com.fone.api.FOne.exception.ApiRequestException;
import com.fone.api.FOne.services.RaceService;
import com.fone.api.FOne.services.ResultService;
import com.fone.api.FOne.services.UtilityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/result")
@Api(tags = "Resultado")
public class ResultController {

	private static final Log log = LogFactory.getLog(ResultController.class);

	@Autowired
	private ResultService resultService;

	@Autowired
	private RaceService raceService;
	
	@Autowired
	private UtilityService utilityService;

	public ResultController() {
		super();
	}

	// UC-030
	@GetMapping(value = "/list/driver/{driver}/position/{position}")
	@ApiOperation(value = "Operación findByPositionAndDriver", notes = "Devuelve la lista de resultados en las que un piloto finalizó"
			+ " la carrera en una posición concreta")
	public Page<Result> findByPositionAndDriverAPI(@PathVariable(required = true) String driver,
												   @PathVariable(required = true) String position,
												   @RequestParam(defaultValue = "0", required = false) Integer offset,
												   @RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Result> results;
		Pageable pageable;
		
		try {
			pageable = this.utilityService.getPageable(limit, offset);

			results = this.resultService.findResultsByPositionAndDriverAPI(driver, position, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}

			throw new ApiRequestException("No se pudo recuperar los resultados" + " según el piloto y la posición", e);
		}

		return results;
	}

	// UC-031
	@GetMapping(value = "/list/driver/{driver}/grid/{grid}")
	@ApiOperation(value = "Operación findByGridAndDriver", notes = "Devuelve la lista de resultados en las que un piloto inició la"
			+ " carrera en una determinada posición")
	public Page<Result> findByGridAndDriverAPI(@PathVariable(required = true) String driver,
			@PathVariable(required = true) String grid,
			@RequestParam(defaultValue = "0", required = false) Integer offset,
			@RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Result> results;
		Pageable pageable;

		try {
			pageable = this.utilityService.getPageable(limit, offset);

			results = this.resultService.findResultsByGridAndDriverAPI(driver, grid, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}

			throw new ApiRequestException("No se pudo recuperar la lista de resultados según"
					+ " el piloto y la posición en la parrilla de salida", e);
		}

		return results;
	}

	// UC-032
	@GetMapping(value = "/list/constructor/{constructor}/position/{position}")
	@ApiOperation(value = "Operación findByPositionAndConstructor", notes = "Devuelve la lista de resultados en las que el monoplaza de"
			+ " cierta escudería finalizó la carrera en una posición")
	public Page<Result> findByPositionAndConstructorAPI(@PathVariable(required = true) String constructor,
														@PathVariable(required = true) String position,
														@RequestParam(defaultValue = "0", required = false) Integer offset,
														@RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Result> results;
		Pageable pageable;

		try {
			pageable = this.utilityService.getPageable(limit, offset);
			results = this.resultService.findResultsByPositionAndConstructorAPI(constructor, position, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}

			throw new ApiRequestException(
					"No se pudo recuperar la lista de resultados" + " para la escudería y posición seleccionada", e);
		}

		return results;
	}

	// UC-033
	@GetMapping(value = "/list/constructor/{constructor}/grid/{grid}")
	@ApiOperation(value = "Operación findByGridAndConstructor", notes = "Devuelve la lista de resultados en las que el monoplaza de una cierta"
			+ " escudería comenzó la carrera en una cierta posición")
	public Page<Result> findByGridAndConstructorAPI(@PathVariable(required = true) String constructor,
			@PathVariable(required = true) String grid,
			@RequestParam(defaultValue = "0", required = false) Integer offset,
			@RequestParam(defaultValue = "10", required = false) Integer limit) {
		Page<Result> results;
		Pageable pageable;

		try {
			pageable = this.utilityService.getPageable(limit, offset);

			results = this.resultService.findResultsByGridAndConstructorAPI(constructor, grid, pageable);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}

			throw new ApiRequestException("No se pudo recuperar la lista de resultados para una"
					+ " escudería y posición en la parrilla de salida", e);
		}

		return results;
	}

	// UC-034
	@GetMapping(value = "/count/driver/{driver}/position/{position}")
	@ApiOperation(value = "Operación findCountByPositionAndDriver",
	              notes = "Devuelve el número de veces en las que un piloto finalizó una carrera"
	              		+ " en una posición determinada")
	public Integer findCountByPositionAndDriverAPI(@PathVariable(required = true) String driver,
												   @PathVariable(required = true) String position) {
		Integer result;

		try {
			
			result = this.resultService.findCountByPositionAndDriverAPI(driver, position);
		
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}

			throw new ApiRequestException("No se pudo recuperar el número de veces en las que un piloto finalizó la carrera en cierta posición", e);
		}

		return result;
	}

	// UC-035 
	@GetMapping(value = "/count/driver/{driver}/grid/{grid}")
	@ApiOperation(value = "Operación findCountByGridAndDriver",
	              notes = "Devuelve el número de veces en las que un piloto inició una carrera"
	              		+ " en una posición determinada")
	public Integer findCountByGridAndDriverAPI(@PathVariable(required = true) String driver,
											   @PathVariable(required = true) String grid) {
		Integer result;

		try {
			
			result = this.resultService.findCountByGridAndDriverAPI(driver, grid);
		
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}

			throw new ApiRequestException("No se pudo recuperar el número de veces en las que un piloto inició la carrera en cierta posición", e);
		}

		return result;
	}
	
	// UC-036
	@GetMapping(value = "/count/constructor/{constructor}/position/{position}")
	@ApiOperation(value = "Operación findCountByPositionAndConstructor",
	              notes = "Devuelve el número de veces en las que una escudería terminó una carrera"
	              		+ " en una posición determinada")
	public Integer findCountByPositionAndConstructorAPI(@PathVariable(required = true) String constructor,
											            @PathVariable(required = true) String position) {
		Integer result;

		try {
			
			result = this.resultService.findCountByPositionAndConstructorAPI(constructor, position);
		
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}

			throw new ApiRequestException("No se pudo recuperar el número de veces en las que una escudería finalizó la carrera en cierta posición", e);
		}

		return result;
	}
	
	// UC-037
	@GetMapping(value = "/count/constructor/{constructor}/grid/{grid}")
	@ApiOperation(value = "Operación findCountByGridAndConstructor",
	              notes = "Devuelve el número de veces en las que una escudería inició una carrera"
	              		+ " en una posición determinada")
	public Integer findCountByGridAndConstructorAPI(@PathVariable(required = true) String constructor,
											        @PathVariable(required = true) String grid) {
		Integer result;

		try {
			
			result = this.resultService.findCountByGridAndConstructorAPI(constructor, grid);
		
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}

			throw new ApiRequestException("No se pudo recuperar el número de veces en las que una escudería inició la carrera en cierta posición", e);
		}

		return result;
	}
	
	// UC-038
	@GetMapping(value = "/count/driver/{driver}")
	@ApiOperation(value = "Operación findCountByDriver",
	              notes = "Devuelve el número de carreras en las que ha participado un piloto")
	public Integer findCountByDriverAPI(@PathVariable(required = true) String driver) {
		Integer result;

		try {
			
			result = this.resultService.findCountByDriverAPI(driver);
		
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}

			throw new ApiRequestException("No se pudo recuperar el número de carreras en las que ha participado un piloto", e);
		}

		return result;
	}
	
	// UC-039
	@GetMapping(value = "/count/constructor/{constructor}")
	@ApiOperation(value = "Operación findCountByConstructor",
	              notes = "Devuelve el número de carreras en las que ha participado una escudería")
	public Integer findCountByConstructorAPI(@PathVariable(required = true) String constructor) {
		Integer result;

		try {
			
			result = this.raceService.findCountByConstructorAPI(constructor);
		
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Mensaje de error: " + e.getMessage(), e);
			} else {
				log.info("Mensaje de error: " + e.getMessage());
			}

			throw new ApiRequestException("No se pudo recuperar el número de carreras en las que ha participado una escudería", e);
		}

		return result;
	}
	
}
