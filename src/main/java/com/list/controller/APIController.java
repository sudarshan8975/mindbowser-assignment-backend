package com.list.controller;

import javax.validation.Valid;

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
	@Autowired
	private RegistrationService registrationService;

	@PostMapping("/saveuser")
	public ResponseEntity<?> saveUser(@Valid @RequestBody Registration registration, BindingResult bindingResult) {
		String retVal = null;
		if(bindingResult.hasErrors()) {
			System.out.println(bindingResult.getFieldErrorCount("emialid"));
		}
		else {
		 retVal = this.registrationService.createUser(registration);
		}
		
		return ResponseEntity.ok(new RegistrationResponse(retVal));
	}
}
