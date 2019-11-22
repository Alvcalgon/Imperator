package com.fone.api.FOne;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fone.api.FOne.domain.Circuit;
import com.fone.api.FOne.domain.Constructor;
import com.fone.api.FOne.domain.Driver;
import com.fone.api.FOne.domain.FastestLap;
import com.fone.api.FOne.domain.Race;
import com.fone.api.FOne.domain.Result;
import com.fone.api.FOne.services.CircuitService;
import com.fone.api.FOne.services.ConstructorService;
import com.fone.api.FOne.services.DriverService;
import com.fone.api.FOne.services.FastestLapService;
import com.fone.api.FOne.services.RaceService;
import com.fone.api.FOne.services.ResultService;

@Component
public class LoadDatabase implements CommandLineRunner {

private static final Log log = LogFactory.getLog(LoadDatabase.class);
	
	private static final boolean MODE = false;
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private CircuitService circuitService;
	
	@Autowired
	private ConstructorService constructorService;
	
	@Autowired
	private RaceService raceService;
	
	@Autowired
	private FastestLapService fastestLapService;
	
	@Autowired
	private ResultService resultService;
	
	@Override
	public void run(String... args) throws Exception {
		Race race;
		FastestLap lap, lapSaved;
		Result result_one, result_two, oneSaved, twoSaved;
		Set<Result> results;
		Driver driver_one, driver_two;
		Constructor constructor;
		Circuit circuit;
		
		if (MODE) {
			//this.circuitService.loadCircuits();
			//this.constructorService.loadConstructors();
			//this.driverService.loadDrivers(); // 851 pilotos
			log.info("Condicion verdadera");
		} else {
//			driver_one = this.driverService.findByFullname("Nino Farina");
//			driver_two = this.driverService.findByFullname("Luigi Fagioli");
//			constructor = this.constructorService.findByName("Alfa Romeo");
//			circuit = this.circuitService.findByName("Autodromo Nazionale Monza");
//			
//			lap = new FastestLap("1:50.600", driver_one, constructor);
//			
//			result_one = new Result("1", "2:13:23.600", 70, 1, 9, driver_one,
//									constructor);
//			result_two = new Result("2", "+2.600s", 70, 3, 6, driver_two,
//									constructor);
//			
//			lapSaved = this.fastestLapService.save(lap);
//			
//			oneSaved = this.resultService.save(result_one);
//			twoSaved = this.resultService.save(result_two);
//			
//			results = new HashSet<Result>();
//			results.add(oneSaved);
//			results.add(twoSaved);
//			
//			race = new Race("100001", "1950", new Date(), circuit, lapSaved, results);
//			
//			this.raceService.save(race);
			log.info("condicion falsa");
		}
		
		log.info("Database loaded");
	}

}