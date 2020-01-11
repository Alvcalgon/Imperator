package com.fone.api.FOne;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LoadDatabase implements CommandLineRunner {

	private static final Log log = LogFactory.getLog(LoadDatabase.class);

	private static final boolean MODE = true;

	@Override
	public void run(String... args) throws Exception {

		if (MODE) {			
			log.info("condicion verdadera");
		} else {
			log.info("condicion falsa");
		}

		log.info("Database loaded");
	}

}