package com.fone.api.FOne.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import com.fone.api.FOne.repositories.CircuitRepository;

@Service
@Transactional
public class CircuitService {

	private static final Log log = LogFactory.getLog(CircuitService.class); 
	
	@Autowired
	private CircuitRepository circuitRepository;
	
	@Autowired
	private UtilityService utilityService;
	
	
	public CircuitService() {
		super();
	}
	
	public Circuit findOne(String circuitId) {
		Circuit result;
		
		result = this.circuitRepository.findById(circuitId).get();
		
		return result;
	}
	
	public Page<Circuit> findAllAPI(Pageable pageable) {
		Page<Circuit> result;
		
		result = this.circuitRepository.findAll(pageable);
		
		return result;
	}
		
	public Page<Circuit> findByLocationAPI(String location, Pageable pageable) {
		Page<Circuit> results;
		
		results = this.circuitRepository.findByLocationAPI(location, pageable);
		
		return results;
	}
	
	public Page<Circuit> findByNameAPI(String name, Pageable pageable) {
		Page<Circuit> results;
		
		results = this.circuitRepository.findByNameAPI(name, pageable);
		
		return results;
	}
	
	public Circuit findByNameAPI2(String name) {
		Circuit result;
		
		result = this.circuitRepository.findByName(name);
		
		return result;
	}
	
	public Page<Circuit> findByAllParametersAPI(String location, String name, Pageable pageable) {
		Page<Circuit> results;
		
		results = this.circuitRepository.findByAllParametersAPI(location, name, pageable);
		
		return results;
	}
	
	// Metodo principal para el web scraping -----------------------------
	public void loadCircuits() {
		this.circuitRepository.deleteAll();
			
		Set<Circuit> circuits = new HashSet<Circuit>();
		List<String> pages = this.getLinks();
		
		for (String page: pages) {
			log.info("------------- Pagina: " + page + " -------------------------");
			
			Document doc = this.utilityService.getDocument(page);
			
			if (doc != null) {
				try {
					Element circuitTable = doc.selectFirst("CircuitTable");
					
					Elements circuitTags = circuitTable.select("Circuit");
					
					for (Element circuitTag: circuitTags) {
						Circuit circuit = this.getCircuit(circuitTag);
							
						circuits.add(circuit);
					}
					
				} catch (Exception e) {				
					log.error("Error inesperado en circuit: " + e.getMessage());
				}
				
				log.info("Numero de circuitos: " + circuits.size());
				this.circuitRepository.saveAll(circuits);
			}
		}
	}
	
	protected Circuit getCircuit(Element circuitTag) {
		Element nameTag = circuitTag.selectFirst("CircuitName");
		Element location = circuitTag.selectFirst("Location");
		Element localityTag = location.selectFirst("Locality");
		Element countryTag = location.selectFirst("Country");
		
		String name = nameTag.text();
		String locality = localityTag.text();
		String country = countryTag.text();
		String info = circuitTag.attr("url").trim();
		
		Circuit circuit = new Circuit(name, locality, country, info);
		
		return circuit;
	}
	
	protected List<String> getLinks() {
		String context = "http://ergast.com/api/f1/circuits?limit=50";		
		List<String> results = new ArrayList<String>();
		
		for (int i=0; i<100; i=i+50) {
			String page = context + "&offset=" + i;
			
			log.info("Página: " + page);
			
			results.add(page);
		}
		
		return results;
	}

	protected Circuit save(Circuit circuit) {
		Circuit result;
		
		result = this.circuitRepository.save(circuit);
		
		return result;
	}
	
	protected void delete(String circuitId) {
		this.circuitRepository.deleteById(circuitId);
	}
	
	protected Circuit findByName(String name) {
		Circuit result;
		
		result = this.circuitRepository.findByName(name);
		
		return result;
	}
	
}
