package com.sticast.model;

import com.sticast.database.DBFollow;

public class Follow {
	private Integer account_id;
	private Integer question_id;
	private String question_text;
	
	public Follow() {
		super();
	}

	public Follow(DBFollow dbFollow) {
		this.account_id = dbFollow.getAccount_id();
		this.question_id = dbFollow.getQuestion_id();
		this.question_text = dbFollow.getQuestion_text();
    }

	public Follow(Integer user_id, Integer question_id) {
		this.account_id = user_id;
		this.question_id = question_id;
	}

	/****************************
	 *  Functions & Procedures  *
	 ****************************/

	public void saveFollow() {
		DBFollow dbFollow = new DBFollow(this.account_id,this.question_id);
		dbFollow.saveFollowInDB();
	}

	public void deleteFollow() {
		DBFollow dbFollow = new DBFollow(this.account_id,this.question_id);
		dbFollow.deleteFollowInDB();
	}

	public boolean checkFollow() {
		DBFollow dbFollow = new DBFollow(this.account_id, this.question_id);
		Integer count = dbFollow.checkFollowInDB();
		
		if(count == 0)
			return false;
		else return true;	
	}

	/***********************
	 *  Get & Set methods  *
	 ***********************/
	
	public Integer getAccount_id() {
		return account_id;
	}

	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}

	public Integer getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Integer question_id) {
		this.question_id = question_id;
	}

	public String getQuestion_text() {
		return question_text;
	}

	public void setQuestion_text(String question_text) {
		this.question_text = question_text;
	}
	
}