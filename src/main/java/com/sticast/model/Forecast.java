package com.sticast.model;

import com.sticast.database.DBForecast;

public class Forecast {
	private Integer question_id;
	private String 	answer;
	private Integer quantity;
	private String  timestamp;
	
	public Forecast(){
		super();
	}

	public Forecast(DBForecast dbForecast) {
		this.question_id = dbForecast.getQuestion_id();
		this.answer = dbForecast.getAnswer();
		this.quantity = dbForecast.getQuantity();
		this.timestamp = dbForecast.getTimestamp();
	}

	/****************************
	 *  Functions & Procedures  *
	 ****************************/
	
	//Saves a forecast made by an user
	public void saveForecast(Integer user_id, Integer id2, String tipo, Integer qnt) {
		DBForecast dbForecast = new DBForecast();
		dbForecast.saveForecastInDB(user_id, id2, tipo, qnt);
	}
	
	public double calculatePayout(Integer yesShareQuantity, Integer noShareQuantity,  Integer quantity, String type) {
		
		final double B = 100.0; //Maximum possible amount of money the market maker can lose	
		
		if(type.equals("yes")) {
		    return B*Math.log((Math.exp((yesShareQuantity + quantity)/B) + Math.exp(noShareQuantity/B))) - 
			       B*Math.log((Math.exp((yesShareQuantity)/B) + Math.exp(noShareQuantity/B)));
		}
		else {
			return B*Math.log((Math.exp((yesShareQuantity)/B) + Math.exp((noShareQuantity + quantity)/B))) - 
				   B*Math.log((Math.exp((yesShareQuantity)/B) + Math.exp(noShareQuantity/B)));
	    }
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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
}