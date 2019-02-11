package com.customer.microservices.customeraccountmysql.customeraccountmysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

@SpringBootApplication
// If we user Feign to consume the other micro service then we need to enable
// feign and give the package to scan
@EnableFeignClients("com.customer.microservices.customeraccountmysql.customeraccountmysql")
//
// T0 Register the Eureka Naming Server user the annotation
// @EnableDiscoveryClient
@EnableDiscoveryClient
//
public class CustomerAccountMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerAccountMysqlApplication.class, args);
	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
