package com.sticast.model;

import java.util.ArrayList;

import com.sticast.database.DBAccount;

public class Account {
	private Integer id;
	private String 	username;
	private String 	password;
	private String 	name;
	private String 	email;
	private Double 	budget;
	private Boolean isAdmin;
	private ArrayList<Forecast> forecastList;
	private ArrayList<Follow> 	followList;

	public Account() {
		super();
	}
	
	public Account(String userID, Integer id){	
		DBAccount dbAccount = new DBAccount(userID, id);
		this.id = dbAccount.getId();
		this.username = dbAccount.getUsername();
		this.password = dbAccount.getPassword();
		this.name = dbAccount.getName();
		this.email = dbAccount.getEmail();
		this.budget = dbAccount.getBudget();
	}
	
	public Account(String username) {
		this.username = username;		
	}

	public Account(DBAccount account) {
		this.id=account.getId();
	}
	
	public Account(String username, String password) {
		this.username = username;
		this.password = password;		
	}
	
	public Account(String username, String password,String name, String email){	
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;		
	}

	/****************************
	 *  Functions & Procedures  *
	 ****************************/
	
	//Saves object in db
	public int saveAccount() {
		DBAccount dbAccount = new DBAccount(this.username, this.password, this.name, this.email);
		return dbAccount.saveAccountInDB();
	}
	
	//Updates a user's budget
	public void updateBudget(Integer userID, Double newBudget) {
		DBAccount dbAccount = new DBAccount();
		dbAccount.updateBudgetInDB(userID, newBudget);
	}
	
	//Updates a user's account (username, password, name and email)
	public int updateAccount(Integer id) {
		DBAccount dbAccount = new DBAccount(this.username, this.password, this.name, this.email);
		return dbAccount.updateAccountInDB(id);
	}
	
	public String checkAccount() {	
		DBAccount dbAccount = new DBAccount(this.username, 0);
		String result = "success";
		
		if(dbAccount.getRSisEmpty()) {
		    result = "wrongUsername";
		    return result;
		}
		else if(!dbAccount.getPassword().equals(this.password)) {
			result = "wrongPassword";
			return result;
		}
		
		this.id = dbAccount.getId();
		this.budget = dbAccount.getBudget();
		this.isAdmin = dbAccount.getIsAdmin();
		this.email = dbAccount.getEmail();
		this.name = dbAccount.getName();
		this.username = dbAccount.getUsername();
		this.password = dbAccount.getPassword();
		
		return result;
	}

	//Retrieves all forecasts for a given user
	public void getAllForecasts(Integer user_id) {
		forecastList = new ArrayList <Forecast>();
		DBAccount dbAccount = new DBAccount();
		dbAccount.getAllForecast(user_id);
		
		for(int i=0; i<dbAccount.getForecastsList().size(); i++) {
            Forecast forecast = new Forecast(dbAccount.getForecastsList().get(i));
	        forecastList.add(forecast);	
		}		
	}
	
	//Retrieves all followed questions for a given user
	public void getAllFollow(Integer id) {
		followList = new ArrayList <Follow>();
		DBAccount dbAccount = new DBAccount();
		dbAccount.getAllFollow(id);
		
		for(int i=0; i<dbAccount.getFollowList().size(); i++){
            Follow follow = new Follow(dbAccount.getFollowList().get(i));
	        followList.add(follow);	
		}	
	}
	
	/***********************
	 *  Get & Set methods  *
	 ***********************/
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double balance) {
		this.budget = balance;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public ArrayList<Forecast> getForecastList() {
		return forecastList;
	}

	public void setForecastList(ArrayList<Forecast> forecastList) {
		this.forecastList = forecastList;
	}
	
	public ArrayList<Follow> getFollowList() {
		return followList;
	}

	public void setFollowList(ArrayList<Follow> followList) {
		this.followList = followList;
	}

}