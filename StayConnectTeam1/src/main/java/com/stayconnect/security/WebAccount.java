package com.stayconnect.security;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class WebAccount {

	@NotBlank
	private String firstName;
    @NotBlank
	private String lastName;
    @NotBlank
    @Email
	private String email;
	@NotNull
    @NotBlank
	@Size(min=8,max=15)
	private String password; 
	@NotNull
    @NotBlank
	@Size(min=8,max=15)
	private String passConfirm;
	@NotBlank
	private String authority;

	public WebAccount() {
		
	}
	
	public WebAccount(String firstName, String lastName, String email, String password, String passConfirm, String authority) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.passConfirm = passConfirm;
		this.authority = authority;
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassConfirm() {
		return passConfirm;
	}
	
	public void setPassConfirm(String passConfirm) {
		this.passConfirm = passConfirm;
	}
	
	public String getAuthority() {
		return authority;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
