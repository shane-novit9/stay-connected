package com.stayconnect.model;


public class Profile {
	private String email;
	private String firstName;
	private String lastName;
	private String biography;
	private String jobtitle1;
	private String jobtitle2;
	private String jobtitle3;
	private String graduationYear;
	private String major;
	private String city1;
	private String state1;
	private String skill1;
	private String skill2;
	private String skill3;
	private String skill4;
	private String skill5;
	private String zipcode1;
	private String phone1;
	private String phone2;
	private String phone3;
	private String company_name;
	private String officeNum;
	public Profile() {
		
	}
	
	public Profile(
			String email, String fName, String lName, 
			String bio,String skill1,String skill2, 
			String skill3,String skill4, String skill5,
			String city1, String state1, String zipcode1, 
			String phone1, String phone2, String phone3, 
			String graduationYear,String company_name,
			String jobtitle1, String jobtitle2, String jobtitle3) {
		this.email = email;
		this.firstName = fName;
		this.lastName = lName;
		this.biography = bio;
		this.city1 = city1;
		this.state1 = state1;
		this.zipcode1 = zipcode1;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.phone3 = phone3;
		this.skill1 = skill1;
		this.skill2 = skill2;
		this.skill3 = skill3;
		this.skill4 = skill4;
		this.skill5 = skill5;
		this.graduationYear = graduationYear;
		this.company_name = company_name;
		this.jobtitle1 = jobtitle1;
		this.jobtitle2 = jobtitle2;
		this.jobtitle3 = jobtitle3;
		
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
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

	public String getState1() {
		return state1;
	}

	public void setState1(String state1) {
		this.state1 = state1;
	}

	public String getZipcode1() {
		return zipcode1;
	}

	public void setZipcode1(String zipcode1) {
		this.zipcode1 = zipcode1;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationDate(String graduationYear) {
		this.graduationYear = graduationYear;
	}
	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getOfficeNum() {
		return officeNum;
	}

	public void setOfficeNum(String officeNum) {
		this.officeNum = officeNum;
	}
	public String getJobtitle1() {
		return jobtitle1;
	}

	public void setJobtitle1(String jobtitle1) {
		this.jobtitle1 = jobtitle1;
	}

	public String getJobtitle2() {
		return jobtitle2;
	}

	public void setJobtitle2(String jobtitle2) {
		this.jobtitle2 = jobtitle2;
	}

	public String getJobtitle3() {
		return jobtitle3;
	}

	public void setJobtitle3(String jobtitle3) {
		this.jobtitle3 = jobtitle3;
	}

	public void setGraduationYear(String graduationYear) {
		this.graduationYear = graduationYear;
	}

}
