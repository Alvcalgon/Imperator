package com.fone.api.FOne.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fone.api.FOne.domain.Constructor;
import com.fone.api.FOne.domain.Driver;
import com.fone.api.FOne.domain.Result;
import com.fone.api.FOne.repositories.ResultRepository;

@Service
@Transactional
public class ResultService {

	private static final Log log = LogFactory.getLog(ResultService.class);
	
	@Autowired
	private ResultRepository resultRepository;
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private ConstructorService constructorService;
	
	
	public ResultService() {
		super();
	}
	
	
	// Consultas que aparecen en la API ------------------
	public Set<Driver> findDriversByConstructorAPI(String constructorName) {
		List<Result> results;
		Set<Driver> drivers;
		Map<String, Driver> mapa; 
		Driver driver;
		
		results = this.resultRepository.findDriversByConstructorAPI(constructorName);
		
		mapa = new HashMap<String, Driver>();
		for (Result r: results) {
			driver = r.getDriver();
			
			if (driver != null) {
				mapa.put(driver.getFullname(), driver);
			}
			
		}
		
		drivers = new HashSet<Driver>(mapa.values());
		
		return drivers;
	}
	
	public Set<Constructor> findConstructorsByDriverAPI(String driverFullname) {
		Set<Constructor> constructors;
		List<Result> results;
		Map<String, Constructor> mapa;
		Constructor constructor;
		
		results = this.resultRepository.findConstructorsByDriverAPI(driverFullname);
		
		mapa = new HashMap<String, Constructor>();
		for (Result r: results) {
			constructor = r.getConstructor();
			
			if (constructor != null) {
				mapa.put(constructor.getName(), constructor);
			}
			
		}
		
		constructors = new HashSet<Constructor>(mapa.values());
		
		return constructors;
	}
	
	public Page<Result> findResultsByPositionAndDriverAPI(String driverFullname, String position, Pageable pageable) {
		Page<Result> results;
		
		results = this.resultRepository.findResultsByPositionAndDriverAPI(driverFullname, position, pageable);
		
		return results;
	}
	
	public Page<Result> findResultsByGridAndDriverAPI(String driverFullname, String grid, Pageable pageable) {
		Page<Result> results;
		
		results = this.resultRepository.findResultsByGridAndDriverAPI(driverFullname, grid, pageable);
		
		return results;
	}
	
	public Page<Result> findResultsByPositionAndConstructorAPI(String constructorName, String position, Pageable pageable) {
		Page<Result> results;
		
		results = this.resultRepository.findResultsByPositionAndConstructorAPI(constructorName, position, pageable);
		
		return results;
	}
	
	public Page<Result> findResultsByGridAndConstructorAPI(String constructorName, String grid, Pageable pageable) {
		Page<Result> results;
		
		results = this.resultRepository.findResultsByGridAndConstructorAPI(constructorName, grid, pageable);
		
		return results;
	}
	
	public Integer findCountByPositionAndDriverAPI(String driverFullname, String position) {
		Integer result;
		
		result = this.resultRepository.findCountByPositionAndDriverAPI(driverFullname, position);
		
		return result;
	}
	
	public Integer findCountByGridAndDriverAPI(String driverFullname, String grid) {
		Integer result;
		
		result = this.resultRepository.findCountByGridAndDriverAPI(driverFullname, grid);
		
		return result;
	}
	
	public Integer findCountByPositionAndConstructorAPI(String constructorName, String position) {
		Integer result;
		
		result = this.resultRepository.findCountByPositionAndConstructorAPI(constructorName,
																			position);
		
		return result;
	}
	
	public Integer findCountByGridAndConstructorAPI(String constructorName, String grid) {
		Integer result;
		
		result = this.resultRepository.findCountByGridAndConstructorAPI(constructorName, grid);
		
		return result;
	}
	
	public Integer findCountByDriverAPI(String driver) {
		Integer result;
		
		result = this.resultRepository.findCountByDriverAPI(driver);
		
		return result;
	}
	
	public Result findOne(String resultId) {
		Result result;
		
		result = this.resultRepository.findById(resultId).get();
		
		return result;
	}
	
	public List<Result> findAll() {
		List<Result> results;
		
		results = this.resultRepository.findAll();
		
		return results;
	}
	
	public Result save(Result result) {
		Result res;
		
		res = this.resultRepository.save(result);
		
		return res;
	}
	
	public void delete(Result result) {
		this.resultRepository.delete(result);
	}
	
	// This method is used in RaceService::delete
	public void deleteAll(Collection<Result> results) {
		this.resultRepository.deleteAll(results);
	}
	
	// This method is used in RaceService::loadRacesAndResults
	public void deleteAll() {
		this.resultRepository.deleteAll();
	}
	
	
	public Set<Result> loadResults(Document doc) {
		Element driverTag, constructorTag, gridTag, statusTag, timeTag, lapsTag;
		String position, time, grid, status;
		Constructor constructor;
		Set<Result> results;
		Elements resultTags;
		Integer points, laps;
		Result result;
		Driver driver;
		
		results = new HashSet<Result>();
		
		if (doc != null) {
			try {
				resultTags = doc.select("Result");
				
				for (Element resultTag: resultTags) {
					position = resultTag.attr("positionText").trim();
					
					Double real_points = Double.valueOf(resultTag.attr("points"));
					points = real_points.intValue();
					
					driverTag = resultTag.selectFirst("Driver");
					driver = this.driverService.getDriver(driverTag);
					
					constructorTag = resultTag.selectFirst("Constructor");
					constructor = this.constructorService.getConstructor(constructorTag);
					
					gridTag = resultTag.selectFirst("Grid");
					grid = gridTag.text();
					
					lapsTag = resultTag.selectFirst("Laps");
					laps = Integer.getInteger(lapsTag.text());
					
					statusTag = resultTag.selectFirst("Status");
					status = statusTag.text();
					
					timeTag = resultTag.selectFirst("Time");
					time = (timeTag != null) ? timeTag.text() : "";
					
					result = new Result(position,
										time,
										laps,
										grid,
										points,
										status,
										driver,
										constructor); 
										
					results.add(result);
				}
				
			} catch (Exception e) {
				log.error("ResultService::loadResults: Error inesperado", e);
			}
		
			log.info("Resultados persistidos: " + results.size());
			
			this.resultRepository.saveAll(results);
		}
		
		return results;
	}

}
