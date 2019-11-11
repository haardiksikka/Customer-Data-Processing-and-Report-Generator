package com.viva.CustomerProcessing.Listener;


import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;


import com.viva.CustomerProcessing.model.Customer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Component
public class JobListener implements JobExecutionListener {
	
	private  final String driverClassName = "org.mariadb.jdbc.Driver";
    private  final String url = "jdbc:mariadb://localhost:3309/customer_processing";
    private  final String dbUsername = "root";
    private  final String dbPassword = "root123";
    
    @Autowired
    private  DataSource dataSource;

	public static List<Customer> success;
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JobListener() {
		this.jdbcTemplate = new JdbcTemplate();
		success = new ArrayList<Customer>();
	}
	
	@Autowired
	public JobListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		success = new ArrayList<Customer>();
	}

	public void beforeJob(JobExecution jobExecution) {
	//	this.jdbcTemplate.setDataSource(dataSource)		
		dataSource = getDataSource();
		this.jdbcTemplate.setDataSource(dataSource);
		this.jdbcTemplate.query("SELECT first_name, middle_name,last_name,address, city, phone_number FROM customer",
				(rs, row) -> new Customer(
					rs.getString(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(6),
					rs.getString(5),
					rs.getString(4)
					)).forEach(customer -> success.add(customer));

	}
	public DriverManagerDataSource getDataSource() {
		 
		  DriverManagerDataSource dataSource = new DriverManagerDataSource();
		 
		  dataSource.setDriverClassName(driverClassName);
		 
		  dataSource.setUrl(url);
		 
		  dataSource.setUsername(dbUsername);
		 
		  dataSource.setPassword(dbPassword);
		 
		  return dataSource;
		    }
	
	public List<Customer> getDbRecords(){
		return this.success;
	}
		@Override
		public void afterJob(JobExecution jobExecution) {
			//add code here
		}
}
