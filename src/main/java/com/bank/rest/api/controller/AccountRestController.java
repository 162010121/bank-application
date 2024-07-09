package com.bank.rest.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.rest.api.entity.Account;
import com.bank.rest.api.impl.AccountServiceImpl;
import com.bank.rest.api.payload.AccountDTO;

@RestController
@RequestMapping(value = "/account")
public class AccountRestController {

	@Autowired
	private AccountServiceImpl service;

	@PostMapping("/create")
	public ResponseEntity<AccountDTO> registerAcconut(@RequestBody Account account) {

		AccountDTO accountDTO = service.createAccount(account);
		return new ResponseEntity<>(accountDTO, HttpStatus.CREATED);

	}

	@GetMapping("/getBy/{Id}")
	public ResponseEntity<Account> getAccountDetailsByCustomerId(@PathVariable("Id") Long Id) {
		Account account = service.getByI(Id);
		return new ResponseEntity<>(account, HttpStatus.FOUND);
	}

	@PutMapping("/deposit/{Id}")
	public ResponseEntity<AccountDTO> moneyDepositInAccount(@PathVariable("Id") Long Id,
			@RequestBody Map<String, Double> req) {

		Double amount = req.get("amount");
		AccountDTO accountDTO = service.moneyDeposit(Id, amount);
		return new ResponseEntity<>(accountDTO, HttpStatus.CREATED);
	}

	@PutMapping("/withdraw/{Id}")
	public ResponseEntity<AccountDTO> moneyWithdrawFromAccount(@PathVariable("Id") Long Id,
			@RequestBody Map<String, Double> req) {

		Double amount = req.get("amount");
		AccountDTO accountDTO = service.moneyWithdraw(Id, amount);
		return new ResponseEntity<>(accountDTO, HttpStatus.CREATED);
	}

	@GetMapping("/getAllAccounts")
	public ResponseEntity<List<Account>> getAllAccountDetails() {

		List<Account> account = service.getAllAccountDetailsFromDataBase();

		return new ResponseEntity<>(account, HttpStatus.FOUND);

	}

	@DeleteMapping("/delete/{Id}")
	public String deleteAccount(@PathVariable("Id") Long Id) {
		service.deleteAccountById(Id);

		return "Dear Customer Your Account Was Deleted Successfully....?";

	}


}
