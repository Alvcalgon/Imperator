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
		log.info("------------------ Cargando Drivers standing -----------------------");
		
		Map<String, String> seasons;
		List<DriverStanding> driversStanding;
		String url;
		Document document;
		
		seasons = this.getSeasons(1950, 2018);
		
		driversStanding = new ArrayList<DriverStanding>();
		for (String season: seasons.keySet()) {
			url = seasons.get(season);
			
			document = this.utilityService.getDocument(url);
			
			if (document != null) {
				driversStanding.addAll(this.getDriversStanding(document, season));
				
				log.info("Obtenido resumen de pilotos de la temporada " + season);
			}
			
			this.driverStandingRepository.saveAll(driversStanding);
		}
	}
	
	protected List<DriverStanding> getDriversStanding(Document document, String season) {
		List<DriverStanding> results;
		Element div, table, tbody, td, a;
		Elements ls_tr;
		String position, driverName, aText, constructorName, points;
		Driver driver;
		Constructor constructor;
		DriverStanding driverStanding;
		
		position = "0";
		points = "-1";
		driver = null;
		constructor = null;
		
		results = new ArrayList<DriverStanding>();
		try {
			div = document.selectFirst("div.resultsarchive-content");
			
			table = div.selectFirst("table.resultsarchive-table");
			
			tbody = table.selectFirst("tbody");
			
			ls_tr = tbody.select("tr");
		
			for (Element tr: ls_tr) {
				try {
					td = tr.selectFirst("td.dark");
					position = td.text().trim();
					
					a = tr.selectFirst("a.dark.bold.ArchiveLink");
					aText = a.text().trim();
					driverName = this.findDriverName(aText);
					driver = this.driverService.findByFullname2(driverName);
					
					a = tr.selectFirst("a.grey.semi-bold.uppercase.ArchiveLink");
					constructorName = a.text().trim();
					constructor = this.constructorService.findByName(constructorName);
					
					td = tr.selectFirst("td.dark.bold");
					points = td.text().trim();
					
					log.info("Driver standing + (" + season + "): " + position + " - " + points);
				} catch (Exception ee) {
					log.info("Datos parciales 1");
					
				}
				
				driverStanding = new DriverStanding(season,
						Integer.valueOf(points),
						position,
						driver,
						constructor);

				results.add(driverStanding);
			}
		
		} catch (Exception e) {
			log.info("Datos parciales 2");
			
			driverStanding = new DriverStanding(season, Integer.valueOf(points), position, driver, constructor);
			results.add(driverStanding);
		}
		
		return results;
	}
	
	private Map<String, String> getSeasons(int seasonStart, int seasonEnd) {
		Map<String, String> results;
		String link, str_season;
		int season;
		
		results = new HashMap<String, String>();
		
		season = seasonStart;
		while (season <= seasonEnd) {
			str_season = String.valueOf(season);
			link = "https://www.formula1.com/en/results.html/" + str_season + "/drivers.html";
			
			results.put(str_season, link);
			
			season++;
		}	
	
		return results;
	}
	
	private String findDriverName(String driverName) {
		String result;
		String[] fields;
		int n;
		
		fields = driverName.split(" ");
		n = fields.length;
		n--;
		
		result = "";
		for (int i=0; i<n; i++) {
			result = result + fields[i] + " ";
		}
		
		return result.trim();
	}
	
}
