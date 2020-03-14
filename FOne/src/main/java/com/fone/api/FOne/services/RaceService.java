package com.fone.api.FOne.services;

import java.util.ArrayList;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fone.api.FOne.domain.Circuit;
import com.fone.api.FOne.domain.Constructor;
import com.fone.api.FOne.domain.Driver;
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

//	@Autowired
//	private MongoTemplate mongoTemplate;

	public RaceService() {
		super();
	}

	// Consultas disponibles en la API ---------------------------------
	public Race findOneAPI(String raceId) {
		Race result;

		result = this.raceRepository.findById(raceId).get();

		return result;
	}

	// UC-003
	public Set<Driver> findDriversBySeasonAPI(String season) {
		Map<String, Driver> mapa;
		Set<Driver> results;
		List<Race> races;
		Driver driver;
		
		
		races = this.findBySeason(season);
		
		mapa = new HashMap<String, Driver>();
		for (Race r: races) {
			for (Result res: r.getResults()) {
				driver = res.getDriver();
				
				if (driver != null) {
					mapa.put(driver.getFullname(), driver);
				}
				
			}
		}
		
		results = new HashSet<Driver>(mapa.values());
		
		return results;
	}
	
	// UC-008
	public Set<Constructor> findConstructorsBySeasonAPI(String season) {
		Map<String, Constructor> mapa;
		Set<Constructor> results;
		List<Race> races;
		Constructor constructor;
		
		races = this.findBySeason(season);
		
		mapa = new HashMap<String, Constructor>();
		for (Race r: races) {
			for (Result res: r.getResults()) {
				constructor = res.getConstructor();
				
				if (constructor != null) {
					mapa.put(constructor.getName(), constructor);
				}
				
			}
		}
		
		results = new HashSet<Constructor>(mapa.values());
		
		return results;
	}
	
	// UC-014
	public List<Circuit> findCircuitsBySeasonAPI(String season) {
		List<Circuit> results;
		List<Race> races;
		
		races = this.findBySeason2(season);
		
		results = new ArrayList<Circuit>();
		for (Race r: races) {
			results.add(r.getCircuit());
		}
		
		return results;
	}
	
	// UC-016
	public Page<Race> findBySeasonAPI(String season, Pageable pageable) {
		Page<Race> results;

		results = this.raceRepository.findBySeasonAPI(season, pageable);

		return results;
	}
	
	// UC-017
	public Page<Race> findByCircuitAPI(String circuitName, Pageable pageable) {
		Page<Race> results;
		
		results = this.raceRepository.findByCircuitAPI(circuitName, pageable);

		return results;
	}

	// UC-018
	public Page<Race> findRacesByDriverAPI(String driverFullname, Pageable pageable) {
		Page<Race> results;
		
		results = this.raceRepository.findRacesByDriverAPI(driverFullname, pageable);
		
		return results;
	}
	
	// UC-019
	public Page<Race> findRacesByConstructorAPI(String constructorName, Pageable pageable) {
		Page<Race> results;

		results = this.raceRepository.findRacesByConstructorAPI(constructorName, pageable);
		
		return results;
	}
	
	// UC-020
	public Page<Race> findRacesByDriverAndSeasonAPI(String driverFullname, String season, Pageable pageable) {
		Page<Race> results;

		results = this.raceRepository.findRacesByDriverAndSeasonAPI(driverFullname, season, pageable);
		
		return results;
	}
	
	// UC-021
	public Page<Race> findRacesByConstructorAndSeasonAPI(String constructorName, String season, Pageable pageable) {
		Page<Race> results;
		
		results = this.raceRepository.findRacesByConstructorAndSeasonAPI(constructorName, season, pageable);
		
		return results;
	}
	
	public Page<Race> findRaceByEventAPI(String event, Pageable pageable) {
		Page<Race> results;

		results = this.raceRepository.findRaceByEventAPI(event, pageable);

		return results;
	}
	
	// UC-022
	public Page<Race> findRaceBySeasonAndEventAPI(String event, String season, Pageable pageable) {
		Page<Race> results;

		results = this.raceRepository.findRaceBySeasonAndEventAPI(event, season, pageable);

		return results;
	}

	public Race findOneBySeasonAndEventAPI(String event, String season) {
		Race result;

		result = this.raceRepository.findOneBySeasonAndEventAPI(event, season);

		return result;
	}
	
	protected List<Race> findAll() {
		List<Race> results;

		results = this.raceRepository.findAll();

		return results;
	}
	
	// Metodo principal para web scraping ------------------------------
	public void loadRacesAndResults() {
		log.info("Cargando las carreras y resultados en la BD");

		Map<String, String> seasons;
		Set<String> seasonKeys;
		Document doc;

		seasons = this.getAllSeasons();
		seasonKeys = seasons.keySet();

		if (!seasons.isEmpty()) {
			for (String season : seasonKeys) {
				log.info("Temporada por insertar: " + season);

				doc = this.utilityService.getDocument(seasons.get(season));

				if (doc != null) {
					this.loadRacesBySeason(doc, season);
				}

				log.info("Temporada ya insertada: " + season);
			}
		}

	}

	// Metodos auxiliares
	public void loadRacesBySeason(Document document, String season) {
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
			for (Element tr : ls_tr) {
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
					log.info("Error al recuperar la carrera");
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

		log.info("Carrera: " + result.getRaceId() + " " + season);

		results = this.resultService.loadResultsByRace(document);
		result.setResults(results);

		return result;
	}

	private Map<String, String> getAllSeasons() {
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
				for (Element tr : ls_tr) {
					ls_td = tr.select("td");

					for (Element td : ls_td) {
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

	public Race save(Race race) {
		Race result;
		
		result = this.raceRepository.save(race);
		
		return result;
	}
	
	private List<Race> findBySeason(String season) {
		List<Race> results;
		
		results = this.raceRepository.findBySeason(season);
		
		return results;
	}
	
	private List<Race> findBySeason2(String season) {
		List<Race> results;
		
		results = this.raceRepository.findBySeason2(season);
		
		return results;
	}
	
}
