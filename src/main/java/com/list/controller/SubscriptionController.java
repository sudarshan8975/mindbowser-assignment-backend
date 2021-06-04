package com.list.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.list.entity.FetchSubResponse;
import com.list.entity.SubscriptionMaster;
import com.list.service.CreateSubscriptionService;
import com.razorpay.*;

@RestController
@CrossOrigin("*")
public class SubscriptionController {
	
	@Autowired
	CreateSubscriptionService createSubscriptionService;
	
	@GetMapping("/get-subscription/{email}")
	public ResponseEntity<?>  getSubscription(@PathVariable("email")  String email) throws RazorpayException {
		System.out.println("suddd"+email);
		  SubscriptionMaster subscription = this.createSubscriptionService.getSubscription(email);
		  if(subscription==null) {
		  return ResponseEntity.ok(new FetchSubResponse("NO_SUBSCRIPTION"));
		  }
		  return ResponseEntity.ok(subscription);
	}
	
	@PostMapping("/create-subscription")
	public SubscriptionMaster createSubscription(@RequestBody SubscriptionMaster subscription) throws Exception {
		System.out.println(subscription.getEmail());
		  SubscriptionMaster sub = this.createSubscriptionService.createSubscription(subscription);
		  return sub;
	}
	
	@PutMapping("/complete-subscription/{email}")
	public ResponseEntity<?>  completeSubscription(@PathVariable("email")  String email) throws RazorpayException {
		System.out.println("suddd"+email);
		  SubscriptionMaster subscription = this.createSubscriptionService.completeSubscription(email);
		  return ResponseEntity.ok(subscription);
	}
	
	@PutMapping("/cancle-subscription/{email}")
	public ResponseEntity<?>  cancleSubscription(@PathVariable("email")  String email) throws RazorpayException {
		System.out.println("suddd"+email);
		  SubscriptionMaster subscription = this.createSubscriptionService.cancleSubscription(email);
		  return ResponseEntity.ok(subscription);
	}
}
