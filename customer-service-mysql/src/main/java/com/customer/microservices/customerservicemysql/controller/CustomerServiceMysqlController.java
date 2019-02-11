package com.customer.microservices.customerservicemysql.controller;

import java.util.List;

import javax.validation.Valid;
//import javax.xml.ws.FaultAction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.microservices.customerservicemysql.bean.Customer;
import com.customer.microservices.customerservicemysql.service.CustomerServiceMysqlService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/api")
public class CustomerServiceMysqlController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CustomerServiceMysqlService customerService;

	@PostMapping("/customer")
	public Customer createCustomer(@Valid @RequestBody Customer customer) {
		return customerService.createCustomer(customer);

	}

	@DeleteMapping("/customer/{id}")
	public void deleteCustomer(@PathVariable("id") Long id) {
		this.customerService.deleteCustomer(id);
	}

	@GetMapping("/customer")
	public List<Customer> getallCustomer() {
		List<Customer> lstCustomer= customerService.getAllCustomer();
		logger.info("{}", lstCustomer);		
		return lstCustomer;
	}
	
	@GetMapping("/fault-tolerance-example")
	@HystrixCommand(fallbackMethod="fallBackgetAllCustomerExample")
	public Customer getAllCustomerExample(){
		throw new RuntimeException("Not available");
	}
	
	public Customer fallBackgetAllCustomerExample(){
		return new Customer();
	}
}
