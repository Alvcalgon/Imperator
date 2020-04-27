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

import com.fone.api.FOne.domain.Driver;
import com.fone.api.FOne.repositories.DriverRepository;

@Service
@Transactional
public class DriverService {

	private static final Log log = LogFactory.getLog(DriverService.class);
	
	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private UtilityService utilityService;
	
	public DriverService() {
		super();
	}
	
	
	// Consultas disponibles en la API ----------------------------
	public Driver findOneAPI(String driverId) {
		return this.driverRepository.findById(driverId).get();
	}
	
	public Page<Driver> findAllAPI(Pageable pageable) {
		Page<Driver> resultados;
		
		resultados = this.driverRepository.findAll(pageable);
		
		return resultados;
	}
	
	public Page<Driver> findByCountryAPI(String country, Pageable pageable) {
		Page<Driver> results;
		
		results = this.driverRepository.findByCountryAPI(country, pageable);
		
		return results;
	}
	
	public Page<Driver> findByFullnameAPI(String fullname, Pageable pageable) {
		Page<Driver> results;
		
		results = this.driverRepository.findByFullnameAPI(fullname, pageable);
		
		return results;
	}
	
	public Page<Driver> findByParametersAPI(String fullname, String country, Pageable pageable) {
		Page<Driver> results;
		
		results = this.driverRepository.findByParametersAPI(fullname, country, pageable);
		
		return results;
	}
	
	public Driver findByFullname2(String fullname) {
		Driver result;
		
		result = this.driverRepository.findByFullname(fullname);
		
		return result;
	}
	
	// Método principal para web scraping
	public void loadDrivers() {
		this.driverRepository.deleteAll();
		
		Elements driverTags;
		Element driverTable;
		Driver driver;
		
		Set<Driver> drivers = new HashSet<Driver>();
		List<String> pages = this.getLinks();
		
		for (String page: pages) {
			log.info("------------- Pagina: " + page + " -------------------------");
			
			Document doc = this.utilityService.getDocument(page);
			
			if (doc != null) {
				try {				
					driverTable = doc.selectFirst("DriverTable");
					
					driverTags = driverTable.select("Driver");
					
					for (Element driverTag: driverTags) {		
						driver = this.getDriver(driverTag);
						drivers.add(driver);
					}
					
				} catch (Exception e) {
					log.error("DriverService::loadDrivers: Error inesperado", e);
				}
			}
		}
	
		log.info("Numero de pilotos: " + drivers.size());
		this.driverRepository.saveAll(drivers);
	}
	

	// Métodos auxiliares -----------------------------------------	
	protected Driver getDriver(Element driverTag) {
		Element givenName, familyName, dateOfBirth, nacion;
		String fullname, nacionality, dateBirth, info;
		Driver result;
		
		givenName = driverTag.selectFirst("GivenName");
		familyName = driverTag.selectFirst("FamilyName");
		dateOfBirth = driverTag.selectFirst("DateOfBirth");
		nacion = driverTag.selectFirst("Nationality");
		
		fullname = givenName.text() + " " + familyName.text();
		dateBirth = dateOfBirth.text();
		nacionality = nacion.text();
		info = driverTag.attr("url").trim();
	
		result = new Driver(fullname, nacionality, dateBirth, info);
		
		return result;
	}
	
	protected List<String> getLinks() {
		List<String> results;
		String context;
		String page;
		
		context = "http://ergast.com/api/f1/drivers?limit=50";		
		results = new ArrayList<String>();
		
		for (int i=0; i<850; i=i+50) {
			page = context + "&offset=" + i;
			
			log.info("Página: " + page);
			
			results.add(page);
		}
		
		return results;
	}
	
}
