package com.sticast.service;

import com.sticast.entity.Account;
import com.sticast.exception.UsernameNotFoundException;

public interface AccountService {
	
	public Account getAccountByUsername(String username) throws UsernameNotFoundException;
	public Account getAccountById(Integer accountID);
	public void SaveAccount(Account account);
}
