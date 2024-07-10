package com.bank.rest.api.payload;

import java.util.List;

import com.bank.rest.api.entity.Account;

import lombok.Data;


@Data
public class CustomersAccountsDetailsDTO {
	
	private int accountsStatusCount;
	
	private List<Account> CustomersAccountsList;
	
	private String accountStatus;
	

}
