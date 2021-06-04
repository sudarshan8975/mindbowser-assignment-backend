package com.list.entity;

public class JwtRequest {
	
	 private String email;
	 private String password;
	 
	 public JwtRequest() {
		// TODO Auto-generated constructor stub
	}

	public JwtRequest(String email, String password) {
		
		this.email = email;
		this.password = password;
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

	
}
