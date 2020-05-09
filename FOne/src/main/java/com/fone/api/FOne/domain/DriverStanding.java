package com.fone.api.FOne.domain;

import javax.validation.constraints.NotBlank;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "DriverStanding")
public class DriverStanding {

	// Atributos --------------------------
	@Id
	@NotBlank
	private String driverStandingId;
	
	@NotBlank
	private String season;
	
	private Double points;
	
	private String position;
	
	private Integer wins;
	
	private Driver driver;
	
	@Transient
	private String driverFullname;
	
	private Constructor constructor;
	
	// Constructores ---------------------
	public DriverStanding() {
		super();
		
		this.driverStandingId = new ObjectId().toString();
	}
	
	public DriverStanding(String season, Double points, String position, Integer wins,
			              Driver driver, Constructor constructor) {
		super();
		
		this.driverStandingId = new ObjectId().toString();
		this.season = season;
		this.points = points;
		this.position = position;
		this.wins = wins;
		this.driver = driver;
		this.constructor = constructor;
	}

	
	// Getter y setters -------------------
	@JsonIgnore
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

	public Double getPoints() {
		return points;
	}

	public void setPoints(Double points) {
		this.points = points;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	public Integer getWins() {
		return wins;
	}

	public void setWins(Integer wins) {
		this.wins = wins;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	@JsonIgnore
	public String getDriverFullname() {
		return driver.getFullname();
	}

	public void setDriverFullname(String driverFullname) {
		this.driverFullname = driverFullname;
	}

	public Constructor getConstructor() {
		return constructor;
	}

	public void setConstructor(Constructor constructor) {
		this.constructor = constructor;
	}

	
	@Override
	public int hashCode() {
		return this.driverStandingId.hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		boolean result;
		
		if (this == other) {
			result = true;
		} else if (other == null) {
			result = false;
		} else if (other instanceof String) {
			result = (this.getDriverStandingId() == (String) other);
		} else if (!this.getClass().isInstance(other)) {
			result = false;
		} else {
			result = (this.getDriverStandingId() == ((DriverStanding) other).getDriverStandingId());
		}
		
		return result;
	}

	@Override
	public String toString() {
		return "DriverStanding [driverStandingId=" + driverStandingId + ", season="
				+ season + ", driver=" + driver + "]";
	}
}
