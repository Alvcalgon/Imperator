package com.formulaOne.api.domain;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ConstructorStanding")
public class ConstructorStanding {

	// Attributos --------------------------
	@Id
	@NotBlank
	private String constructorStandingId;
	
	@Min(0)
	private int points;
	
	@Valid
	@NotNull
	private Constructor constructor;
	
	@Valid
	@NotNull
	private Race race;
	
	// Constructores ----------------------
	public ConstructorStanding() {
		super();
		
		this.constructorStandingId = new ObjectId().toString();
	}
	
	public ConstructorStanding(int points, Constructor constructor, Race race) {
		super();
		
		this.constructorStandingId = new ObjectId().toString();
		this.constructor = constructor;
		this.race = race;
	}

	// Getters y setters
	public String getConstructorStandingId() {
		return constructorStandingId;
	}

	public void setConstructorStandingId(String constructorStandingId) {
		this.constructorStandingId = constructorStandingId;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Constructor getConstructor() {
		return constructor;
	}

	public void setConstructor(Constructor constructor) {
		this.constructor = constructor;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}
	
	
	public String toString() {
		return this.constructor.getName() + ": " + this.points + " puntos"; 
	}
	
}
