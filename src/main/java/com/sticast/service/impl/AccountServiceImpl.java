package com.sticast.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sticast.entity.Account;
import com.sticast.exception.UsernameNotFoundException;
import com.sticast.repository.AccountRepository;
import com.sticast.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepository accountRepository;

	@Override
	@Transactional
	public Account getAccountByUsername(String username) throws UsernameNotFoundException {	
		return accountRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));		
	}
	
	@Override
	public void SaveAccount(Account account) {
		accountRepository.save(account);
	}
	
	@Override
	public Account getAccountById(Integer accountID) {
		return accountRepository.findOneById(accountID);
	}	
}