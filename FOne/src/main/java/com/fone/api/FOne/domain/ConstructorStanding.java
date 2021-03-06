package com.fone.api.FOne.domain;

import javax.validation.constraints.NotBlank;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "ConstructorStanding")
public class ConstructorStanding {

	// Attributos --------------------------
	@Id
	@NotBlank
	private String constructorStandingId;
	
	@NotBlank
	private String season;
	
	private String position;
	
	private Integer points;
	
	private Integer wins;
	
	private Constructor constructor;
	
	
	// Constructores ----------------------
	public ConstructorStanding() {
		super();
		
		this.constructorStandingId = new ObjectId().toString();
	}
	
	public ConstructorStanding(String season, String position, Integer points, Integer wins,
			                   Constructor constructor) {
		super();
		
		this.constructorStandingId = new ObjectId().toString();
		this.season = season;
		this.position = position;
		this.points = points;
		this.wins = wins;
		this.constructor = constructor;
	}

	// Getters y setters
	@JsonIgnore
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

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}
	
	public Integer getWins() {
		return wins;
	}

	public void setWins(Integer wins) {
		this.wins = wins;
	}

	public Constructor getConstructor() {
		return constructor;
	}

	public void setConstructor(Constructor constructor) {
		this.constructor = constructor;
	}
	
	
	@Override
	public int hashCode() {
		return this.constructorStandingId.hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		boolean result;
		
		if (this == other) {
			result = true;
		} else if (other == null) {
			result = false;
		} else if (other instanceof String) {
			result = (this.getConstructorStandingId() == (String) other);
		} else if (!this.getClass().isInstance(other)) {
			result = false;
		} else {
			result = (this.getConstructorStandingId() == ((ConstructorStanding) other).getConstructorStandingId());
		}
		
		return result;
	}

	@Override
	public String toString() {
		return "ConstructorStanding [constructorStandingId=" + constructorStandingId + ", season=" + season
				+ ", constructor=" + constructor + "]";
	}
	
}
