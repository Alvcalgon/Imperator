package com.formulaOne.api.services;

import java.util.Date;
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

import com.formulaOne.api.FormulaOneAPIApplication;
import com.formulaOne.api.domain.Driver;
import com.formulaOne.api.repositories.DriverRepository;

@Service
@Transactional
public class DriverService {

	private static final Log log = LogFactory.getLog(FormulaOneAPIApplication.class);
	
	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private UtilityService utilityService;
	
	public DriverService() {
		super();
	}
	
	public Driver findOne(String driverId) {
		return this.driverRepository.findById(driverId).get();
	}
	
	public List<Driver> findAll() {
		List<Driver> resultados;
		
		resultados = this.driverRepository.findAll();
		
		return resultados;
	}
	
	public Driver save(Driver driver) {
		Driver resultado;
		
		resultado = this.driverRepository.save(driver);
		
		return resultado;
	}
	
	
	public void delete(Driver driver) {
		this.driverRepository.delete(driver);
	}
	
	public void deleteAll() {
		this.driverRepository.deleteAll();
	}
	
	public void loadDrivers() {
		log.info("--------------- Cargando datos de los pilotos en la BD ----------------");
		this.driverRepository.deleteAll();
		
		Document doc, subDoc;
		Elements ls_tr, ls_td;
		Element a, tbody;
		String href = "";
		Set<Driver> drivers;
		Driver driver;
		
		drivers = new HashSet<Driver>();
		
		// Este documento contiene el enlace de cada piloto
		doc = this.utilityService.getDocument("https://www.f1-fansite.com/f1-drivers/");
		
		if (doc != null) {
			try {	
				tbody = doc.selectFirst("tbody");
				
				ls_tr = tbody.select("tr");		
				for (Element tr: ls_tr) {
					ls_td = tr.select("td");
					
					for (Element td: ls_td) {
						a = td.selectFirst("a");
						href = a.attr("href").trim();
						
						// Este documento contiene la informacion del piloto
						subDoc = this.utilityService.getDocument(href);
						
						driver = this.getDriver(subDoc);
						
						if (driver != null) {
							drivers.add(driver);
						}
						
						// Añadimos este tiempo de retraso para que el sistema web no interprete
						// de que somos un robot
						TimeUnit.SECONDS.sleep(5);
					}	
				}
				
			} catch (Exception e) {
				log.info("Error inesperado: " + e.getMessage());
			}
		
			log.info("Numero de pilotos: " + drivers.size());
			this.driverRepository.saveAll(drivers);
		}
		
	}
	
	protected Driver getDriver(Document doc) {
		Driver result;
		Element div, tbody, tr, td, a;
		String fullname, country, placeOfBirth, str_birthOfDate;
		Date birthOfDate;
		
		try {
			div = doc.selectFirst("div.column.half");
			tbody = div.selectFirst("tbody");
			
			tr = tbody.selectFirst("tr.msr_row1");
			td = tr.child(1);
			fullname = td.text().trim();
			
			tr = tbody.selectFirst("tr.msr_row2");
			td = tr.child(1);
			a = td.child(1);
			country = a.text().trim();
			
			tr = tbody.selectFirst("tr.msr_row3");
			td = tr.child(1);
			placeOfBirth = td.text().trim();
			
			tr = tbody.selectFirst("tr.msr_row4");
			td = tr.child(1);
			
			str_birthOfDate = td.text();
			birthOfDate = this.utilityService.getDate(str_birthOfDate);
			
			result = (birthOfDate != null) ? new Driver(fullname, placeOfBirth, country, birthOfDate) : new Driver(fullname, placeOfBirth, country);
			
			log.info("Piloto: " + fullname);
		} catch (Exception e) {
			result = null;
			
			log.info("Error al recuperar el piloto: " + e.getMessage());
		}
		
		return result;
	}
	
}
