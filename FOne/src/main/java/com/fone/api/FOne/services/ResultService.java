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
	
	public Set<Result> loadResultsByRace(Document document) {
		Element div, table, tbody, th, td, a;
		Elements ls_tr, ls_a;
		String position, time, str_laps, grid, str_points, driverFullname, constructorName;
		Integer laps, points;
		Driver driver;
		Constructor constructor;
		Result result;
		Set<Result> results;
		
		results = new HashSet<Result>();
		
		try {
			div = document.selectFirst("div#msr_result");
			
			table = div.selectFirst("table.motor-sport-results.msr_result");
			
			tbody = table.selectFirst("tbody");
			
			ls_tr = tbody.select("tr");
			for (Element tr: ls_tr) {
				try {
					th = tr.selectFirst("th.msr_col1");
					position = th.text().trim();
					
					td = tr.selectFirst("td.msr_col5");
					time = td.text().trim();
					
					td = tr.selectFirst("td.msr_col6");
					str_laps = td.text().trim();
					laps = Integer.valueOf(str_laps);
					
					td = tr.selectFirst("td.msr_col8");
					grid = td.text().trim();
					
					td = tr.selectFirst("td.msr_col9");
					str_points = td.text().trim();
					points = Integer.valueOf(str_points);
					
					td = tr.selectFirst("td.msr_col3");
					ls_a = td.select("a");
					
					a = (ls_a.size() == 2) ? ls_a.get(1) : null;
					if (a != null) {
						driverFullname = a.text().trim();
						
						driver = this.driverService.findByFullname2(driverFullname);
					} else {
						driver = null;
					}
					
					td = tr.selectFirst("td.msr_col4");
					ls_a = td.select("a");
					
					a = (ls_a.size() == 2) ? ls_a.get(1) : null;
					if (a != null) {
						constructorName = a.text().trim();
						constructor = this.constructorService.findByName(constructorName);
					} else {
						constructor = null;
					}
					
					result = new Result(position, time, laps, grid, points, driver, constructor);	
					results.add(result);
					
					log.info("Resultado: " + result.getResultId());
				} catch (Exception ex) {
					log.info("Error al obtener un resultado de una carrera");
				}
				
			}
			
		} catch (Exception e) {
			log.info("No se pudieron obtener los resultados de la carrera");
		}
		
		this.resultRepository.saveAll(results);
		
		return results;
	}
	
}
