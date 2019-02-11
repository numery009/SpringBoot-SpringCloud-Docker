package com.customer.microservices.customerservicemysql.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false)
	private String socialSecurityNumber;

	@Temporal(TemporalType.DATE)
	@Column(name = "dob", unique = true, nullable = false, length = 10)
	private Date dateOfBirth;
	@Column(nullable = false)
	private double totalLoanAmount;
	@Column(nullable = false)
	private int bonusPoints;
	@Temporal(TemporalType.DATE)
	@Column(name = "customer_since", unique = true, nullable = false, length = 10)
	private Date memberSince;

	@Column()
	private int rating;

	public Customer() {
		super();
	}

	public Customer(long id, String firstName, String lastName,
			String socialSecurityNumber, Date dateOfBirth,
			double totalLoanAmount, int bonusPoints, Date memberSince,
			int rating) {
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

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", socialSecurityNumber="
				+ socialSecurityNumber + ", dateOfBirth=" + dateOfBirth
				+ ", totalLoanAmount=" + totalLoanAmount + ", bonusPoints="
				+ bonusPoints + ", memberSince=" + memberSince + ", rating="
				+ rating + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bonusPoints;
		result = prime * result
				+ ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((memberSince == null) ? 0 : memberSince.hashCode());
		result = prime * result + rating;
		result = prime
				* result
				+ ((socialSecurityNumber == null) ? 0 : socialSecurityNumber
						.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalLoanAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (bonusPoints != other.bonusPoints)
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (memberSince == null) {
			if (other.memberSince != null)
				return false;
		} else if (!memberSince.equals(other.memberSince))
			return false;
		if (rating != other.rating)
			return false;
		if (socialSecurityNumber == null) {
			if (other.socialSecurityNumber != null)
				return false;
		} else if (!socialSecurityNumber.equals(other.socialSecurityNumber))
			return false;
		if (Double.doubleToLongBits(totalLoanAmount) != Double
				.doubleToLongBits(other.totalLoanAmount))
			return false;
		return true;
	}

}