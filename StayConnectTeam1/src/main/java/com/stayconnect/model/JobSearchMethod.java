package com.stayconnect.model;

public class JobSearchMethod {
	private String title, type, companyName, email, city, state, zip;
	
	public JobSearchMethod() {
		
	}
	
	public JobSearchMethod(String title, String type, String companyName, String email, 
			String city, String state, String zip) {
		this.title = title;
		this.type = type;
		this.companyName = companyName;
		this.email = email;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
}
