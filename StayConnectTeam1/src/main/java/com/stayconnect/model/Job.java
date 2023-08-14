package com.stayconnect.model;

import javax.validation.constraints.NotBlank;


public class Job {

	@NotBlank
	private String title;
	@NotBlank
	private String type;
	@NotBlank
	private String companyName;
	@NotBlank
	private String description;
	@NotBlank
	private String skill1;
	private String skill2;
	private String skill3;
	private String skill4;
	private String skill5;
	@NotBlank
	private String city1;
	@NotBlank
	private String state1;
	@NotBlank
	private String zipcode1;
	private String city2;
	private String state2;
	private String zipcode2;
	private String city3;
	private String state3;
	private String zipcode3;
	private String city4; 
	private String state4;
	private String zipcode4;





	private int id;
	private String poster_email;

	
	public Job() {}
	
	public Job(String title, String type, String description, String companyName, String poster_email,
			String skill1,String skill2,String skill3,String skill4,String skill5,
			String city1, String state1, String zipcode1,
			String city2, String state2, String zipcode2,
			String city3, String state3, String zipcode3,
			String city4, String state4, String zipcode4) {
		this.title = title;
		this.type = type;
		this.description = description;
		this.companyName = companyName;
		this.poster_email = poster_email;
		this.city1 = city1;
		this.state1 = state1;
		this.zipcode1 = zipcode1;
		this.city2 = city2;
		this.state2 = state2;
		this.zipcode2 = zipcode2;
		this.city3 = city3;
		this.state3 = state3;
		this.zipcode3 = zipcode3;
		this.city4 = city4;
		this.state4 = state4;
		this.zipcode4 = zipcode4;
		this.skill1 = skill1;
		this.skill2 = skill2;
		this.skill3 = skill3;
		this.skill4 = skill4;
		this.skill5 = skill5;

	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPoster_email() {
		return poster_email;
	}

	public void setPoster_email(String poster_email) {
		this.poster_email = poster_email;
	}


	public String getSkill1() {
		return skill1;
	}

	public void setSkill1(String skill1) {
		this.skill1 = skill1;
	}

	public String getSkill2() {
		return skill2;
	}

	public void setSkill2(String skill2) {
		this.skill2 = skill2;
	}

	public String getSkill3() {
		return skill3;
	}

	public void setSkill3(String skill3) {
		this.skill3 = skill3;
	}

	public String getSkill4() {
		return skill4;
	}

	public void setSkill4(String skill4) {
		this.skill4 = skill4;
	}

	public String getSkill5() {
		return skill5;
	}

	public void setSkill5(String skill5) {
		this.skill5 = skill5;
	}
	public String getCity1() {
		return city1;
	}

	public void setCity1(String city1) {
		this.city1 = city1;
	}

	public String getCity2() {
		return city2;
	}

	public void setCity2(String city2) {
		this.city2 = city2;
	}

	public String getCity3() {
		return city3;
	}

	public void setCity3(String city3) {
		this.city3 = city3;
	}

	public String getCity4() {
		return city4;
	}

	public void setCity4(String city4) {
		this.city4 = city4;
	}

	public String getState1() {
		return state1;
	}

	public void setState1(String state1) {
		this.state1 = state1;
	}

	public String getState2() {
		return state2;
	}

	public void setState2(String state2) {
		this.state2 = state2;
	}

	public String getState3() {
		return state3;
	}

	public void setState3(String state3) {
		this.state3 = state3;
	}

	public String getState4() {
		return state4;
	}

	public void setState4(String state4) {
		this.state4 = state4;
	}

	public String getZipcode1() {
		return zipcode1;
	}

	public void setZipcode1(String zipcode1) {
		this.zipcode1 = zipcode1;
	}

	public String getZipcode2() {
		return zipcode2;
	}

	public void setZipcode2(String zipcode2) {
		this.zipcode2 = zipcode2;
	}

	public String getZipcode3() {
		return zipcode3;
	}

	public void setZipcode3(String zipcode3) {
		this.zipcode3 = zipcode3;
	}

	public String getZipcode4() {
		return zipcode4;
	}

	public void setZipcode4(String zipcode4) {
		this.zipcode4 = zipcode4;
	}


}