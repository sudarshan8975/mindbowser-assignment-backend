package com.list.utility;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

public class RozarpayManager
{
    
    private static RazorpayClient razorpayClient = null;
  
    public static RazorpayClient getInstance() throws RazorpayException
    {
        if (razorpayClient == null)
        	 razorpayClient = new RazorpayClient("rzp_test_IN9yB9gfcsbBHv", "6KnOPpW5oQMniQxGvTC0egTo");
        return razorpayClient;
    }
}