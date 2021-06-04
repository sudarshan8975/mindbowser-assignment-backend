package com.list.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.list.config.JwtUtils;
import com.list.entity.JwtRequest;
import com.list.entity.JwtResponse;
import com.list.service.implemantation.UserDetailsServiceImpl;

@RestController
public class AuthenticateController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl detailsServiceImpl;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		
		try {
			
			this.authenticate(jwtRequest.getEmail(),jwtRequest.getPassword());
			
		}
		catch(Exception e) {
			throw new Exception("Incorrect username or password", e);
		}
		UserDetails userDetails = this.detailsServiceImpl.loadUserByUsername(jwtRequest.getEmail());
		String token = this.jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
		
	}
	
	private void authenticate(String username,String password) throws Exception {
		
		try {
			 authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}
		catch(Exception e1) {
			throw new Exception("Incorrect username or password", e1);
		} 
	}
}
