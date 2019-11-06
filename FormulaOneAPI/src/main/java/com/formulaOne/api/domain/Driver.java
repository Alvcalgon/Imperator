package com.formulaOne.api.domain;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Driver")
public class Driver {
	
	// Atributos --------------------------
	@Id
	@NotBlank
	private String driverId;
	
	@NotBlank
	private String fullname;
	
	private String placeOfBirth;
	
	private String country;
	
	@Past
	private Date dateOfBirth;	
	
	
	// Constructores -------------------------
	public Driver() {
		super();
		
		this.driverId = new ObjectId().toString();
	}
	
	public Driver(String fullname, String placeOfBirth, String country) {
		super();
		
		this.driverId = new ObjectId().toString();
		this.fullname = fullname;
		this.placeOfBirth = placeOfBirth;
		this.country = country;
	}
	
	public Driver(String fullname, String placeOfBirth, String country, Date dateOfBirth) {
		super();
		
		this.driverId = new ObjectId().toString();
		this.fullname = fullname;
		this.placeOfBirth = placeOfBirth;
		this.country = country;
		this.dateOfBirth = dateOfBirth;
	}
	
	
	// Getters y setters ----------------------
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
	
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
	@Override
	public String toString() {
		return "Driver's fullname: " + this.fullname;
	}
	
}
