package com.bank.rest.api.payload;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AccountDTO {

	private Long customerId;

	
	private String accountHolderName;

	private Double currentBalance;

	private String ifscCode;

	private String city;

	private LocalDate currentDate;

	private Long accountNumber;
	
	private String bankName;
	
	private String message;
	

	private String accountType;
	
	private String accountStatus;

}
