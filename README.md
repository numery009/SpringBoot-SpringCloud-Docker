# Spring-Boot-And-Spring-Cloud

Design microservice with Spring Boot and Spring Cloud----

1) Spring Cloud Config Server and Bus.
2) Load Balancing with Ribbon and Feign.
3) Implementing Naming Server with Eureka.
4) Distibuted Tracing with Zipkin.
5) Fault Tolerance with Hystrix.


a) Spring Cloud Config Server: Centralized external configuration management backed by a git repository. The configuration resources map directly to Spring Environment but could be used by non-Spring applications if desired.

b) Spring Cloud Bus: An event bus for linking services and service instances together with distributed messaging. Useful for propagating state changes across a cluster (e.g. config change events).

c) Spring Cloud OpenFeign: Spring Cloud OpenFeign provides integrations for Spring Boot apps through autoconfiguration and binding to the Spring Environment and other Spring programming model idioms.

d )Spring Cloud Netflix: Integration with various Netflix OSS components (Eureka, Hystrix, Zuul, Archaius, etc.)
         i) Eureka: This is a Naming Server. All microservices will be registered on Eureka server.
        ii) Zuul : This is an API Gateway. It uses for Security, Rate Limits, Fault Tolerance, Service Aggregation.

e) Spring Cloud Sleuth: Distributed tracing for Spring Cloud applications, compatible with Zipkin, HTrace and log-based (e.g. ELK) tracing.

f) Spring Cloud Security: Provides support for load-balanced OAuth2 rest client and authentication header relays in a Zuul proxy.




# Ports

<table>
   <tr>
      <td>
         Application
      </td>      
      <td>
         Ports
      </td>
   </tr>    
   <tr>
      <td>
         Spring Cloud Config Server
      </td>      
      <td>
         8888
      </td>
   </tr> 

   <tr>
      <td>
         Customer Service
      </td>      
      <td>
         8080, 8081, ..
      </td>
   </tr> 
   <tr>
      <td>
         Customer Account Service
      </td>      
      <td>
         8100, ...
      </td>
   </tr> 
   <tr>
      <td>
         Netflix Eureka Naming Server
      </td>      
      <td>
         8761
      </td>
   </tr> 
   <tr>
      <td>
         Netflix Zuul API Gateway Server
      </td>      
      <td>
         8765
      </td>
   </tr> 
   <tr>
      <td>
         Zipkin Distributed Tracing Server
      </td>      
      <td>
         9411
      </td>
   </tr>    
<table>


# URLs

<table>
   <tr>
      <td>
         Application
      </td>      
      <td>
         URL
      </td>
   </tr>   
   <tr>
      <td>
         Spring Cloud Config Server
      </td>      
      <td>         
http://localhost:8888/customer-service-mysql/dev,
http://localhost:8888/customer-service-mysql/test,
http://localhost:8888/customer-service-mysql/stage,
http://localhost:8888/customer-service-mysql/prod
      </td>
   </tr>   
   <tr>
      <td>
         Customer Service - Direct Call
      </td>      
      <td>
         http://localhost:8080/api/customer, http://localhost:8081/api/customer
      </td>
   </tr>
    <tr>
      <td>
         Customer Account Service
      </td>      
      <td>
         http://localhost:8100/api/v1/customer
      </td>
   </tr>   
    <tr>
      <td>
         Customer Account Service – Feign
      </td>      
      <td>
         http://localhost:8100/api/v1/customer-feign
      </td>
   </tr>   
    <tr>
      <td>
         Eureka
      </td>      
      <td>
         http://localhost:8761/
      </td>
   </tr>   
   <tr>
      <td>
         Zuul – Customer Account Service Feign
      </td>      
      <td>
         http://localhost:8765/customer-account-mysql/api/v1/customer-feign
      </td>
   </tr>  
   
   <tr>
      <td>
         Zuul – Customer Account Service 
      </td>      
      <td>
         http://localhost:8765/customer-account-mysql/api/v1/customer
      </td>
   </tr>  
   <tr>
      <td>
         Zuul – Customer Service
      </td>      
      <td>
         http://localhost:8765/customer-service-mysql/api/customer
      </td>
   </tr>   
   <tr>
      <td>
         Zipkin
      </td>      
      <td>
         http://localhost:9411/zipkin/
      </td>
   </tr>   
   <tr>
      <td>
         Spring Cloud Bus Refresh
      </td>      
      <td>
         http://localhost:8080/bus/refresh
      </td>
   </tr>   
</table>




# Zipkin Installation
Quick Start Page- 
   https://zipkin.io/pages/quickstart

Downloading Zipkin Jar-
  https://search.maven.org/remote_content?g=io.zipkin.java&a=zipkin-server&v=LATEST&c=exec

Command to run - 
RABBIT_URI=amqp://localhost java -jar zipkin-server-2.5.2-exec.jar

# VM Argument
-Dserver.port=8081


# RabbitMQ
Install and Download -- https://www.rabbitmq.com/download.html

# Distributed Tracing
 In the microservices architerture we need to have the distributed tracing for debugging or find out where the error is.
         1) Spring Cloud Sleuth- It will assign a unique ID to all the request.
         2) Zipkin- It is a distributed tracing system.
 
 How does it work?: All the logs of the all microserivices we will sent to the Zipkin Server through the RabbitMQ. Zipkin                       server will be the centrlize logging server where we can track any request. In the RabbitMQ Exchange,                       Queue and Interception of Messages are set by Spring Boot Auto Configuration - Both in Server and the                       Client.
 
 

# Feign
      It is basically uses to call other micro services. It is basically makes easire to call other microservices. 
      To add Feign need to do the following steps--
      1) Add the dependency of the OpenFeign.
      2) @RnableFeignClients("Package") (with "package")
      3) Create a Feign Proxy which will contain the service-name, URL, verb of the URL, methodName and reponse type.


# Ribbon
      It is basically client side load balancing. Ribbon has to be installed on the client side to consume the  micro             serivces.
      For Ribbon we need to use the follwoing steps-
      1) Add Ribbon Dependency.
      2) Enable the @RibbonClient on the Feign proxy with the microservices that will be consume.
      3) If we are not using the Zuul then we need to add the list of server on the properties file.
      4) If we use the Zuul then we can skip the step 3 and add the zuul server name on the @FeignClient("Zuul-Server-Name")
         on the Proxy.
      
 # Send All Request through Zuul
 
 package com.customer.microservices.customeraccountmysql.customeraccountmysql.service;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.customer.microservices.customeraccountmysql.customeraccountmysql.bean.CustomerAccount;

// To define only fiegn we will use the follwoing protocal
// @FeignClient(name="customer-service-mysql", url="localhost:8080/api")


// Follwoing one we use for Feign and Ribbon
// @FeignClient(name = "customer-service-mysql")
// @GetMapping("/api/customer")

// if we want the request will go through the Zuul API between the Microservices 
// then we need the follwoing declaration
@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "customer-service-mysql")
public interface CustomerAccountMysqlServiceFeignnRibbonnZuul {
	@GetMapping("/customer-service-mysql/api/customer")
	public CustomerAccount[] customerDetails();
}

# Fault Tolerance with Hystrix
      Microservices architecture is number of component. Instead of one application there are lots services which are running and depending to each another. It is possible that couple of services are down. And that could cause to pull down entire chain of microservices that are dependent on them. Hystrix will help us to solve this issue. It will build the fault tolerance of the microservices. For this we need to do the follwoing steps-
      1) Spring-Cloud-Starter-netflix-hystrix ad this dependency.
      2) Enable the Hystrix in the main method. 
      3) Add the annotation on the method @HystrixCommand(fallbackMethod="fallBackgetAllCustomerExample")
      4) Create new fallback method.
      


