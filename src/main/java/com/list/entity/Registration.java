package com.list.entity;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "registration_master")
public class Registration implements UserDetails{
	
	 @Id
	 @NotBlank(message = "Email Should Not Be Black!")
	 private String email;
	 
	 @NotBlank(message = "First Name Should Not Be Blank!")
	 @Size(max = 100,message = "Use First Name Is 100 Character")
	 @Column(length = 100)
	 private String firstName;
	 
	 @NotBlank(message = "Last Name Should Not Be Blank!")
	 @Size(max = 100,message = "Use Last Name Is 100 Character")
	 @Column(length = 100)
	 private String lastName;
	 
	 @NotBlank(message = "First Name Should Not Be Blank!")
	 @Column(length = 100)
	 private String password;
	 
	 
	 @NotBlank(message = "Address Should Not Be Blank!")
	 @Column(length = 100)
	 private String address;
	 
	 
	 @Temporal(TemporalType.DATE)
	 @JsonFormat(pattern="dd-MM-yyyy")
	 private Date birthDate;
	 
	 
	 @NotBlank(message = "Company Name Should Not Be Blank!")
	 @Column(length = 100)
	 private String companyName;
	 
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
