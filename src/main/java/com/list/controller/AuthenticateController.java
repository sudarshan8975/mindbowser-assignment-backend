package com.list.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.list.config.JwtUtils;
import com.list.entity.JwtRequest;
import com.list.entity.JwtResponse;
import com.list.service.implemantation.UserDetailsServiceImpl;

@RestController
public class AuthenticateController {
	
	Logger logger= LoggerFactory.getLogger(APIController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl detailsServiceImpl;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		
		try {
			logger.info("[in generateToken]:Mail Id-"+jwtRequest.getEmail());
			logger.info("[in generateToken]:Password-"+jwtRequest.getPassword());
			this.authenticate(jwtRequest.getEmail(),jwtRequest.getPassword());
			
		}
		catch(Exception e) {
			logger.error("[in generateToken] "+e.getMessage());
			throw new Exception("Incorrect username or password", e);
		}
		UserDetails userDetails = this.detailsServiceImpl.loadUserByUsername(jwtRequest.getEmail());
		String token = this.jwtUtils.generateToken(userDetails);
		logger.error("[in generateToken response] "+token);
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
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> exception(Exception e) {
		return new ResponseEntity<>(new JwtResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
	}
}
