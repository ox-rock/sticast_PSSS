package com.sticast.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sticast.entity.Account;
import com.sticast.entity.Category;
import com.sticast.entity.Comment;
import com.sticast.entity.Forecast;
import com.sticast.entity.Question;
import com.sticast.entity.Winner;
import com.sticast.service.AccountService;
import com.sticast.service.AdministratorService;
import com.sticast.service.ForecastService;
import com.sticast.service.QuestionService;
import com.sticast.service.ServiceFacade;

@Service
public class ServiceFacade {

	@Autowired
    AccountService accountService;
	@Autowired
    QuestionService questionService;
	@Autowired
    ForecastService forecastService;
	@Autowired
    AdministratorService administratorService;
	
	public Optional<Account> getAccountByUsername(String username) {
		return accountService.getAccountByUsername(username);
	}

	public Account getAccountById(Integer accountID) {
		return accountService.getAccountById(accountID);
	}

	public void SaveAccount(Account account) {
		accountService.SaveAccount(account);
	}

	public ArrayList<Category> getAllCategories() {
		return questionService.getAllCategories();
	}

	public void saveComment(Comment comment) {
		questionService.saveComment(comment);
	}

	public ArrayList<Forecast> getAllForecastsByAccount(Integer accountID) {
		return forecastService.getAllForecastsByAccount(accountID);
	}

	public Double calculatePayout(Forecast forecast) {
		return forecastService.calculatePayout(forecast);
	}

	public ArrayList<Question> getAllQuestions(String category) {
		return questionService.getAllQuestions(category);
	}

	public Optional<Question> getQuestion(Integer questionID) {
		return questionService.getQuestion(questionID);
	}

	public Double makeForecast(Forecast forecast) {
		forecast = forecastService.saveForecast(forecast);
		questionService.updateShareValue(forecast);
		Account account = getAccountById(forecast.getAccount().getId());
		double newBudget = account.getBudget() - forecast.getPayout();
		account.setBudget(newBudget);
		SaveAccount(account);
		return newBudget;
	}

	public Double calculateShareValue(Question question) {
		return questionService.calculateShareValue(question);
	}

	public void closeQuestion(Integer questionID, String answer) {

		List<Winner> winnersList  = administratorService.getWinners(questionID,answer);

		for(int i=0; i < winnersList.size(); i++)
				accountService.payWinner(winnersList.get(i).getA(), winnersList.get(i).getQ());
		
		questionService.closeQuestion(questionID);
	}

	public Integer getShareQuantity(Integer accountID, Integer questionID, String answer) {
		return accountService.getShareQuantity(accountID, questionID, answer);
	}
}