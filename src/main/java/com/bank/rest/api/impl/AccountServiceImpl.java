package com.bank.rest.api.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.rest.api.entity.Account;
import com.bank.rest.api.payload.AccountDTO;
import com.bank.rest.api.repository.AccountRespository;
import com.bank.rest.api.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRespository repository;

	@Override
	public AccountDTO createAccount(Account account) {

		AccountDTO accountDTO = new AccountDTO();

		accountDTO.setCustomerId(account.getCustomerId());
		accountDTO.setAccountHolderName(account.getAccountHolderName());
		accountDTO.setAccountNumber(account.getAccountNumber());
		accountDTO.setCity(account.getCity());
		accountDTO.setIfscCode(account.getIfscCode());
		accountDTO.setCurrentBalance(account.getAmount());
		accountDTO.setAccountStatus(account.getAccountStatus());
		accountDTO.setAccountType(account.getAccountType());


		LocalDate localDate = LocalDate.now();
		account.setCurrentDate(localDate);
		accountDTO.getCurrentDate();
		accountDTO.setBankName(account.getBankName());
		accountDTO.setMessage("Account Created Successfully" + " " + "" + "In  " + account.getBankName());
		repository.save(account);
		return accountDTO;
	}

	@Override
	public Account getByI(Long Id) {
		// TODO Auto-generated method stub
		return repository.findById(Id).get();
	}

	@Override
	public AccountDTO moneyDeposit(Long Id, Double amount) {

		AccountDTO accountDTO = new AccountDTO();

		Account account = repository.findById(Id).orElseThrow(() -> new RuntimeException("Account does not Exist"));
		double total = account.getAmount() + amount;
		account.setAmount(total);
		Account saveAccount = repository.save(account);

		accountDTO.setCustomerId(saveAccount.getCustomerId());
		accountDTO.setAccountHolderName(saveAccount.getAccountHolderName());
		accountDTO.setAccountNumber(saveAccount.getAccountNumber());
		accountDTO.setCity(saveAccount.getCity());
		accountDTO.setAccountStatus(saveAccount.getAccountStatus());
		accountDTO.setAccountType(saveAccount.getAccountType());

		LocalDate localDate = LocalDate.now();
		saveAccount.setCurrentDate(localDate);

		accountDTO.setIfscCode(saveAccount.getIfscCode());
		accountDTO.setCurrentBalance(saveAccount.getAmount());
		accountDTO.setBankName(saveAccount.getBankName());

		accountDTO.setMessage(
				"Dear..! Customer An Amount Of INR" + " " + amount + "/-"+"   "+ "has been CREDITED to your account" + " "
						+ saveAccount.getAccountNumber() +  " " + "on." + saveAccount.getCurrentDate()
						+ "Total Available Balance" + " " + total + "-" + saveAccount.getBankName());

		return accountDTO;
	}

	@Override
	public AccountDTO moneyWithdraw(Long Id, Double amount) {

		AccountDTO accountDTO = new AccountDTO();

		Account account = repository.findById(Id).orElseThrow(() -> new RuntimeException("Account does not Exist"));
		double total = account.getAmount() - amount;
		account.setAmount(total);
		Account saveAccount = repository.save(account);

		if (account.getAmount() < amount) {

			throw new RuntimeException("Insuffient Balance..!");

		}

		accountDTO.setCustomerId(saveAccount.getCustomerId());
		accountDTO.setAccountHolderName(saveAccount.getAccountHolderName());
		accountDTO.setAccountNumber(saveAccount.getAccountNumber());
		accountDTO.setCity(saveAccount.getCity());
		
		accountDTO.setAccountStatus(saveAccount.getAccountStatus());
		accountDTO.setAccountType(saveAccount.getAccountType());

		LocalDate localDate = LocalDate.now();
		saveAccount.setCurrentDate(localDate);

		accountDTO.setIfscCode(saveAccount.getIfscCode());
		accountDTO.setCurrentBalance(saveAccount.getAmount());
		accountDTO.setBankName(saveAccount.getBankName());

		accountDTO.setMessage(
				"Dear..! Customer An Amount Of INR" + " " + amount + " " + "has been DEBITED to your account" + " "
						+ saveAccount.getAccountNumber() + " " + "on." + saveAccount.getCurrentDate()
						+ "Total Available Balance" + " " + total + "-" + saveAccount.getBankName());

		return accountDTO;

	}

	@Override
	public List<Account> getAllAccountDetailsFromDataBase() {
		return repository.findAll();
	}

}
