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

import com.fone.api.FOne.domain.Constructor;
import com.fone.api.FOne.repositories.ConstructorRepository;


@Service
@Transactional
public class ConstructorService {

	private static final Log log = LogFactory.getLog(ConstructorService.class);
	
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
	
	public Page<Constructor> findAllAPI(Pageable pageable) {
		Page<Constructor> results;
		
		results = this.constructorRepository.findAll(pageable);
		
		return results;
 	}
	
	public Page<Constructor> findByNationalityAPI(String nationality, Pageable pageable) {
		Page<Constructor> results;
		
		results = this.constructorRepository.findByNationalityAPI(nationality, pageable);
		
		return results;
	}
	
	public Page<Constructor> findByNameAPI(String name, Pageable pageable) {
		Page<Constructor> result;
		
		result = this.constructorRepository.findByNameAPI(name, pageable);
		
		return result;
	}
	
	public Constructor findByNameAPI2(String name) {
		Constructor result;
		
		result = this.constructorRepository.findByName(name);
		
		return result;
	}
	
	public Page<Constructor> findByParametersAPI(String name, String nationality, Pageable pageable) {
		Page<Constructor> results;
		
		results = this.constructorRepository.findByParametersAPI(name, nationality, pageable);
		
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
		this.constructorRepository.deleteAll();
		
		Elements constructorTags;
		Element constructorTable;
		Set<Constructor> constructors;
		Constructor constructor;
		
		constructors = new HashSet<Constructor>();
		List<String> pages = this.getLinks();
		
		for (String page: pages) {
			log.info("------------- Pagina: " + page + " -------------------------");
			
			Document doc = this.utilityService.getDocument(page);
			
			if (doc != null) {
				try {	
					constructorTable = doc.selectFirst("ConstructorTable");
					
					constructorTags = constructorTable.select("Constructor");
					
					for (Element constructorTag: constructorTags) {
						constructor = this.getConstructor(constructorTag);
						
						log.info("Constructor: " + constructor.getName());
						
						constructors.add(constructor);
					}
					
				} catch (Exception e) {
					log.info("ConstructorService::loadConstructors: Error inesperado: " + e.getMessage());
				}
				
				log.info("Numero de escuderias: " + constructors.size());
				this.constructorRepository.saveAll(constructors);
			}
		}
	}
	
	protected Constructor getConstructor(Element constructorTag) {
		Constructor result;
		Element nameTag, nacion;
		String name, nacionality, info;
		
		nameTag = constructorTag.selectFirst("Name");
		nacion = constructorTag.selectFirst("Nationality");
		
		name = nameTag.text();
		nacionality = nacion.text();
		info = constructorTag.attr("url").trim();
		
		result = new Constructor(name, nacionality, info);
		
		return result;
	}
	
	protected List<String> getLinks() {
		List<String> results;
		String context;
		String page;
		
		context = "http://ergast.com/api/f1/constructors?limit=70";		
		results = new ArrayList<String>();
		
		for (int i=0; i<210; i=i+70) {
			page = context + "&offset=" + i;
			
			log.info("Página: " + page);
			
			results.add(page);
		}
		
		return results;
	}
	
	protected Constructor findByName(String name) {
		Constructor result;
		
		result = this.constructorRepository.findByName(name);
		
		return result;
	}
	
}
