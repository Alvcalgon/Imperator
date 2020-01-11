package com.fone.api.FOne.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "Race")
public class Race {

	// Atributos -----------------------
	@Id
	@NotBlank
	private String raceId;
	
	@NotBlank
	private String season;
	
	private Date raceDate;
	
	private String event;
	
	@Valid
	private Circuit circuit;
	
	@NotNull
	Set<Result> results;
	
	
	// Constructores -----------------------
	public Race() {
		super();
		
		this.raceId = new ObjectId().toString();
		this.results = new HashSet<Result>();
	}

	public Race(String season, Date raceDate) {
		super();
		
		this.raceId = new ObjectId().toString();
		this.season = season;
		this.raceDate = raceDate;
		this.results = new HashSet<Result>();
	}
	
	public Race(String season, Date raceDate, String event, Circuit circuit) {
		super();
		
		this.raceId = new ObjectId().toString();
		this.season = season;
		this.raceDate = raceDate;
		this.circuit = circuit;
		this.event = event;
		this.results = new HashSet<Result>();
	}
	
	
	// Getters y setters ----------------
	@JsonIgnore
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
	
	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Circuit getCircuit() {
		return circuit;
	}

	public void setCircuit(Circuit circuit) {
		this.circuit = circuit;
	}

	public Set<Result> getResults() {
		return results;
	}

	public void setResults(Set<Result> results) {
		this.results = results;
	}

	@Override
	public int hashCode() {
		return this.raceId.hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		boolean result;
		
		if (this == other) {
			result = true;
		} else if (other == null) {
			result = false;
		} else if (other instanceof String) {
			result = (this.getRaceId() == (String) other);
		} else if (!this.getClass().isInstance(other)) {
			result = false;
		} else {
			result = (this.getRaceId() == ((Race) other).getRaceId());
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		return "[Race [id=" + this.raceId + ", season=" + this.season + ", event=" + this.event + "]";
	}
	
	
}
