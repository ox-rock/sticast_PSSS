package com.sticast.service.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sticast.entity.Account;
import com.sticast.entity.Category;
import com.sticast.entity.Comment;
import com.sticast.entity.Forecast;
import com.sticast.entity.Question;
import com.sticast.exception.UsernameNotFoundException;
import com.sticast.service.AccountService;
import com.sticast.service.ForecastService;
import com.sticast.service.QuestionService;
import com.sticast.service.ServiceFacade;

@Service
public class ServiceFacadeImpl implements ServiceFacade {

	@Autowired
    AccountService accountService;
	@Autowired
    QuestionService questionService;
	@Autowired
    ForecastService forecastService;
	

	@Override
	public Account getAccountByUsername(String username) throws UsernameNotFoundException {
		return accountService.getAccountByUsername(username);
	}

	@Override
	public Account getAccountById(Integer accountID) {
		return accountService.getAccountById(accountID);
	}

	@Override
	public void SaveAccount(Account account) {
		accountService.SaveAccount(account);
	}

	@Override
	public ArrayList<Category> getAllCategories() {
		return questionService.getAllCategories();
	}

	@Override
	public void saveComment(Comment comment) {
		questionService.saveComment(comment);
	}

	@Override
	public ArrayList<Forecast> getAllForecastsByAccount(Integer accountID) {
		return forecastService.getAllForecastsByAccount(accountID);
	}

	@Override
	public Double calculatePayout(Forecast forecast) {
		return forecastService.calculatePayout(forecast);
	}

	@Override
	public ArrayList<Question> getAllQuestions(String category) {
		return questionService.getAllQuestions(category);
	}

	@Override
	public Optional<Question> getQuestion(Integer questionID) {
		return questionService.getQuestion(questionID);
	}



	@Override
	public Double makeForecast(Forecast forecast) {
		forecast = forecastService.saveForecast(forecast);
		questionService.updateShareValue(forecast);
		Account account = getAccountById(forecast.getAccount().getId());
		double newBudget = account.getBudget() - forecast.getPayout();
		account.setBudget(newBudget);
		SaveAccount(account);
		return newBudget;
	}
}
