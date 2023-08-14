package com.stayconnect.model;

import javax.validation.constraints.NotBlank;

public class WorkExperience {
	private String email;
    @NotBlank
	private String title;
    @NotBlank
	private String description;
    @NotBlank
	private String type;
    @NotBlank
	private String companyName;
    @NotBlank
	private String position;
    @NotBlank
	private String startMonth;
    @NotBlank
	private String startYear;
    @NotBlank
	private String endMonth;
    @NotBlank
	private String endYear;
    @NotBlank
	private String city;
    @NotBlank
    private String state;
    @NotBlank
    private String zipcode;
	
	public WorkExperience() {}
	
	public WorkExperience(String email, String title, String description, String type, String companyName,
			String position, String startMonth, String startYear, String endMonth, String endYear, String city,
			String state, String zipcode) {
		super();
		this.email = email;
		this.title = title;
		this.description = description;
		this.type = type;
		this.companyName = companyName;
		this.position = position;
		this.startMonth = startMonth;
		this.startYear = startYear;
		this.endMonth = endMonth;
		this.endYear = endYear;
		this.city = city;
		this.state = state;
		this.zipcode =zipcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	public String getEndYear() {
		return endYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
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

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
}
