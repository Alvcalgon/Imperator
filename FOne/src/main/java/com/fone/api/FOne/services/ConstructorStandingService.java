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
import com.fone.api.FOne.domain.ConstructorStanding;
import com.fone.api.FOne.repositories.ConstructorStandingRepository;

@Service
@Transactional
public class ConstructorStandingService {

	private static final Log log = LogFactory.getLog(ConstructorStandingService.class);
	
	@Autowired
	private ConstructorStandingRepository constructorStandingRepository;
	
	@Autowired
	private ConstructorService constructorService;
	
	@Autowired
	private UtilityService utilityService;
	
	
	public ConstructorStandingService() {
		super();
	}
	
	// Consultas en la API
	public Page<ConstructorStanding> findBySeasonAPI(String season, Pageable pageable) {
		Page<ConstructorStanding> results;
		
		results = this.constructorStandingRepository.findBySeasonAPI(season, pageable);
		
		return results;
	}
	
	public Page<ConstructorStanding> findByPositionAPI(String position, Pageable pageable) {
		Page<ConstructorStanding> results;
		
		results = this.constructorStandingRepository.findByPositionAPI(position, pageable);
		
		return results;
	}
	
	public Page<ConstructorStanding> findByConstructorAPI(String constructor, Pageable pageable) {
		Page<ConstructorStanding> results;
		
		results = this.constructorStandingRepository.findByConstructorAPI(constructor, pageable);
		
		return results;
	}
	
	
	public Integer findCountByConstructorAPI(String constructor) {
		Integer result;
		
		result = this.constructorStandingRepository.findCountByConstructorAPI(constructor);
		
		return result;
	}
	
	
	public Integer findCountByConstructorAndPositionAPI(String constructor, String position) {
		Integer result;
		
		result = this.constructorStandingRepository.findCountByConstructorAndPositionAPI(constructor, position);
		
		return result;
	}
	
	public List<ConstructorStanding> findAll() {
		List<ConstructorStanding> results;
		
		results = this.constructorStandingRepository.findAll();
		
		return results;
	}
	
	public ConstructorStanding findOne(String constructorStandingId) {
		ConstructorStanding result;
		
		result = this.constructorStandingRepository.findById(constructorStandingId).get();
		
		return result;
	}
	
	public void save(String constructorStandingId, String constructorId) {
		Constructor c;
		ConstructorStanding cs;
		
		c = this.constructorService.findOne(constructorId);
		cs = this.findOne(constructorStandingId);
		
		cs.setConstructor(c);
		
		this.constructorStandingRepository.save(cs);
	}
	
	public void loadConstructorStandings() {
		//this.constructorStandingRepository.deleteAll();
		
		List<ConstructorStanding> constructorsStanding;
		ConstructorStanding constructorStanding;
		Map<String, String> seasons;
		Elements standingTags;
		Element constructorTag;
		Constructor constructor;
		String url;
		String position;
		Integer points, wins;
		
		constructorsStanding = new ArrayList<ConstructorStanding>();
		seasons = this.getSeasons(2009, 2009);
		
		for (String season: seasons.keySet()) {
			url = seasons.get(season);
			
			Document doc = this.utilityService.getDocument(url);
			
			if (doc != null) {
				try {
					standingTags = doc.select("ConstructorStanding");
					
					for (Element standing: standingTags) {
						position = standing.attr("positionText").trim();
						
						Double real_points = Double.valueOf(standing.attr("points"));
						points = real_points.intValue();
						
						wins = Integer.valueOf(standing.attr("wins"));
						
						constructorTag = standing.selectFirst("Constructor");
						constructor = this.constructorService.getConstructor(constructorTag);
						
						constructorStanding = new ConstructorStanding(season,
																	  position,
																	  points,
																	  wins,
																	  constructor);
						
						log.info("Constructor standing: " + season + " - " + constructor.getName());
						
						constructorsStanding.add(constructorStanding);
					}
					
				} catch (Exception e) {
					log.error("ConstructorStandingService::loadConstructorStanding: error inesperado");
				}
			}
		}
		
		this.constructorStandingRepository.saveAll(constructorsStanding);
	}
	
	private Map<String, String> getSeasons(int seasonStart, int seasonEnd) {
		Map<String, String> results;
		String link, str_season;
		int season;
		
		results = new HashMap<String, String>();
		
		season = seasonStart;
		while (season <= seasonEnd) {
			str_season = String.valueOf(season);
		
			link = "http://ergast.com/api/f1/" + str_season + "/constructorStandings";
			
			results.put(str_season, link);
			
			season++;
		}	
	
		return results;
	}
	
}
