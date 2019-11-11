package com.viva.CustomerProcessing.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.viva.CustomerProcessing.App;
import com.viva.CustomerProcessing.Listener.JobListener;
import com.viva.CustomerProcessing.model.Customer;
import com.viva.CustomerProcessing.model.FeeInfo;



public class FeeInfoProcessor implements ItemProcessor<Customer, FeeInfo> {
	
	FeeInfo fee=new FeeInfo();
	@Autowired	
	JobListener job;

	
	Set<Customer> fileData = new HashSet<Customer>();
	
	@Override
    public FeeInfo process(Customer customer) {
		
		if(job.success.contains(customer) || fileData.contains(customer)) {    		
    		return null;
    	}
    	else {
    		fileData.add(customer);
    		fee.setFeeAmount(App.getFeeAmount());
    		fee.setPhoneNumber(customer.getPhoneNumber());   		
    		return fee;
    	}    	

    }
    }
