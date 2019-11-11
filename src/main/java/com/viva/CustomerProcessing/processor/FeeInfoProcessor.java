package com.viva.CustomerProcessing.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.viva.CustomerProcessing.Listener.JobListener;
import com.viva.CustomerProcessing.model.Customer;
import com.viva.CustomerProcessing.model.FeeInfo;



public class FeeInfoProcessor implements ItemProcessor<Customer, FeeInfo> {
	
	FeeInfo fee=new FeeInfo();
	@Autowired	
	JobListener job;
	
	FeeInfo feeinfo=new FeeInfo();
	
	@Override
    public FeeInfo process(Customer customer) {
		
		if(job.success.contains(customer)) {
    		System.out.println("failed");
    		return null;
    	}
    	else {
    	  feeinfo.setPhoneNumber(customer.getPhoneNumber());
    		System.out.println("success");
    		return feeinfo;
    	}    	

    }
    }
