package com.bank.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.rest.api.entity.Account;


@Repository
public interface AccountRespository extends JpaRepository<Account, Long> {
	
	
	@Query
	public Account findByCustomerId(Long Id);

}
