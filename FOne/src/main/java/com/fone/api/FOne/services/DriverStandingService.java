package com.fone.api.FOne.services;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.limit;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

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
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fone.api.FOne.domain.Constructor;
import com.fone.api.FOne.domain.Driver;
import com.fone.api.FOne.domain.DriverStanding;
import com.fone.api.FOne.domain.DriverTitle;
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
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
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
		
	public List<DriverTitle> findDriversTitle() {
		Aggregation aggregation = newAggregation(match(Criteria.where("position").is("1")),
												 group("driver.fullname").count().as("titles"),
				                                 project("titles").and("driverFullname").previousOperation(),
				                                 sort(Direction.DESC, "titles"),
				                                 limit(10));
		
		AggregationResults<DriverTitle> results = this.mongoTemplate.aggregate(aggregation, "DriverStanding", DriverTitle.class);
		
		List<DriverTitle> driversTitle = results.getMappedResults();
		
		return driversTitle;
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
		Driver c = this.driverService.findOneAPI(driverId);
		
		DriverStanding ds = this.findOne(driverStandingId);
		ds.setDriver(c);
		
		this.driverStandingRepository.save(ds);
	}
	
	public void saveConstructor(String driverStandingId, String constructorId) {		
		Constructor c = this.constructorService.findOne(constructorId);
		
		DriverStanding ds = this.findOne(driverStandingId);
		
		ds.setConstructor(c);
		
		this.driverStandingRepository.save(ds);
	}
	
	public void deleteAll() {
		this.driverStandingRepository.deleteAll();
	}
	
	// Metodo principal para web scraping ------------------------
	public void loadDriverStandings() {
		this.driverStandingRepository.deleteAll();

		List<DriverStanding> driversStanding = new ArrayList<DriverStanding>();
		
		Map<String, String> seasons = this.getSeasons(1950, 2019);
		for (String season: seasons.keySet()) {
			String url = seasons.get(season);
			
			Document doc = this.utilityService.getDocument(url);
			
			if (doc != null) {
				try {
					Elements standingTags = doc.select("DriverStanding");
					
					for (Element standing: standingTags) {
						String position = standing.attr("positionText").trim();
						Double points = Double.valueOf(standing.attr("points"));
						Integer wins = Integer.valueOf(standing.attr("wins"));
						
						Element driverTag = standing.selectFirst("Driver");
						Driver driver = this.driverService.getDriver(driverTag);
						
						Element constructorTag = standing.selectFirst("Constructor");
						Constructor constructor = this.constructorService.getConstructor(constructorTag);
						
						DriverStanding driverStanding = new DriverStanding(season, points,
																		   position, wins,
																		   driver, constructor);
						
						log.info("Driver standing: " + season + " - " + driver.getFullname());
						
						driversStanding.add(driverStanding);
					}
					
				} catch (Exception e) {
					log.error("Error inesperado en driver standing");
				}
			}
			
			this.driverStandingRepository.saveAll(driversStanding);
		}
	}
			
	private Map<String, String> getSeasons(int seasonStart, int seasonEnd) {
		Map<String, String> results = new HashMap<String, String>();
		
		int season = seasonStart;
		while (season <= seasonEnd) {
			String str_season = String.valueOf(season);
			String link = "http://ergast.com/api/f1/" + season + "/driverStandings?limit=120";
			
			results.put(str_season, link);
			
			season++;
		}	
	
		return results;
	}
}
