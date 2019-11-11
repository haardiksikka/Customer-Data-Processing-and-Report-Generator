package com.viva.CustomerProcessing;

import java.util.Scanner;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static double feeAmount;
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter fee amount :");
		feeAmount = sc.nextDouble();
		
		setFeeAmount(feeAmount);
		String[] springConfig  = 
			{"spring/batch/config/context.xml",
				"spring/batch/config/database.xml", 				
				"spring/batch/jobs/job-report.xml" 
			};
		
		ApplicationContext context = 
				new ClassPathXmlApplicationContext(springConfig);
		
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("reportJob");

		try {

			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Exit Status : " + execution.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done");

	}
	public static void setFeeAmount(double feeAmt) {
		feeAmount = feeAmt;
	}
	public static double getFeeAmount() {
		return feeAmount;
	}
}
