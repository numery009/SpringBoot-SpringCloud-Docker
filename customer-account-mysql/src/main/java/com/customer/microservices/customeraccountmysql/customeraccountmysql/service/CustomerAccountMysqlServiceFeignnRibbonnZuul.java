
package com.customer.microservices.customeraccountmysql.customeraccountmysql.service;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.customer.microservices.customeraccountmysql.customeraccountmysql.bean.CustomerAccount;

// To define only fiegn we will use the follwoing protocal
// @FeignClient(name="customer-service-mysql", url="localhost:8080/api")

// Follwoing one we use for Feign and Ribbon // @FeignClient(name =
//"customer-service-mysql") // @GetMapping("/api/customer")

// if we want the request will go through the Zuul API between the
//  Microservices // then we need the follwoing declaration

@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "customer-service-mysql")
public interface CustomerAccountMysqlServiceFeignnRibbonnZuul {

	@GetMapping("/customer-service-mysql/api/customer")
	public CustomerAccount[] customerDetails();
}
