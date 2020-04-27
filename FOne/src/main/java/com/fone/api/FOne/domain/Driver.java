package com.fone.api.FOne.domain;

import javax.validation.constraints.NotBlank;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "Driver")
public class Driver {
	
	// Atributos --------------------------
	@Id
	@NotBlank
	private String driverId;
	
	@NotBlank
	private String fullname;
		
	private String nacionality;
	
	private String dateOfBirth;	
	
	private String information;
	
	// Constructores -------------------------
	public Driver() {
		super();
		
		this.driverId = new ObjectId().toString();
	}
	
	public Driver(String fullname, String nacionality, String dateOfBirth, String information) {
		super();
		
		this.driverId = new ObjectId().toString();
		this.fullname = fullname;
		this.nacionality = nacionality;
		this.dateOfBirth = dateOfBirth;
		this.information = information;
	}
	
	
	// Getters y setters ----------------------
	@JsonIgnore
	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	
	public String getFullname() {
		return fullname;
	}
	
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public String getNacionality() {
		return nacionality;
	}

	public void setNacionality(String nacionality) {
		this.nacionality = nacionality;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	
	@Override
	public int hashCode() {
		return this.driverId.hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		boolean result;
		
		if (this == other) {
			result = true;
		} else if (other == null) {
			result = false;
		} else if (other instanceof String) {
			result = (this.getDriverId() == (String) other);
		} else if (!this.getClass().isInstance(other)) {
			result = false;
		} else {
			result = (this.getDriverId() == ((Driver) other).getDriverId());
		}
		
		return result;
	}

	@Override
	public String toString() {
		return "Driver [driverId=" + driverId + ", fullname=" + fullname + "]";
	}
	
}
