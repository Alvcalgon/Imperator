package com.fone.api.FOne.domain;

import javax.validation.constraints.NotBlank;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "DriverStanding")
public class DriverStanding {

	// Atributos --------------------------
	@Id
	@NotBlank
	private String driverStandingId;
	
	@NotBlank
	private String season;
	
	private String points;
	
	private String position;
	
	private Driver driver;
	
	private Constructor constructor;
	
	// Constructores ---------------------
	public DriverStanding() {
		super();
		
		this.driverStandingId = new ObjectId().toString();
	}
	
	public DriverStanding(String season, String points, String position, Driver driver, Constructor constructor) {
		super();
		
		this.driverStandingId = new ObjectId().toString();
		this.season = season;
		this.points = points;
		this.position = position;
		this.driver = driver;
		this.constructor = constructor;
	}

	
	// Getter y setters -------------------
	public String getDriverStandingId() {
		return driverStandingId;
	}

	public void setDriverStandingId(String driverStandingId) {
		this.driverStandingId = driverStandingId;
	}

	
	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
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
	
	public Constructor getConstructor() {
		return constructor;
	}

	public void setConstructor(Constructor constructor) {
		this.constructor = constructor;
	}

	public String toString() {
		return "Posición de " + this.driver.getFullname() + " - " + this.constructor.getName() + ": " + this.position;
	}
	
}
