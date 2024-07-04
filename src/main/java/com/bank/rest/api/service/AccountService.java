package com.bank.rest.api.service;

import java.util.List;

import com.bank.rest.api.entity.Account;
import com.bank.rest.api.payload.AccountDTO;

public interface AccountService {

	AccountDTO createAccount(Account account);

	Account getByI(Long Id);

	AccountDTO moneyDeposit(Long Id, Double amount);

	AccountDTO moneyWithdraw(Long Id, Double amount);

	List<Account> getAllAccountDetailsFromDataBase();

	public void deleteAccountById(Long Id);

}
