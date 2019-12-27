package com.fone.api.FOne.domain;

import javax.validation.constraints.NotBlank;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ConstructorStanding")
public class ConstructorStanding {

	// Attributos --------------------------
	@Id
	@NotBlank
	private String constructorStandingId;
	
	@NotBlank
	private String season;
	
	private String position;
	
	private String points;
	
	@DBRef
	private Constructor constructor;
	
	
	// Constructores ----------------------
	public ConstructorStanding() {
		super();
		
		this.constructorStandingId = new ObjectId().toString();
	}
	
	public ConstructorStanding(String season, String position, String points, Constructor constructor) {
		super();
		
		this.constructorStandingId = new ObjectId().toString();
		this.season = season;
		this.position = position;
		this.points = points;
		this.constructor = constructor;
	}

	// Getters y setters
	public String getConstructorStandingId() {
		return constructorStandingId;
	}

	public void setConstructorStandingId(String constructorStandingId) {
		this.constructorStandingId = constructorStandingId;
	}
	
	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public Constructor getConstructor() {
		return constructor;
	}

	public void setConstructor(Constructor constructor) {
		this.constructor = constructor;
	}
	
	
	public String toString() {
		return this.constructor.getName() + ": " + this.points + " puntos"; 
	}
	
}
