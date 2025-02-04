package com.bank.rest.api.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.rest.api.entity.Account;
import com.bank.rest.api.payload.AccountDTO;
import com.bank.rest.api.payload.CustomersAccountsDetailsDTO;
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

		account.setCurrentDate(new Date());
		accountDTO.setCurrentDate(new Date());
		accountDTO.setTrasactionType("Account Create");

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

		if (account.getAccountStatus().contains("De-Active")) {
			throw new RuntimeException("You Can't Deposite this Account...Becuse" + "this account is De-Activate");

		}

		accountDTO.setCustomerId(saveAccount.getCustomerId());
		accountDTO.setAccountHolderName(saveAccount.getAccountHolderName());
		accountDTO.setAccountNumber(saveAccount.getAccountNumber());
		accountDTO.setCity(saveAccount.getCity());
		accountDTO.setAccountStatus(saveAccount.getAccountStatus());
		accountDTO.setAccountType(saveAccount.getAccountType());

		accountDTO.setCurrentDate(new Date());

		accountDTO.setIfscCode(saveAccount.getIfscCode());
		accountDTO.setCurrentBalance(saveAccount.getAmount());
		accountDTO.setBankName(saveAccount.getBankName());
		accountDTO.setTrasactionType("Deposite");

		accountDTO.setMessage(
				"Dear..! Customer An Amount Of INR" + " " + amount + "/-" + "   " + "has been CREDITED to your account"
						+ " " + saveAccount.getAccountNumber() + " " + "on." + " " + saveAccount.getCurrentDate() + " "
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

		if (account.getAccountStatus().contains("De-Active")) {
			throw new RuntimeException("You Can't Withdraw from this Account...Becuse this account is De-Activate");

		}

		if (amount > 50000.00) {
			throw new RuntimeException("Limit Exceed...!" + "You Can't Withdraw/Transfer ...!");

		}
		if (account.getAmount() < amount) {

			throw new RuntimeException("Insuffient Balance...!");

		}

		accountDTO.setCustomerId(saveAccount.getCustomerId());
		accountDTO.setAccountHolderName(saveAccount.getAccountHolderName());
		accountDTO.setAccountNumber(saveAccount.getAccountNumber());
		accountDTO.setCity(saveAccount.getCity());

		accountDTO.setAccountStatus(saveAccount.getAccountStatus());
		accountDTO.setAccountType(saveAccount.getAccountType());

		accountDTO.setCurrentDate(new Date());

		accountDTO.setIfscCode(saveAccount.getIfscCode());
		accountDTO.setCurrentBalance(saveAccount.getAmount());
		accountDTO.setBankName(saveAccount.getBankName());
		accountDTO.setTrasactionType("Withdraw");

		accountDTO.setMessage("Dear..! Customer An Amount Of INR" + " " + amount + " " + "DEBITED to your account" + " "
				+ saveAccount.getAccountNumber() + " " + "on." + " " + saveAccount.getCurrentDate() + " "
				+ "Total Available Balance" + " " + total + "-" + saveAccount.getBankName());

		return accountDTO;

	}

	@Override
	public List<Account> getAllAccountDetailsFromDataBase() {

		List<Account> account = repository.findAll();

		List<Account> activeAccounts = account.stream().filter(s -> s.getAccountStatus().equalsIgnoreCase("Active"))
				.collect(Collectors.toList());

		return activeAccounts;

	}

	@Override
	public void deleteAccountById(Long Id) {

		repository.deleteById(Id);

	}

	@Override
	public CustomersAccountsDetailsDTO getAllAccountDetailsFromData() {

		CustomersAccountsDetailsDTO customerDetails = new CustomersAccountsDetailsDTO();

		String status = "De-Active";

		List<Account> account = repository.findAll();

		List<Account> activeAccounts = account.stream().filter(s -> s.getAccountStatus().equalsIgnoreCase(status))
				.collect(Collectors.toList());

		customerDetails.setAccountsStatusCount(activeAccounts.size());

		customerDetails.setCustomersAccountsList(activeAccounts);

		customerDetails.setAccountStatus(status);

		return customerDetails;
	}

}
