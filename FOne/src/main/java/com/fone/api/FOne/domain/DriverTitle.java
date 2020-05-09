package com.fone.api.FOne.domain;

public class DriverTitle {
	
	private String driverFullname;
	private Integer titles;
	
	public DriverTitle() {
		super();
	}
		
	public String getDriverFullname() {
		return driverFullname;
	}

	public void setDriverFullname(String driverFullname) {
		this.driverFullname = driverFullname;
	}

	public Integer getTitles() {
		return titles;
	}

	public void setTitles(Integer titles) {
		this.titles = titles;
	}

}
