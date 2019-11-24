package com.fone.api.FOne.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
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
						
						driver = this.driverService.findByFullname(driverFullname);
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
