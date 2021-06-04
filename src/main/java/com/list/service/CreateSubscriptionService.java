package com.list.service;

import com.list.entity.SubscriptionMaster;

public interface CreateSubscriptionService {

	public SubscriptionMaster getSubscription(String email);
	public SubscriptionMaster createSubscription(SubscriptionMaster subscriptionMaster) throws Exception;
	public SubscriptionMaster cancleSubscription(String email);
	public SubscriptionMaster completeSubscription(String email);
	
}
