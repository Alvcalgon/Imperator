package com.formulaOne.api.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
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
	
	public Optional<Driver> findOptionalOne(String customerId) {
		Optional<Driver> resultado;
		
		resultado = this.driverRepository.findById(customerId);
		log.info("Objeto recuperado correctamente: " + resultado.get().getDriverId());
		
		return resultado;
	}
	
	public Driver findOne(String driverId) {
		return this.findOptionalOne(driverId).get();
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
	
	public void loadDriver() {
		log.info("--------------- Cargando datos de los pilotos en la BD ----------------");
		this.driverRepository.deleteAll();
		
		Document doc, subDoc;
		Elements tbody, ls_tr, ls_td;
		Element a;
		String href = "";
		List<Driver> drivers;
		Driver driver;
		
		drivers = new ArrayList<Driver>();
		
		try {
			// Este documento contiene el enlace de cada piloto
			doc = this.utilityService.getDocument("https://www.f1-fansite.com/f1-drivers/");
			tbody = doc.select("tbody");
			
			ls_tr = tbody.select("tr");		
			for (Element tr: ls_tr) {
				ls_td = tr.select("td");
				
				for (Element td: ls_td) {
					a = td.selectFirst("a");
					href = a.attr("href").trim();
					
					// Este documento contiene la informacion del piloto
					subDoc = Jsoup.connect(href).get();
					
					driver = this.getDriver(subDoc);
					drivers.add(driver);
					
					TimeUnit.SECONDS.sleep(5);
				}	
			}
			
			this.driverRepository.saveAll(drivers);
		} catch (IOException e) {
			log.info("Error al acceder a la pagina web" + e.getMessage());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Numero de iteraciones: " + drivers.size());
		log.info("------------ Carga de datos completada --------------------");
	}
	
	protected Driver getDriver(Document doc) {
		Driver result;
		Element div, tbody, tr, td, a;
		String fullname, country, placeOfBirth, str_birthOfDate;
		Date birthOfDate;
		
		div = doc.selectFirst("div.column half");
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
		
		result = new Driver(fullname, placeOfBirth, country, birthOfDate);
		
		log.info("Fullname: " + fullname + " Conuntry: " + country + " Place: " + placeOfBirth + " Date: " + birthOfDate);
		
		return result;
	}
	
}
