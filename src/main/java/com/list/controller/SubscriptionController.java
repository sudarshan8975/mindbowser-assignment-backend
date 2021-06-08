package com.list.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.list.entity.FetchSubResponse;
import com.list.entity.JwtResponse;
import com.list.entity.SubscriptionMaster;
import com.list.service.CreateSubscriptionService;
import com.razorpay.*;

@RestController
@CrossOrigin("*")
public class SubscriptionController {
	
	Logger logger= LoggerFactory.getLogger(APIController.class);
	
	@Autowired
	CreateSubscriptionService createSubscriptionService;
	
	@GetMapping("/get-subscription/{email}")
	public ResponseEntity<?>  getSubscription(@PathVariable("email")  String email) throws RazorpayException {
		logger.info("[in getSubscription]:email-"+email);
		  SubscriptionMaster subscription = this.createSubscriptionService.getSubscription(email);
		  if(subscription==null) {
		  return ResponseEntity.ok(new FetchSubResponse("NO_SUBSCRIPTION"));
		  }
		  return ResponseEntity.ok(subscription);
	}
	
	@PostMapping("/create-subscription")
	public SubscriptionMaster createSubscription(@RequestBody SubscriptionMaster subscription) throws Exception {
		logger.info("[in createSubscription]");
		return this.createSubscriptionService.createSubscription(subscription);
		  
	}
	
	@PutMapping("/complete-subscription/{email}")
	public SubscriptionMaster  completeSubscription(@PathVariable("email")  String email) throws RazorpayException {
		
		  return this.createSubscriptionService.completeSubscription(email);
		  
	}
	
	@PutMapping("/cancle-subscription/{email}")
	public SubscriptionMaster cancleSubscription(@PathVariable("email")  String email) throws RazorpayException {
		return this.createSubscriptionService.cancleSubscription(email);
		   
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Object> nullPointerException(Exception e) {
		logger.error("[in SubscriptionController nullPointerException]"+e);
		return new ResponseEntity<>("NO Data Found",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> exception(Exception e) {
		logger.error("[in SubscriptionController exception]"+e);
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
