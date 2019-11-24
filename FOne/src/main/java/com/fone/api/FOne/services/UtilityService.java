package com.fone.api.FOne.services;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UtilityService {

	private static final Log log = LogFactory.getLog(UtilityService.class);
	
	
	// Constructor --------------------------------
	public UtilityService() {
		super();
	}
	
	
	// Metodos ------------------------------------
	protected Date getDateByParameters(String season, String monthDay) {
		Date result;
		String str_date, day, month, nameMonth, nameDay;
		String[] fields;
		SimpleDateFormat format;
		
		fields = monthDay.split(" ");
		
		nameDay = fields[1];
		nameMonth = fields[0];
		
		day = nameDay.trim();
		month = this.getMM(nameMonth.trim());
		
		str_date = String.join("/", day, month, season);
		format = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			result = format.parse(str_date);
		} catch (Exception e) {
			result = null;
			log.info("Error al definir un valor correcto para Race::raceDate");
		}
		
		return result;
	}
	
	protected Date getDate(String str_date) {
		Date result;
		String str_day, str_month, monthAsNumber, str_year, strDate;
		String[] fields;
		Pattern pattern;
		Matcher matcher;
		SimpleDateFormat format;
		
		fields = str_date.split(" ");
		
		str_month = fields[0].trim();
		monthAsNumber = this.getMonth(str_month);
		str_year = fields[2].trim();
		
		pattern = Pattern.compile("^\\d+");
		matcher = pattern.matcher(fields[1]);
		
		if (matcher.find()) {
			str_day = matcher.group();
		} else {
			str_day = "22";
			log.info("Establecido dia por defecto");
		}
		
		strDate = String.join("/", str_day, monthAsNumber, str_year);
		
		format = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			result = format.parse(strDate);
		} catch (ParseException e) {
			result = null;
			log.info("Error al extraer la fecha");
		}
		
		return result;
	}
	
	protected String getMonth(String month) {
		return this.getNameMonth().get(month);
	}
	
	protected String getMM(String month) {
		return this.getNameByMonth().get(month);
	}
	
	protected Map<String, String> getNameMonth() {
		Map<String, String> result;
		
		result = new HashMap<String, String>();
		result.put("Jan", "01");
		result.put("Feb", "02");
		result.put("Mar", "03");
		result.put("Apr", "04");
		result.put("May", "05");
		result.put("Jun", "06");
		result.put("Jul", "07");
		result.put("Aug", "08");
		result.put("Sep", "09");
		result.put("Oct", "10");
		result.put("Nov", "11");
		result.put("Dec", "12");
		
		return result;
	}
	
	protected Map<String, String> getNameByMonth() {
		Map<String, String> result;
		
		result = new HashMap<String, String>();
		result.put("January", "01");
		result.put("February", "02");
		result.put("March", "03");
		result.put("April", "04");
		result.put("May", "05");
		result.put("June", "06");
		result.put("July", "07");
		result.put("August", "08");
		result.put("September", "09");
		result.put("October", "10");
		result.put("November", "11");
		result.put("December", "12");
		
		return result;
	}
	
	
	protected Document getDocument(String url) {
		Response response;
		Document result;
		
		try {
			// Añadimos esos parametros en el header para evitar que nos bloqueen el acceso a la 
			// pagina web.
			response = Jsoup.connect(url)
					.referrer("https://www.google.com/") // Sitio desde el cual se realiza la busqueda
					.userAgent(this.getUserAgent())
					.execute();
			
			result = response.parse();
		
			try {
				// Añadimos este tiempo de retraso para que el sistema web no interprete
				// de que somos un robot
				TimeUnit.SECONDS.sleep(4);
			} catch (InterruptedException e) {
				log.info("Error con el retraso agregado");
			}
			
		} catch (IOException e) {
			result = null;
			
			log.info("Error al recuperar la pagina web: " + e.getMessage());
		}
		
		return result;
	}
	
	// Cambiamos de user agent para evitar que nos bloqueen el acceso a la pagina web
	protected String getUserAgent() {
		String result;
		String[] userAgents = {"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:70.0) Gecko/20100101 Firefox/70.0",
					  		   "Chrome/77.0.3865.120 Safari/537.36",
					           "Edge/18.18362"};
		int index, n;
		Random random = new Random();
		
		n = userAgents.length;
		index = random.nextInt(n);
		
		result = userAgents[index];
		
		return result;
	}
	
}
