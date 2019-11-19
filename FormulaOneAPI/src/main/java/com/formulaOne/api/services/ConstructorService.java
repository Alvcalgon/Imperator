package com.formulaOne.api.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formulaOne.api.domain.Constructor;
import com.formulaOne.api.repositories.ConstructorRepository;

@Service
@Transactional
public class ConstructorService {

	private static final Log log = LogFactory.getLog(Constructor.class);
	
	@Autowired
	private ConstructorRepository constructorRepository;
	
	@Autowired
	private UtilityService utilityService;
	
	public ConstructorService() {
		super();
	}
	
	
	public Constructor findOne(String constructorId) {
		Constructor result;
		
		result = this.constructorRepository.findById(constructorId).get();
		
		return result;
	}
	
	public List<Constructor> findAll() {
		List<Constructor> results;
		
		results = this.constructorRepository.findAll();
		
		return results;
 	}
	
	public Constructor save(Constructor constructor) {
		Constructor result;
		
		result = this.constructorRepository.save(constructor);
		
		return result;
	}
	
	public void delete(Constructor constructor) {
		this.constructorRepository.delete(constructor);
	}
	
	public void deleteAll() {
		this.constructorRepository.deleteAll();
	}
	
	public void loadConstructors() {
		log.info("------------- Cargando datos de los constructores en la BD -------------------");
		this.constructorRepository.deleteAll();
		
		Document doc, subDoc;
		Elements ls_tr, ls_td;
		Element tbody, a;
		String href;
		Set<Constructor> constructors;
		Constructor constructor;
		
		constructors = new HashSet<Constructor>();
		
		// Este documento contiene el enlace hacia cada constructor
		doc = this.utilityService.getDocument("https://www.f1-fansite.com/f1-teams/");
		
		if (doc != null) {
			try {	
				tbody = doc.selectFirst("tbody");
				
				ls_tr = tbody.select("tr");
				for (Element tr: ls_tr) {
					ls_td = tr.select("td");
					
					for (Element td: ls_td) {
						a = td.selectFirst("a");
						href = a.attr("href").trim();
						
						// Este documento contiene los datos de la escuderia
						subDoc = this.utilityService.getDocument(href);
						
						constructor = this.getConstructor(subDoc);
						
						if (constructor != null) {
							constructors.add(constructor);
						}
						
						// Añadimos este tiempo de retraso para que el sistema web
						// interprete de que es un humano quien esta realizando
						// peticiones.
						TimeUnit.SECONDS.sleep(5);
					}
					
				}
				
			} catch (Exception e) {
				log.info("Error inesperado: " + e.getMessage());
			}
			
			log.info("Numero de escuderias: " + constructors.size());
			this.constructorRepository.saveAll(constructors);
		}
		
	}
	
	protected Constructor getConstructor(Document doc) {
		Constructor result;
		Elements p;
		Element div, tbody, tr, td, a;
		String name, country, principal;
		
		try {
			div = doc.selectFirst("div.column.half");
			tbody = div.selectFirst("tbody");
			
			tr = tbody.selectFirst("tr.msr_row1");
			td = tr.child(1);
			name = td.text().trim();
			
			tr = tbody.selectFirst("tr.msr_row2");
			td = tr.child(1);
			a = td.child(1);
			country = a.text().trim();
			
			div = doc.selectFirst("div.column.one_fourth");
			
			principal = "";
			if (div.children() != null) {
				if (div.children().size() > 0) {
					p = div.select("p");
					
					if (p.hasText()) {
						principal = this.getPrincipal(p.text());
					}
					
				}
			}
			
			result = (principal == "" || principal.isEmpty()) ? new Constructor(name, country) : new Constructor(name, country, principal);
			
			log.info("Nombre de la escuderia: " + name);
		} catch (Exception e) {
			result = null;
			
			log.info("Error al recuperar la escuderia: " + e.getMessage());
		}
				
		return result;
	}
	
	private String getPrincipal(String text) {
		String result;
		int index;
		
		index = text.indexOf(":");
			
		result = (index != -1) ? text.substring(index+1).trim(): "";
		
		return result;
	}
	
	public Constructor findByName(String name) {
		Constructor result;
		
		result = this.constructorRepository.findByName(name);
		
		return result;
	}
	
	public List<Constructor> findByCountry(String country) {
		List<Constructor> results;
		
		results = this.constructorRepository.findByCountry(country);
		
		return results;
	}
	
}