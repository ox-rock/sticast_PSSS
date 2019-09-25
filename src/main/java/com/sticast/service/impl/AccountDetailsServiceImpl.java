package com.sticast.service.impl;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sticast.entity.Account;
import com.sticast.entity.AccountDetails;
import com.sticast.entity.Question;
import com.sticast.repository.AccountDetailsRepository;
import com.sticast.service.AccountDetailsService;

@Service
public class AccountDetailsServiceImpl implements AccountDetailsService {

	@Autowired
	AccountDetailsRepository accountDetailsRepository;
	 
	@Override
	public AccountDetails getAccountDetails(Integer accountID, Integer questionID) {		
		Optional<AccountDetails> accountDetails = accountDetailsRepository.findByAccountIdAndQuestionId(accountID, questionID);
		if (accountDetails.isEmpty()) {
			AccountDetails emptyAccountDetails = new AccountDetails(0,0,0);
			return emptyAccountDetails;
		}
		else {	
			return accountDetails.get();
		}
	}

	@Override
	public ArrayList<AccountDetails> getFollowList(Integer accountID) {
		return accountDetailsRepository.findAllByAccountIdAndIsFollowed(accountID,1);
	}

	@Override
	public void followOrUnfollowQuestion(Integer accountID, Integer questionID, String type) {
		Optional<AccountDetails> accountDetails = accountDetailsRepository.findByAccountIdAndQuestionId(accountID, questionID);
	    if(accountDetails.isEmpty()) {
	       	Account account = new Account(accountID);
	        Question question = new Question(questionID);
	        AccountDetails accDet = new AccountDetails(account, question, 0, 0, 1);
	        accountDetailsRepository.save(accDet);
	    }
	    else {
	        if(type.equals("follow")) {
	            accountDetails.get().setIsFollowed(1);
	            accountDetailsRepository.save(accountDetails.get());
	        }
	        else {
	            accountDetails.get().setIsFollowed(0);
	            accountDetailsRepository.save(accountDetails.get());		
	        }   		
	    } 	
	}
}
