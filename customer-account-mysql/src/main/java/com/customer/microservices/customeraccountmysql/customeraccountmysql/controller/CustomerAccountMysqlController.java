package com.customer.microservices.customeraccountmysql.customeraccountmysql.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.microservices.customeraccountmysql.customeraccountmysql.bean.CustomerAccount;
import com.customer.microservices.customeraccountmysql.customeraccountmysql.service.CustomerAccountMysqlService;

@RestController
@RequestMapping("/api/v1")
public class CustomerAccountMysqlController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CustomerAccountMysqlService customerAccountService;
	
	@GetMapping("/customer")
	public CustomerAccount customerDetails() {
		return customerAccountService.customerDetails(); 
	}
	
	@GetMapping("/customer-feign")
	public CustomerAccount customerDetailswithFeign() {
		
		CustomerAccount customerAccount= customerAccountService.customerDetailswithFeign();
		logger.info("{}", customerAccount);
		return customerAccount; 
	}
}
