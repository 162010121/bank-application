package com.bank.rest.api.service;

import java.util.List;

import com.bank.rest.api.entity.Account;
import com.bank.rest.api.payload.AccountDTO;
import com.bank.rest.api.payload.CustomersAccountsDetailsDTO;

public interface AccountService {

	AccountDTO createAccount(Account account);

	Account getByI(Long Id);

	AccountDTO moneyDeposit(Long Id, Double amount);

	AccountDTO moneyWithdraw(Long Id, Double amount);

	List<Account> getAllAccountDetailsFromDataBase();

	public void deleteAccountById(Long Id);
	
	public CustomersAccountsDetailsDTO getAllAccountDetailsFromData();

	

}
