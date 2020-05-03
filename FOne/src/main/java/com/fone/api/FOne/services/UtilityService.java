package com.fone.api.FOne.services;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public Pageable getPageable(int limit, int offset, Sort sort) {
		Pageable result;
		
		result = PageRequest.of(offset, limit, sort);
		
		return result;
	}
	
	public Pageable getPageable(int limit, int offset) {
		Pageable result;
		
		result = PageRequest.of(offset, limit);
		
		return result;
	}
		
	protected Document getDocument(String url) {
		Response response;
		Document result;
		
		result = null;
		
		try {
			// Añadimos esos parametros en el header para evitar que nos bloqueen el acceso a la 
			// pagina web.
			response = Jsoup.connect(url)
					.referrer("https://www.google.com/") // Sitio desde el cual se realiza la busqueda
					.userAgent(this.getUserAgent())
					.execute();
			
			result = response.parse();
				
			TimeUnit.SECONDS.sleep(4);
			
			log.info("UtilityService::getDocument: Retraso agregado");
		} catch (InterruptedException e) {
			
			log.info("UtilityService::getDocument: Error con el retraso agregado");
		
		} catch (IOException e) {
			
			log.info("UtilityService::getDocument: Error al recuperar la pagina web: "
					+ e.getMessage());
		
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
