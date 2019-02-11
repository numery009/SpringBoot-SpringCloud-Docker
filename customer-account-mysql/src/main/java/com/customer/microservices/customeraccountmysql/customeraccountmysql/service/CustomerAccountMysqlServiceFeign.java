/*package com.customer.microservices.customeraccountmysql.customeraccountmysql.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.customer.microservices.customeraccountmysql.customeraccountmysql.bean.CustomerAccount;

@FeignClient(name="customer-service-mysql", url="localhost:8080/api")
public interface CustomerAccountMysqlServiceFeign {
	@GetMapping("/customer")
	public CustomerAccount[] customerDetails();
}*/