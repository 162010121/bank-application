package com.bank.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.rest.api.entity.Account;



public interface AccountRespository extends JpaRepository<Account,Long> {

}
