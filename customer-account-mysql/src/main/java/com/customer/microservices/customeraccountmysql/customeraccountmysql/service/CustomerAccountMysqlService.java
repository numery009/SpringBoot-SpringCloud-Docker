package com.customer.microservices.customeraccountmysql.customeraccountmysql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.customer.microservices.customeraccountmysql.customeraccountmysql.bean.CustomerAccount;

@Service
public class CustomerAccountMysqlService {

	@Autowired
	private CustomerAccount customerAccount;

	@Autowired
	private CustomerAccountMysqlServiceFeignnRibbonnZuul proxy;

	@Value("${customer.accountNumber}")
	private String accountNumber;

	@Value("${customer.accountType}")
	private String accountType;

	@Value("${customer.creditAmount}")
	private String creditAmount;

	public CustomerAccount customerDetails() {

		///////////////////////////////////////////////////////////////////////////////

		// Consume an external Web Service by using the HTTP Rest Template from
		// Spring//

		/*
		 * ResponseEntity<CustomerAccount[]> responseEntity = new RestTemplate()
		 * .getForEntity("http://localhost:8080/api/customer", CustomerAccount[].class);
		 */
		////////////////////////////////////////////////////////////////////////////////

		ResponseEntity<CustomerAccount[]> responseEntity = new RestTemplate()
				.getForEntity("http://customer-service-mysql:8080/api/customer", CustomerAccount[].class);

		CustomerAccount[] responseList = responseEntity.getBody();

		for (CustomerAccount response : responseList) {
			customerAccount.setId(response.getId());
			customerAccount.setFirstName(response.getFirstName());
			customerAccount.setLastName(response.getLastName());
			customerAccount.setSocialSecurityNumber(response.getSocialSecurityNumber());
			customerAccount.setDateOfBirth(response.getDateOfBirth());
			customerAccount.setTotalLoanAmount(response.getTotalLoanAmount());
			customerAccount.setBonusPoints(response.getBonusPoints());
			customerAccount.setMemberSince(response.getMemberSince());
			customerAccount.setRating(response.getRating());
			customerAccount.setAccountNumber(accountNumber);
			customerAccount.setAccountType(Boolean.valueOf(accountType));
			customerAccount.setCreditAmount(Double.valueOf(creditAmount));
			break;
		}
		return customerAccount;

		// Consume an external Web Service by using the HTTP Rest Template from
		// Spring//
	}

	public CustomerAccount customerDetailswithFeign() {

		// Consume an external Web Service by using the Feign//
		CustomerAccount[] responseList = proxy.customerDetails();

		for (CustomerAccount response : responseList) {
			customerAccount.setId(response.getId());
			customerAccount.setFirstName(response.getFirstName());
			customerAccount.setLastName(response.getLastName());
			customerAccount.setSocialSecurityNumber(response.getSocialSecurityNumber());
			customerAccount.setDateOfBirth(response.getDateOfBirth());
			customerAccount.setTotalLoanAmount(response.getTotalLoanAmount());
			customerAccount.setBonusPoints(response.getBonusPoints());
			customerAccount.setMemberSince(response.getMemberSince());
			customerAccount.setRating(response.getRating());
			customerAccount.setAccountNumber(accountNumber);
			customerAccount.setAccountType(Boolean.valueOf(accountType));
			customerAccount.setCreditAmount(Double.valueOf(creditAmount));
			break;
		}
		return customerAccount;

		// Consume an external Web Service by using the Feign//
	}

}
