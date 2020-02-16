package com.sticast.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sticast.entity.Account;
import com.sticast.repository.AccountRepository;
import com.sticast.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepository accountRepository;

	@Override
	@Transactional
	public Optional<Account> getAccountByUsername(String username) {	
		return accountRepository.findByUsername(username);		
	}
	
	@Override
	public void SaveAccount(Account account) {
		accountRepository.save(account);
	}
	
	@Override
	public Account getAccountById(Integer accountID) {
		return accountRepository.findOneById(accountID);
	}

	@Override
	public void payWinner(Integer accountID, Integer quantity) {
		accountRepository.payWinner(accountID, quantity);	
	}

	@Override
	public Integer getShareQuantity(Integer accountID, Integer questionID, String answer) {
		return accountRepository.getShareQuantity(accountID, questionID, answer);
	}	
	
}