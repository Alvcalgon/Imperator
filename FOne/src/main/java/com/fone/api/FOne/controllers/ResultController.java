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
	private UtilityService utilityService;
	
	public ResultController() {
		super();
	}

	// UC-034
	@GetMapping(value = "/list/driver/{driver}/position/{position}")
	@ApiOperation(value = "Operación findByPositionAndDriver",
    			  notes = "Devuelve la lista de resultados en las que un piloto finalizó"
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
			
			throw new ApiRequestException("No se pudo recuperar los resultados"
					+ " según el piloto y la posición", e);
		}

		return results;
	}

	// UC-035
	@GetMapping(value = "/list/driver/{driver}/grid/{grid}")
	@ApiOperation(value = "Operación findByGridAndDriver",
    			  notes = "Devuelve la lista de resultados en las que un piloto inició la"
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

	// UC-036
	@GetMapping(value = "/list/constructor/{constructor}/position/{position}")
	@ApiOperation(value = "Operación findByPositionAndConstructor",
    			 notes = "Devuelve la lista de resultados en las que el monoplaza de"
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
			
			throw new ApiRequestException("No se pudo recuperar la lista de resultados"
					+ " para la escudería y posición seleccionada", e);
		}

		return results;
	}

	// UC-037
	@GetMapping(value = "/list/constructor/{constructor}/grid/{grid}")
	@ApiOperation(value = "Operación findByGridAndConstructor",
    			  notes = "Devuelve la lista de resultados en las que el monoplaza de una cierta"
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

}
