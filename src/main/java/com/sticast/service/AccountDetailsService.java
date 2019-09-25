package com.sticast.service;

import java.util.ArrayList;
import com.sticast.entity.AccountDetails;

public interface AccountDetailsService {

	public AccountDetails getAccountDetails(Integer accountID, Integer questionID);
	public ArrayList<AccountDetails> getFollowList(Integer accountID);
	public void followOrUnfollowQuestion(Integer accountID, Integer questionID, String type);
}
