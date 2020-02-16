package com.sticast.service;

import java.util.Optional;
import com.sticast.entity.Account;

public interface AccountService {
	
	public Optional<Account> getAccountByUsername(String username);
	public Account getAccountById(Integer accountID);
	public void SaveAccount(Account account);
	public void payWinner(Integer accountID, Integer quantity);
	public Integer getShareQuantity(Integer accountID, Integer questionID, String answer);
}
