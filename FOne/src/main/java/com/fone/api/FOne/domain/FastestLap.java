package com.fone.api.FOne.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "FastestLap")
public class FastestLap {

	// Atributos ----------------------------
	@Id
	@NotBlank
	private String fastestLapId;
	
	@NotBlank
	private String time;
	
	@DBRef
	@Valid
	@NotNull
	private Driver driver;
	
	@DBRef
	@Valid
	@NotNull
	private Constructor constructor;
	
	// Constructores -------------------------
	public FastestLap() {
		super();
		
		this.fastestLapId = new ObjectId().toString();
	}
	
	public FastestLap(String time, Driver driver, Constructor constructor) {
		super();
		
		this.fastestLapId = new ObjectId().toString();
		this.time = time;
		this.driver = driver;
		this.constructor = constructor;
	}

	// Getters y setters ----------------------
	public String getFastestLapId() {
		return fastestLapId;
	}

	public void setFastestLapId(String fastestLapId) {
		this.fastestLapId = fastestLapId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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
	public String toString() {
		return this.driver.getFullname() + "(" + this.constructor.getName() + "): " + this.time;
	}
	
}
