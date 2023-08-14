package com.stayconnect.model;

public class ProfileSearchMethod {
	private String email, city, state, zip, firstName, lastName,
	companyName, officeNum, graduationDate;
	
	public ProfileSearchMethod() {
		
	}
	
	public ProfileSearchMethod(String email, String city, String state, String zip, 
			String firstName, String lastName, String companyName, 
			String officeNum, String graduationDate) {
		this.email = email;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.firstName = firstName;
		this.lastName = lastName;
		this.companyName = companyName;
		this.officeNum = officeNum;
		this.graduationDate = graduationDate;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOfficeNum() {
		return officeNum;
	}

	public void setOfficeNum(String officeNum) {
		this.officeNum = officeNum;
	}

	public String getGraduationDate() {
		return graduationDate;
	}

	public void setGraduationDate(String graduationDate) {
		this.graduationDate = graduationDate;
	}
}
