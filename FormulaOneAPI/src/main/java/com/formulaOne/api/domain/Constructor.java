package com.formulaOne.api.domain;

import javax.validation.constraints.NotBlank;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Constructor")
public class Constructor {

	// Atributos --------------------------------------------
	@Id
	@NotBlank
	private String constructorId;
	
	@NotBlank
	private String name;
	
	private String country;
	
	private String principal;
	
	// Constructores -----------------------------------------
	public Constructor() {
		super();
		
		this.constructorId = new ObjectId().toString();
	}

	public Constructor(String name, String country, String principal) {
		super();
		
		this.constructorId = new ObjectId().toString();
		this.name = name;
		this.country = country;
		this.principal = principal;
	}

	public Constructor(String name, String country) {
		super();
		
		this.constructorId = new ObjectId().toString();
		this.name = name;
		this.country = country;
	}

	
	// Getters y setters ---------------------------
	public String getConstructorId() {
		return constructorId;
	}

	public void setConstructorId(String constructorId) {
		this.constructorId = constructorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	
	
	@Override
	public String toString() {
		return "Constructor's name: " + this.name;
	}
	
}
