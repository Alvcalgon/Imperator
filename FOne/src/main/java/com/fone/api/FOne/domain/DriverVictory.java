package com.fone.api.FOne.domain;

public class DriverVictory {

	private String driverFullname;
	private Integer victories;
	
	
	public DriverVictory() {
		super();
	}

	public String getDriverFullname() {
		return driverFullname;
	}
	
	public void setDriverFullname(String driverFullname) {
		this.driverFullname = driverFullname;
	}
	
	public Integer getVictories() {
		return victories;
	}
	
	public void setVictories(Integer victories) {
		this.victories = victories;
	}
	
}
