package com.sticast.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sticast.entity.Forecast;
import com.sticast.service.ServiceFacade;

@Controller
public class ForecastController {

	@Autowired
    ServiceFacade serviceFacade;

	@RequestMapping(value = "/forecasts", method = RequestMethod.GET)
	public String showForecastsList(Model model,HttpServletRequest request) {
		  
	    HttpSession session = request.getSession(false);			
		Integer accountID = (Integer)session.getAttribute("accountID");
		if(accountID == null)
			return "login";
			
		ArrayList<Forecast> forecastsList = serviceFacade.getAllForecastsByAccount(accountID);
		request.setAttribute("forecast", forecastsList);
			
		return "forecasts"; 
	}
	  
	@RequestMapping(value = "/question/{questionID}",  method = RequestMethod.POST)
	public String makeForecast(Model model, @PathVariable Integer questionID, HttpServletRequest request, @ModelAttribute("forecast") Forecast forecast) {
	    	
	    HttpSession session = request.getSession(false);			

		Double newBudget = serviceFacade.makeForecast(forecast);
		session.setAttribute("userBudget", newBudget);
		return "redirect:/question/"+questionID; 	   	
    }
}