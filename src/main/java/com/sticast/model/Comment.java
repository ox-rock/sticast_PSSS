package com.sticast.model;

import com.sticast.database.DBComment;

public class Comment {
	private String name_account;	
	private String text;
	private String timestamp;
	
	public Comment() {
		super();
	}
	
	public Comment(DBComment dbComment) {
		this.name_account= dbComment.getName_account(); // dbComment.getAccount.getUsername();
		this.text = dbComment.getText();
		this.timestamp = dbComment.getTimestamp();
	}

	/****************************
	 *  Functions & Procedures  *
	 ****************************/

	public void saveComment(Integer question_id, Integer account_id, String text) {
		DBComment dbComment= new DBComment();
		dbComment.saveCommentInDB(question_id, account_id, text);				
	}

	/***********************
	 *  Get & Set methods  *
	 ***********************/
	
	public String getName_account() {
		return name_account;
	}

	public void setName_account(String name_account) {
		this.name_account = name_account;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}