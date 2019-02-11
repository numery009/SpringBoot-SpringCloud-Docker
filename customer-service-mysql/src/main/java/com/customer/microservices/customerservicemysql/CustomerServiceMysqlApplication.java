package com.customer.microservices.customerservicemysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import brave.sampler.Sampler;

@SpringBootApplication
// T0 Register the Eureka Naming Server use the annotation
// @EnableDiscoveryClient
@EnableDiscoveryClient
//
// Enable the Hystrix in the main method by the annotation @EnableHystrix 
@EnableHystrix

public class CustomerServiceMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceMysqlApplication.class, args);
	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}

}
