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
		log.info("---------------- Cargando Constructor standing ------------------");
		
		Map<String, String> seasons;
		List<ConstructorStanding> constructorsStanding;
		String url;
		Document document;
		
		seasons = this.getSeasons(1991, 2018);
		
		constructorsStanding = new ArrayList<ConstructorStanding>();
		for (String season: seasons.keySet()) {
			url = seasons.get(season);
			
			document = this.utilityService.getDocument(url);
			
			if (document != null) {
				constructorsStanding.addAll(this.getConstructorStanding(document, season));
			
				log.info("Obtenido resumen de escuderías de la temporada " + season);
			}
			
			this.constructorStandingRepository.saveAll(constructorsStanding);
		}
		
	}
	
	protected List<ConstructorStanding> getConstructorStanding(Document document, String season) {
		List<ConstructorStanding> results;
		Element div, table, tbody, td, a;
		Elements ls_tr;
		String position, points, constructorName;
		Constructor constructor;
		ConstructorStanding constructorStanding;
		
		position = "";
		points = "";
		constructor = null;
		
		results = new ArrayList<ConstructorStanding>();
		try {
			div = document.selectFirst("div.resultsarchive-content");
			
			table = div.selectFirst("table.resultsarchive-table");
			
			tbody = table.selectFirst("tbody");
			
			ls_tr = tbody.select("tr");

			for (Element tr: ls_tr) {
				try {
					td = tr.selectFirst("td.dark");
					position = td.text().trim();
					
					a = tr.selectFirst("a.dark.bold.uppercase.ArchiveLink");
					constructorName = a.text().trim();
					constructor = this.constructorService.findByName(constructorName);
					
					if (constructor == null) {
						log.info(" --- Escudería no obtenida ---");
					}
					
					td = tr.selectFirst("td.dark.bold");
					points = td.text().trim();
					
					constructorStanding = new ConstructorStanding(season, position, points, constructor);
					results.add(constructorStanding);
					
					log.info("Constructor standing (" + season + "): " + position + " - " + points);
				} catch (Exception ee) {
					log.info("Datos parciales 1");
					
					constructorStanding = new ConstructorStanding(season, position, points, constructor);
					results.add(constructorStanding);
				}
				
			}
			
		} catch (Exception e) {
			log.info("Datos parciales 2");
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
			link = "https://www.formula1.com/en/results.html/" + str_season + "/team.html";
			
			results.put(str_season, link);
			
			season++;
		}	
	
		return results;
	}
	
}
