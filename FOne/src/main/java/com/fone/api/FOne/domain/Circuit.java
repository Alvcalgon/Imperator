package com.fone.api.FOne.domain;

import javax.validation.constraints.NotBlank;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "Circuit")
public class Circuit {

	
	// Atributos --------------------------------
	@Id
	@NotBlank
	private String circuitId;
	
	@NotBlank
	private String name;
	
	private String locality;
	
	private String country;
	
	private String information;
	
	
	// Constructores ------------------------------
	public Circuit() {
		super();
		
		this.circuitId = new ObjectId().toString();
	}

	public Circuit(String name, String locality, String country, String information) {
		super();
		
		this.circuitId = new ObjectId().toString();
		this.name = name;
		this.locality = locality;
		this.country = country;
		this.information = information;
	}


	// Getters y setters ----------------------
	@JsonIgnore
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

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	@Override
	public int hashCode() {
		return this.circuitId.hashCode();
	}
	
	@Override
	public  boolean equals(Object other) {
		boolean result;
		
		if (this == other) {
			result = true;
		} else if (other == null) {
			result = false;
		} else if (other instanceof String) {
			result = (this.getCircuitId() == (String) other);
		} else if (!this.getClass().isInstance(other)) {
			result = false;
		} else {
			result = (this.getCircuitId() == ((Circuit) other).getCircuitId());
		}
		
		return result;
	}

	@Override
	public String toString() {
		return "Circuit [circuitId=" + circuitId + ", name=" + name + "]";
	}
		
}
