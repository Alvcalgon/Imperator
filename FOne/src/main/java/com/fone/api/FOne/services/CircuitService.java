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
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
	
	// UC-011
	public List<Circuit> findAllAPI() {
		List<Circuit> result;
		Sort sort;
		
		sort = Sort.by(Direction.ASC, "name");
		result = this.circuitRepository.findAll(sort);
		
		return result;
	}
	
	// UC-012
	public List<Circuit> findByTypeAPI(String type) {
		List<Circuit> results;
		Sort sort;
		
		sort = Sort.by(Direction.ASC, "name");
		results = this.circuitRepository.findByTypeAPI(type, sort);
		
		return results;
	}
	
	// UC-013
	public List<Circuit> findByLocationAPI(String location) {
		List<Circuit> results;
		Sort sort;
		
		sort = Sort.by(Direction.ASC, "name");
		results = this.circuitRepository.findByLocationAPI(location, sort);
		
		return results;
	}
	
	// UC-015
	public List<Circuit> findByNameAPI(String name) {
		List<Circuit> results;
		Sort sort;
		
		sort = Sort.by(Direction.ASC, "name");
		
		results = this.circuitRepository.findByNameAPI(name, sort);
		
		return results;
	}
	
	// Metodo principal para el web scraping -----------------------------
	public void loadCircuits() {
		log.info("------------ Cargando datos de los circuitos en la BD ----------------");
		this.circuitRepository.deleteAll();
		
		Document doc, subDoc;
		Elements ls_tr, ls_td;
		Element a, tbody, table;
		String href, name;
		Set<Circuit> circuits;
		Circuit circuit;
		
		circuits = new HashSet<Circuit>();
		
		doc = this.utilityService.getDocument("https://www.f1-fansite.com/f1-circuits/");
		
		if (doc != null) {
			try {
				table = doc.selectFirst("table.motor-sport-results");
				
				tbody = table.selectFirst("tbody");
				
				ls_tr = tbody.select("tr");
				for (Element tr: ls_tr) {
					ls_td = tr.select("td");
					
					for (Element td: ls_td) {
						a = td.selectFirst("a");
						
						href = a.attr("href").trim();
						name = a.text().trim();
						
						subDoc = this.utilityService.getDocument(href);
						
						circuit = this.getCircuit(subDoc, name);
						
						if (circuit != null) {
							circuits.add(circuit);
						}
					}
				}
				
			} catch (Exception e) {				
				log.info("Error inesperado: " + e.getMessage());
			}
			
			log.info("Numero de circuitos: " + circuits.size());
			this.circuitRepository.saveAll(circuits);
		}
		
	}
	
	protected Circuit getCircuit(Document doc, String name) {
		Circuit result;
		String location, type, lapDistance;
		Element div, tbody, tr, td;
		
		try {
			div = doc.selectFirst("div.column.three_eighth");
			tbody = div.selectFirst("tbody");
			
			tr = tbody.child(1);
			td = tr.child(1);
			location = td.text().trim();
			
			tr = tbody.child(4);
			td = tr.child(1);
			type = td.text().trim();
			
			tr = tbody.child(5);
			td = tr.child(1);
			lapDistance = td.text().trim();
			
			result = new Circuit(name, location, type, lapDistance);
			
			log.info("Nombre del circuito: " + name);
		} catch (Exception e) {
			result = new Circuit(name);
			
			log.info("Error al recuperar todos los datos del circuito: " + e.getMessage());
		}
		
		return result;
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
