package com.customer.microservices.customerservicemysql.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.microservices.customerservicemysql.bean.Customer;

@Repository
public interface CustomerServiceMysqlRepository extends JpaRepository<Customer, Long> {
	List<Customer> findAll();		
}
