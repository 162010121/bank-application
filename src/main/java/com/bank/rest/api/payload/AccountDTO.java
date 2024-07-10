package com.bank.rest.api.payload;

import java.util.Date;

import lombok.Data;

@Data
public class AccountDTO {

	private Long customerId;

	private String accountHolderName;

	private Double currentBalance;

	private String ifscCode;

	private String city;

	private Date currentDate;

	private Long accountNumber;

	private String bankName;

	private String message;

	private String trasactionType;

	private String accountType;

	private String accountStatus;
	

}
