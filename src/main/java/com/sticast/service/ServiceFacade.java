package com.sticast.service;

import java.util.ArrayList;
import java.util.Optional;
import com.sticast.entity.Account;
import com.sticast.entity.Category;
import com.sticast.entity.Comment;
import com.sticast.entity.Forecast;
import com.sticast.entity.Question;
import com.sticast.exception.UsernameNotFoundException;

public interface ServiceFacade {
	
	public Account getAccountByUsername(String username) throws UsernameNotFoundException;
	public Account getAccountById(Integer accountID);
	public void SaveAccount(Account account);
	public Integer getShareQuantity(Integer accountID, Integer questionID, String answer);
	
	public ArrayList<Forecast> getAllForecastsByAccount(Integer accountID);
	public Double calculatePayout(Forecast forecast);
	
	public ArrayList<Question> getAllQuestions(String category);
	public Optional<Question> getQuestion(Integer questionID);
	public ArrayList<Category> getAllCategories();
	public void saveComment(Comment comment);
	public Double calculateShareValue(Question question);
	
	public Double makeForecast(Forecast forecast);
	
	public void closeQuestion(Integer questionID, String answer);
}