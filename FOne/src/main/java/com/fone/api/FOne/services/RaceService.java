package com.fone.api.FOne.services;

import java.util.Date;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fone.api.FOne.domain.Circuit;
import com.fone.api.FOne.domain.Race;
import com.fone.api.FOne.domain.Result;
import com.fone.api.FOne.repositories.RaceRepository;

@Service
@Transactional
public class RaceService {

	private static final Log log = LogFactory.getLog(RaceService.class);
	
	@Autowired
	private RaceRepository raceRepository;
	
	@Autowired
	private CircuitService circuitService;
	
	@Autowired
	private ResultService resultService;
	
	@Autowired
	private UtilityService utilityService;
	
	
	public RaceService() {
		super();
	}
	
	public Race findOne(String raceId) {
		Race result;
		
		result = this.raceRepository.findById(raceId).get();
		
		return result;
	}
	
	public List<Race> findAll() {
		List<Race> results;
		
		results = this.raceRepository.findAll();
		
		return results;
	}
	
	public Race save(Race race) {
		Race result;
		
		result = this.raceRepository.save(race);
		
		return result;
	}
	
	public void delete(Race race) {
		this.raceRepository.delete(race);
	}
	
	public List<Race> findByCircuit(String circuitId) {
		List<Race> results;
		
		results = this.raceRepository.findByCircuit(circuitId);
		
		return results;
	}

	public List<Race> findBySeason(String season) {
		List<Race> results;
		
		results = this.raceRepository.findBySeason(season);
		
		return results;
	}
	
	public void loadRacesAndResults() {
		log.info("Cargando las carreras y resultados en la BD");
		
		Map<String, String> seasons;
		Set<String> seasonKeys;
		Document doc;
		
		seasons = this.getAllSeasons();
		seasonKeys = seasons.keySet();
		
		if (!seasons.isEmpty()) {
			for (String season: seasonKeys) {
				doc = this.utilityService.getDocument(seasons.get(season));
				
				if (doc != null) {
					this.loadRacesBySeason(doc, season);
				}
				
			}
		}
		
	}
	
	public void loadRacesBySeason(Document document, String season) {
		log.info("Carreras y sus resultados de " + season);
		
		Document doc;
		Element table, tbody, td_href, td_raceDate, a;
		Elements ls_tr;
		Date raceDate;
		String href, monthDay;
		Race race;
		Set<Race> races;
		
		races = new HashSet<Race>();
		
		try {
			table = document.selectFirst("table.motor-sport-results.msr_season_summary.tablesorter");
			
			tbody = table.selectFirst("tbody");
			
			ls_tr = tbody.select("tr");
			for (Element tr: ls_tr) {
				try {
					td_href = tr.selectFirst("td.msr_col1");
					td_raceDate = tr.selectFirst("td.msr_col2");
					
					monthDay = td_raceDate.text().trim();
					raceDate = this.utilityService.getDateByParameters(season, monthDay);
					
					a = td_href.selectFirst("a");
					
					// Este enlace contiene los datos Race::results, Race::event y Race::circuit
					href = a.attr("href").trim();
					doc = this.utilityService.getDocument(href);
					if (doc != null) {
						race = this.getRace(doc, season, raceDate);
						
						races.add(race);
					}
				} catch (Exception ex) {
					log.info("Error al recuperar una carrera concreta");
				}
			}
			
		} catch (Exception e) {
			log.info("Error inesperado recuperando las carreras de la temporada " + season);
		}
		
		this.raceRepository.saveAll(races);
		
		log.info("Carreras obtenidas: " + races.size());
	}
	
	private Race getRace(Document document, String season, Date raceDate) {
		Race result;
		Element div_parent, div_child, p, a_event, a_circuit;
		
		Elements ls_a;
		String event = "", circuitName; 
		Circuit circuit = null;
		Set<Result> results;
		
		try {
			div_parent = document.selectFirst("div.post-content");
			div_child = div_parent.selectFirst("div.entry.clearfix");
			
			p = div_child.selectFirst("p");
			ls_a = p.select("a");
			
			if (ls_a.size() >= 2) {
				a_event = ls_a.get(0);
				a_circuit = ls_a.get(1);
			
				event = a_event.text().trim();
				circuitName = a_circuit.text().trim();
			
				circuit = this.circuitService.findByName(circuitName);
			}
			
		 result = new Race(season, raceDate, event, circuit);
		} catch (Exception e) {
			result = new Race(season, raceDate);
			
			log.info("No se encontraron todos los datos de la carrera");
		}
		
//		results = this.resultService.loadResultsByRace(document);
//		result.setResults(results);
		
		log.info("Carrera: " + raceDate.getTime() + " " + season);
		
		return result;
	}
	
	protected Map<String, String> getAllSeasons() {
		Map<String, String> results;
		Document document;
		Elements ls_tr, ls_td;
		Element table, tbody, a;
		String href, season;
		
		results = new HashMap<String, String>();
		
		document = this.utilityService.getDocument("https://www.f1-fansite.com/f1-results/");
		if (document != null) {
			try {
				table = document.selectFirst("table.motor-sport-results.msr_seasons");
				
				tbody = table.selectFirst("tbody");
				
				ls_tr = tbody.select("tr");
				for (Element tr: ls_tr) {
					ls_td =tr.select("td");
					
					for (Element td: ls_td) {
						a = td.selectFirst("a");
						
						href = a.attr("href").trim();
						season = a.text().trim();
						
						results.put(season, href);
					}
				}
				
			} catch (Exception e) {
				log.info("Error inesperado al recuperar las temporadas: " + e.getMessage());
			}
		}
		
		log.info("Numero de temporadas: " + results.size());
		
		return results;
	}
	
}
