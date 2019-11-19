package com.formulaOne.api.domain;

import javax.validation.constraints.NotBlank;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Circuit")
public class Circuit {

	
	// Atributos --------------------------------
	@Id
	@NotBlank
	private String circuitId;
	
	@NotBlank
	private String name;
	
	private String location;
	
	private String type;
	
	private String lapDistance;
	
	
	// Constructores ------------------------------
	public Circuit() {
		super();
		
		this.circuitId = new ObjectId().toString();
	}

	public Circuit(String name) {
		super();
		
		this.circuitId = new ObjectId().toString();
		this.name = name;
	}

	public Circuit(String name, String location, String type, String lapDistance) {
		super();
		
		this.circuitId = new ObjectId().toString();
		this.name = name;
		this.location = location;
		this.type = type;
		this.lapDistance = lapDistance;
	}


	// Getters y setters ----------------------
	public String getCircuitId() {
		return circuitId;
	}


	public void setCircuitId(String ciruitId) {
		this.circuitId = ciruitId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getLapDistance() {
		return lapDistance;
	}


	public void setLapDistance(String lapDistance) {
		this.lapDistance = lapDistance;
	}
	
	
	@Override
	public String toString() {
		return "Nombre del circuito: " + this.name;
	}
	
}