package com.viva.CustomerProcessing.processor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.viva.CustomerProcessing.App;
import com.viva.CustomerProcessing.Listener.JobListener;
import com.viva.CustomerProcessing.logger.Slf4jLogger;
import com.viva.CustomerProcessing.model.Customer;



public class CustomerItemProcessor implements ItemProcessor<Customer, Customer> {
	@Autowired	
	JobListener job;
	
	@Override
    public Customer process(Customer customer) {
		Slf4jLogger logger = new Slf4jLogger(App.class);
		if(job.success.contains(customer)) {
    		logger.info("Customer with phone number "+customer.getPhoneNumber()+" --------------> Registartion Status Failed.");
    		return null;
    	}
    	else {
    		logger.info("Customer with phone number "+customer.getPhoneNumber()+" --------------> Registartion Status Success.");
    		return customer;
    	}    	

    }
}