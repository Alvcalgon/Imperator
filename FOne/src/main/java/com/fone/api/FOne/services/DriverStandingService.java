package com.fone.api.FOne.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.fone.api.FOne.domain.DriverStanding;
import com.fone.api.FOne.repositories.DriverStandingRepository;

@Service
@Transactional
public class DriverStandingService {

	private static final Log log = LogFactory.getLog(DriverStandingService.class);
	
	@Autowired
	private DriverStandingRepository driverStandingRepository;
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private ConstructorService constructorService;
	
	@Autowired
	private UtilityService utilityService;
	
	
	public DriverStandingService() {
		super();
	}
	
	// Consultas de la API
	public Page<DriverStanding> findBySeasonAPI(String season, Pageable pageable) {
		Page<DriverStanding> results;
		
		results = this.driverStandingRepository.findBySeasonAPI(season, pageable);
		
		return results;
	}
	
	public Page<DriverStanding> findByPositionAPI(String position, Pageable pageable) {
		Page<DriverStanding> results;
		
		results = this.driverStandingRepository.findByPositionAPI(position, pageable);
		
		return results;
	}
	
	public Page<DriverStanding> findByDriverAPI(String driver, Pageable pageable) {
		Page<DriverStanding> results;
		
		results = this.driverStandingRepository.findByDriverAPI(driver, pageable);
		
		return results;
	}
	
	public Integer findCountByDriverAPI(String driver) {
		Integer result;
		
		result = this.driverStandingRepository.findCountByDriverAPI(driver);
		
		return result;
	}
	
	public Integer findCountDriverAndPositionAPI(String driver, String position) {
		Integer result;
		
		result = this.driverStandingRepository.findCountDriverAndPositionAPI(driver, position);
		
		return result;
	}
	
	public Integer findDriversTitlesByConstructorAPI(String constructor) {
		Integer result;
		
		result = this.driverStandingRepository.findDriversTitlesByConstructorAPI(constructor);
		
		return result;
	}
	
	public DriverStanding findOne(String driverStandingId) {
		DriverStanding result;
		
		result = this.driverStandingRepository.findById(driverStandingId).get();
		
		return result;
	}
	
	public void saveDriver(String driverStandingId, String driverId) {
		Driver c;
		DriverStanding ds;
		
		c = this.driverService.findOneAPI(driverId);
		ds = this.findOne(driverStandingId);
		
		ds.setDriver(c);
		
		this.driverStandingRepository.save(ds);
	}
	
	public void saveConstructor(String driverStandingId, String constructorId) {
		Constructor c;
		DriverStanding ds;
		
		c = this.constructorService.findOne(constructorId);
		ds = this.findOne(driverStandingId);
		
		ds.setConstructor(c);
		
		this.driverStandingRepository.save(ds);
	}
	
	public void deleteAll() {
		this.driverStandingRepository.deleteAll();
	}
	
	// Metodo principal para web scraping ------------------------
	public void loadDriverStandings() {
		this.driverStandingRepository.deleteAll();
		
		Map<String, String> seasons;
		List<DriverStanding> driversStanding;
		DriverStanding driverStanding;
		Elements standingTags;
		Element driverTag, constructorTag;
		String position;
		Integer wins;
		Double points;
		String url;
		Driver driver;
		Constructor constructor;
		
		driversStanding = new ArrayList<DriverStanding>();
		seasons = this.getSeasons(1950, 2019);

		for (String season: seasons.keySet()) {
			url = seasons.get(season);
			
			Document doc = this.utilityService.getDocument(url);
			
			if (doc != null) {
				try {
					standingTags = doc.select("DriverStanding");
					
					for (Element standing: standingTags) {
						position = standing.attr("positionText").trim();
						points = Double.valueOf(standing.attr("points"));
						wins = Integer.valueOf(standing.attr("wins"));
						
						driverTag = standing.selectFirst("Driver");
						driver = this.driverService.getDriver(driverTag);
						
						constructorTag = standing.selectFirst("Constructor");
						constructor = this.constructorService.getConstructor(constructorTag);
						
						driverStanding = new DriverStanding(season,
															points,
															position,
															wins,
															driver,
															constructor);
						
						log.info("Driver standing: " + season + " - " + driver.getFullname());
						
						driversStanding.add(driverStanding);
					}
					
				} catch (Exception e) {
					log.error("DriverStandingService::loadDriverStandings: Error inesperado");
				}
			}
			
			this.driverStandingRepository.saveAll(driversStanding);
		}
	}
			
	private Map<String, String> getSeasons(int seasonStart, int seasonEnd) {
		Map<String, String> results;
		String link, str_season;
		int season;
		
		results = new HashMap<String, String>();
		
		season = seasonStart;
		while (season <= seasonEnd) {
			str_season = String.valueOf(season);
			link = "http://ergast.com/api/f1/" + season + "/driverStandings?limit=120";
			
			results.put(str_season, link);
			
			season++;
		}	
	
		return results;
	}
}
