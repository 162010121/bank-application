package com.bank.rest.api.entity;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "customer_account_details")
@Entity
@Data
public class Account {

	@Id
	@Column(name = "CustomerId", nullable = false)
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
	private Date currentDate;

	@Column(name = "AccountNumber")
	private Long accountNumber;

	@Column(name = "AccountType")
	private String accountType;

	@Column(name = "AccountStatus")
	private String accountStatus;

}
