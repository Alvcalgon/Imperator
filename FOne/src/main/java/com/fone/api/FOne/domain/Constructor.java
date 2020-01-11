package com.fone.api.FOne.domain;

import javax.validation.constraints.NotBlank;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
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
	public int hashCode() {
		return this.constructorId.hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		boolean result;
		
		if (this == other) {
			result = true;
		} else if (other == null) {
			result = false;
		} else if (other instanceof String) {
			result = (this.getConstructorId() == (String) other);
		} else if (!this.getClass().isInstance(other)) {
			result = false;
		} else {
			result = (this.getConstructorId() == ((Constructor) other).getConstructorId());
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		return "Constructor [id=" + this.constructorId + ", name=" + this.name + "]";
	}
	
}
