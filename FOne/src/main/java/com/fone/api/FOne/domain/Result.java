package com.fone.api.FOne.domain;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "Result")
public class Result {

	// Atributos --------------------------------
	@Id
	@NotBlank
	private String resultId;
	
	private String position;
	
	private String time;
	
	@Min(0)
	private Integer laps;
	
	private String grid;
	
	@Min(0)
	private Integer points;
	
	private String status;
	
	private String driverFullname;
	
	@Valid
	@NotNull
	private Driver driver;
	
	@Valid
	@NotNull
	private Constructor constructor;
	
	
	// Constructores --------------------------------
	public Result() {
		super();
		
		this.resultId = new ObjectId().toString();
	}


	public Result(String position, String time, Integer laps, String grid, Integer points,
			      String status, Driver driver, Constructor constructor) {
		super();
		
		this.resultId = new ObjectId().toString();
		this.position = position;
		this.time = time;
		this.laps = laps;
		this.grid = grid;
		this.points = points;
		this.status = status;
		this.driver = driver;
		this.constructor = constructor;
	}

	
	// Getters y setters -----------------------------
	@JsonIgnore
	public String getResultId() {
		return resultId;
	}


	public void setResultId(String resultId) {
		this.resultId = resultId;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public Integer getLaps() {
		return laps;
	}


	public void setLaps(Integer laps) {
		this.laps = laps;
	}


	public String getGrid() {
		return grid;
	}


	public void setGrid(String grid) {
		this.grid = grid;
	}


	public Integer getPoints() {
		return points;
	}


	public void setPoints(Integer points) {
		this.points = points;
	}

	
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	@JsonIgnore
	public String getDriverFullname() {
		return driver.getFullname();
	}

	public void setDriverFullname(String driverFullname) {
		this.driverFullname = driverFullname;
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
	
	@Override
	public int hashCode() {
		return this.resultId.hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		boolean result;
		
		if (this == other) {
			result = true;
		} else if (other == null) {
			result = false;
		} else if (other instanceof String) {
			result = (this.getResultId() == (String) other);
		} else if (!this.getClass().isInstance(other)) {
			result = false;
		} else {
			result = (this.getResultId() == ((Result) other).getResultId());
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		return "Result [id=" + this.resultId + ", driver=" + this.driver.getFullname() + ", constructor=" + this.getConstructor().getName() + ", position=" + this.position + "]";
	}
	
}
