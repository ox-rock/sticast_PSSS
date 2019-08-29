package com.sticast.model;

import com.sticast.database.DBShare;

public class Share {
	private Integer question_id;	
	private Integer account_id;
	private Integer yesShareQuantity;
	private Integer noShareQuantity;
	
	public Share() {
		super();
	}
	
	public Share(Integer user_id, Integer domanda){		
		DBShare dbShare = new DBShare(user_id,domanda);	
		this.yesShareQuantity = dbShare.getYesShareQuantity();
		this.noShareQuantity = dbShare.getNoShareQuantity();
	}

	public Share(DBShare dbShare) {
		this.account_id = dbShare.getAccount_id();
		this.yesShareQuantity = dbShare.getYesShareQuantity();
		this.noShareQuantity = dbShare.getNoShareQuantity();
	}

	/***********************
	 *  Get & Set methods  *
	 ***********************/

	public Integer getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Integer question_id) {
		this.question_id = question_id;
	}

	public Integer getAccount_id() {
		return account_id;
	}

	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}

	public Integer getYesShareQuantity() {
		return yesShareQuantity;
	}

	public void setYesShareQuantity(Integer yesShareQuantity) {
		this.yesShareQuantity = yesShareQuantity;
	}

	public Integer getNoShareQuantity() {
		return noShareQuantity;
	}

	public void setNoShareQuantity(Integer noShareQuantity) {
		this.noShareQuantity = noShareQuantity;
	}

}