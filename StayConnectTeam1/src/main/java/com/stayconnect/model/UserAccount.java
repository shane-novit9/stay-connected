package com.stayconnect.model;

public class UserAccount {
	private String email, password, authority;
	private boolean active;	
	private Profile profile;

	public UserAccount() {
		
	}
	
	public UserAccount(String email, String password, String authority, boolean active, Profile profile) {
		this.email = email;
		this.password = password;
		this.authority = authority;
		this.active = active;
		this.profile = profile;
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

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}
