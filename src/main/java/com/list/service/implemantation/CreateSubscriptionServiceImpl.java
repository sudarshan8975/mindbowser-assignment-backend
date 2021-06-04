package com.list.service.implemantation;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.list.entity.SubscriptionMaster;
import com.list.repository.SubscriptionRepository;
import com.list.service.CreateSubscriptionService;
import com.list.utility.Constant;
import com.list.utility.RozarpayManager;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Subscription;
@Service
public class CreateSubscriptionServiceImpl implements CreateSubscriptionService{

	@Autowired
	SubscriptionRepository subscriptionRepository;
	
	@Override
	public SubscriptionMaster getSubscription(String email) {
		// TODO Auto-generated method stub
		
		SubscriptionMaster subscription = subscriptionRepository.findByEmail(email);
		return subscription;
	}

	@Override
	public SubscriptionMaster createSubscription(SubscriptionMaster subscriptionMaster) throws Exception  {
		
		SubscriptionMaster reg = subscriptionRepository.findByEmail(subscriptionMaster.getEmail());
		System.out.println(reg);
		if(reg==null) {
			
			try{
				RazorpayClient razorpayClient=RozarpayManager.getInstance();
				JSONObject request = new JSONObject();
				request.put("plan_id", Constant.MonthlyPlanId);
				request.put("total_count", 1);
				Subscription subscription = razorpayClient.Subscriptions.create(request);
				JSONObject response = new JSONObject(subscription.toString());
				subscriptionMaster.setPlanId(response.getString("plan_id"));
				subscriptionMaster.setSubscriptionId(response.getString("id"));
				subscriptionMaster.setSubscriptionStatus(response.getString("status"));
				System.out.println(subscription.toString());
			}
			 catch(RazorpayException e) {
				  throw new Exception("problem to create subscription");
			 }
			
			reg = subscriptionRepository.save(subscriptionMaster);
		}
				
		return reg;
	}

	@Override
	public SubscriptionMaster cancleSubscription(String email) {
		// TODO Auto-generated method stub
		SubscriptionMaster subscription = subscriptionRepository.findByEmail(email);
		subscription.setSubscriptionStatus("cancelled");
		SubscriptionMaster cancleSub = subscriptionRepository.save(subscription);
		return cancleSub;
	}

	@Override
	public SubscriptionMaster completeSubscription(String email) {
		// TODO Auto-generated method stub
		SubscriptionMaster subscription = subscriptionRepository.findByEmail(email);
		subscription.setSubscriptionStatus("completed");
		SubscriptionMaster saveSub = subscriptionRepository.save(subscription);
		return saveSub;
	}

}
