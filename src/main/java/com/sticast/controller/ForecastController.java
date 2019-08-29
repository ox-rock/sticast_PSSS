package com.sticast.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sticast.model.Account;
import com.sticast.model.Forecast;
import com.sticast.model.Question;

@Controller
public class ForecastController {

	  //GET Request
	  @RequestMapping(value = "/forecasts", method = RequestMethod.GET)
	  public String showForecastsList(Model model,HttpServletRequest request) {
		  
		  HttpSession session = request.getSession(false);			
			Integer accountID = (Integer)session.getAttribute("accountID");
			if(accountID == null)
				return "login";
			Account account = new Account();
			account.getAllForecasts(accountID);
			ArrayList<Forecast> array_forecast = account.getForecastList();  
			request.setAttribute("forecast", array_forecast);
			
			return "forecasts"; 
	  }
	  
	  @RequestMapping(value = "/question/{questionID}",  method = RequestMethod.POST)
	    public String makeForecast(Model model, @PathVariable Integer questionID,
		    HttpServletRequest request) {
	    	
			//Get some parameters from front-end and from current session
			HttpSession session = request.getSession(false);
			Integer accountID = (Integer)session.getAttribute("accountID");
			Double userBudget = (Double)session.getAttribute("userBudget");
			String shareType = request.getParameter("selectYesNo");
			String buyOrSell = request.getParameter("buyOrSell");	
			String s_quantity = request.getParameter("quantity");
		   	Integer quantity = Integer.parseInt(s_quantity);
		
			if(buyOrSell.equals("sell"))
				quantity = (-1)*quantity;
				
			//Calculate the payout given the number of yes share bought/selled by the user
			Question question = new Question(questionID);
			Forecast forecast = new Forecast();
			double payout = forecast.calculatePayout(question.getYesShareQuantity(), question.getNoShareQuantity(), quantity, shareType);
				
			//Saves the forecast
			forecast.saveForecast(accountID,questionID,shareType,quantity);
				
			//Calculate new quantities and new prices for yes/no type shares
			question.updateShareDetails(question.getYesShareQuantity(), question.getNoShareQuantity(), quantity, shareType);
			
			//Calculate and update new budget for this user, then passes new budget to front-end
			Account acc = new Account();
			double newBudget = userBudget - payout;
			acc.updateBudget(accountID, newBudget);
			session.setAttribute("userBudget", newBudget);
			
			return "redirect:/question/"+questionID; 	   	
	    }
	  
	  
}