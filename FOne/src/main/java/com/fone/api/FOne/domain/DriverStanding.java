package com.fone.api.FOne.domain;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "DriverStanding")
public class DriverStanding {

	// Atributos --------------------------
	@Id
	@NotBlank
	private String driverStandingId;
	
	@Min(0)
	private int points;
	
	private String position;
	
	@DBRef
	@Valid
	@NotNull
	private Driver driver;
	
	@DBRef
	@Valid
	@NotNull
	private Race race;
	
	// Constructores ---------------------
	public DriverStanding() {
		super();
		
		this.driverStandingId = new ObjectId().toString();
	}
	
	public DriverStanding(int points, String position, Driver driver, Race race) {
		super();
		
		this.driverStandingId = new ObjectId().toString();
		this.points = points;
		this.position = position;
		this.driver = driver;
		this.race = race;
	}

	
	// Getter y setters -------------------
	public String getDriverStandingId() {
		return driverStandingId;
	}

	public void setDriverStandingId(String driverStandingId) {
		this.driverStandingId = driverStandingId;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}
	
	public String toString() {
		return "Posición de " + this.driver.getFullname() + ": " + this.position;
	}
	
	
}
