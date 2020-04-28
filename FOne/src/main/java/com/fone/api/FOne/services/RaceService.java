package com.fone.api.FOne.services;

import java.util.ArrayList;
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
	
	public Page<Race> findBySeasonAPI(String season, Pageable pageable) {
		Page<Race> results;

		results = this.raceRepository.findBySeasonAPI(season, pageable);

		return results;
	}
	
	public Page<Race> findByCircuitAPI(String circuitName, Pageable pageable) {
		Page<Race> results;
		
		results = this.raceRepository.findByCircuitAPI(circuitName, pageable);

		return results;
	}

	public Page<Race> findRacesByDriverAPI(String driverFullname, Pageable pageable) {
		Page<Race> results;
		
		results = this.raceRepository.findRacesByDriverAPI(driverFullname, pageable);
		
		return results;
	}
	
	public Page<Race> findRacesByConstructorAPI(String constructorName, Pageable pageable) {
		Page<Race> results;

		results = this.raceRepository.findRacesByConstructorAPI(constructorName, pageable);
		
		return results;
	}
	
	public Page<Race> findRacesByDriverAndSeasonAPI(String driverFullname, String season, Pageable pageable) {
		Page<Race> results;

		results = this.raceRepository.findRacesByDriverAndSeasonAPI(driverFullname, season, pageable);
		
		return results;
	}
	
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
	
	public Integer findCountByConstructorAPI(String constructor) {
		Integer result;
		
		result = this.raceRepository.findCountByConstructorAPI(constructor);
		
		return result;
	}
	
	protected List<Race> findAll() {
		List<Race> results;

		results = this.raceRepository.findAll();

		return results;
	}
	
	// Metodo principal para web scraping ------------------------------
	public void loadRacesAndResults() {
		this.raceRepository.deleteAll();
		this.resultService.deleteAll();
		
		String raceDate, event, info;
		Map<String, String> seasons;
		Set<Result> results;
		Elements raceTags;
		Element circuitTag, eventTag, dateTag;
		Circuit circuit;
		List<Race> races;
		String url, round, urlToResults;
		Race race;
		
		races = new ArrayList<Race>();
		seasons = this.getSeasons(2009, 2019);
		
		for (String season: seasons.keySet()) {
			url = seasons.get(season);
			
			Document doc = this.utilityService.getDocument(url);
			
			if (doc != null) {
				try {
					raceTags = doc.select("Race");
					
					for (Element raceTag: raceTags) {
						info = raceTag.attr("url");
						round = raceTag.attr("round");
						
						eventTag = raceTag.selectFirst("RaceName");
						event = eventTag.text().trim();
						
						dateTag = raceTag.selectFirst("Date");
						raceDate = dateTag.text().trim();
						
						circuitTag = raceTag.selectFirst("Circuit");
						circuit = this.circuitService.getCircuit(circuitTag);
						
						urlToResults = "http://ergast.com/api/f1/" + season + "/"
								+ round + "/" + "results";
						
						Document subDoc = this.utilityService.getDocument(urlToResults);
						
						results = this.resultService.loadResults(subDoc);
						
						race = new Race(season, raceDate, event, info, circuit, results);
						
						log.info(race.toString());
						
						races.add(race);
					}
					
				} catch (Exception e) {
					log.error("RaceService::loadRaces: Error inesperado", e);
				}
			}
		}
		
		this.raceRepository.saveAll(races);
	}

	private Map<String, String> getSeasons(int seasonStart, int seasonEnd) {
		Map<String, String> results;
		String link, str_season;
		int season;
		
		results = new HashMap<String, String>();
		
		season = seasonStart;
		while (season <= seasonEnd) {
			str_season = String.valueOf(season);
	
			link = "http://ergast.com/api/f1/" + season;
			
			results.put(str_season, link);
			
			season++;
		}	
	
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
