package com.bank.rest.api.entity;

import java.time.LocalDate;
import java.util.Currency;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "customer_account_details")
@Entity
@Data
public class Account {

	@Id
	@Column(name = "CustomerId", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;

	@Column(name = "AccountHolderName")
	private String accountHolderName;

	@Column(name = "Amount")
	private Double amount;

	@Column(name = "IfscCode")
	private String ifscCode;

	@Column(name = "BankName")
	private String bankName;

	@Column(name = "City")
	private String city;

	@Column(name = "Date")
	private LocalDate currentDate;

	@Column(name = "AccountNumber")
	private Long accountNumber;

	

	@Column(name = "AccountType")
	private String accountType;
	
	@Column(name="AccountStatus")
	private String accountStatus;

}
