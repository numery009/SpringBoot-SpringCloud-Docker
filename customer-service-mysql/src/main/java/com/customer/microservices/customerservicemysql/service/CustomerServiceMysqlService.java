package com.customer.microservices.customerservicemysql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.microservices.customerservicemysql.bean.Customer;
import com.customer.microservices.customerservicemysql.repository.CustomerServiceMysqlRepository;

@Service
public class CustomerServiceMysqlService {

	@Autowired
	CustomerServiceMysqlRepository customerRepository;

	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}

	public List<Customer> getAllCustomer() {
		return customerRepository.findAll();
	}
}
