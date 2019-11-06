package com.formulaOne.api.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Race")
public class Race {

	// Atributos -----------------------
	@Id
	@NotBlank
	private String raceId;
	
	@NotBlank
	private String season;
	
	private Date raceDate;
	
	@Valid
	@NotNull
	private Circuit circuit;
	
	@NotNull
	Set<Result> results;
	
	// Constructores -----------------------
	public Race() {
		super();
		
		this.raceId = new ObjectId().toString();
		this.results = new HashSet<Result>();
	}

	public Race(String raceId, String season, Date raceDate, Set<Result> results) {
		super();
		
		this.raceId = new ObjectId().toString();
		this.season = season;
		this.raceDate = raceDate;
		this.results = new HashSet<Result>(results);
	}

	
	// Getters y setters ----------------
	public String getRaceId() {
		return raceId;
	}

	public void setRaceId(String raceId) {
		this.raceId = raceId;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public Date getRaceDate() {
		return raceDate;
	}

	public void setRaceDate(Date raceDate) {
		this.raceDate = raceDate;
	}
	

	@Override
	public String toString() {
		return "Race: " + this.circuit.getName() + " - " + this.season;
	}
	
	
}
