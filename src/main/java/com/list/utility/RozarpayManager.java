package com.list.utility;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

public class RozarpayManager
{
    
    private static RazorpayClient razorpayClient = null;
  
    public static RazorpayClient getInstance() throws RazorpayException
    {
        if (razorpayClient == null)
        	 razorpayClient = new RazorpayClient(Constant.razorpayKeyId, Constant.razorpaySecretKey);
        return razorpayClient;
    }
}