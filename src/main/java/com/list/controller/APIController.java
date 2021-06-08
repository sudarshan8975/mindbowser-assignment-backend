package com.list.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.list.entity.Registration;
import com.list.entity.RegistrationResponse;
import com.list.service.RegistrationService;
@RestController
public class APIController {
	Logger logger= LoggerFactory.getLogger(APIController.class);
	
	@Autowired
	private RegistrationService registrationService;
	
	@PostMapping("/saveuser")
	public ResponseEntity<?> saveUser(@Valid @RequestBody Registration registration, BindingResult bindingResult) {
		
		logger.info("[in saveUser]");
		String retVal = null;
		if(bindingResult.hasErrors()) {
			retVal = "Please Enter All Fields";
		}
		else {
		    retVal = this.registrationService.createUser(registration);
		}
		logger.info("[in saveUser response]"+retVal);
		return ResponseEntity.ok(new RegistrationResponse(retVal));
	}
}
