package com.customer.microservices.customeraccountmysql.customeraccountmysql.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class CustomerAccount {

	private long id;
	private String firstName;
	private String lastName;
	private String socialSecurityNumber;
	private Date dateOfBirth;
	private double totalLoanAmount;
	private int bonusPoints;
	private Date memberSince;
	private int rating;

	private String accountNumber;
	private boolean accountType;
	private double creditAmount;

	public CustomerAccount() {
		super();
	}

	public CustomerAccount(long id, String firstName, String lastName,
			String socialSecurityNumber, Date dateOfBirth,
			double totalLoanAmount, int bonusPoints, Date memberSince,
			int rating, String accountNumber, boolean accountType,
			double loanAmount) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.socialSecurityNumber = socialSecurityNumber;
		this.dateOfBirth = dateOfBirth;
		this.totalLoanAmount = totalLoanAmount;
		this.bonusPoints = bonusPoints;
		this.memberSince = memberSince;
		this.rating = rating;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.creditAmount = loanAmount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public double getTotalLoanAmount() {
		return totalLoanAmount;
	}

	public void setTotalLoanAmount(double totalLoanAmount) {
		this.totalLoanAmount = totalLoanAmount;
	}

	public int getBonusPoints() {
		return bonusPoints;
	}

	public void setBonusPoints(int bonusPoints) {
		this.bonusPoints = bonusPoints;
	}

	public Date getMemberSince() {
		return memberSince;
	}

	public void setMemberSince(Date memberSince) {
		this.memberSince = memberSince;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public boolean isAccountType() {
		return accountType;
	}

	public void setAccountType(boolean accountType) {
		this.accountType = accountType;
	}

	public double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}

	@Override
	public String toString() {
		return "CustomerAccount [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", socialSecurityNumber="
				+ socialSecurityNumber + ", dateOfBirth=" + dateOfBirth
				+ ", totalLoanAmount=" + totalLoanAmount + ", bonusPoints="
				+ bonusPoints + ", memberSince=" + memberSince + ", rating="
				+ rating + ", accountNumber=" + accountNumber
				+ ", accountType=" + accountType + ", creditAmount=" + creditAmount
				+ "]";
	}

}
