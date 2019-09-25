package com.sticast.service.impl;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sticast.entity.Forecast;
import com.sticast.entity.Question;
import com.sticast.repository.ForecastRepository;
import com.sticast.service.ForecastService;
import com.sticast.service.QuestionService;

@Service
public class ForecastServiceImpl implements ForecastService {

	@Autowired
	QuestionService questionService;

	@Autowired 
	ForecastRepository forecastRepository;
	
	@Override
	public ArrayList<Forecast> getAllForecastsByAccount(Integer accountID) {
		return forecastRepository.findAllByAccountId(accountID);
	}

	@Override
	public Double calculatePayout(Forecast forecast) { 				
		final double B = 100.0; //Maximum possible amount of money the market maker can lose	
		
		if(forecast.getAnswer().equals("yes")) {
		    return B*Math.log((Math.exp((forecast.getQuestion().getYesShareQuantity() + forecast.getQuantity())/B) + Math.exp(forecast.getQuestion().getNoShareQuantity()/B))) - 
			       B*Math.log((Math.exp((forecast.getQuestion().getYesShareQuantity()/B)) + Math.exp(forecast.getQuestion().getNoShareQuantity()/B)));
		}
		else {
			return B*Math.log((Math.exp((forecast.getQuestion().getYesShareQuantity())/B) + Math.exp((forecast.getQuestion().getNoShareQuantity() + forecast.getQuantity())/B))) - 
				   B*Math.log((Math.exp((forecast.getQuestion().getYesShareQuantity())/B) + Math.exp(forecast.getQuestion().getNoShareQuantity()/B)));
	    }
	}

	@Override
	public Forecast saveForecast(Forecast forecast) {	
		Optional<Question> question = questionService.getQuestion(forecast.getQuestion().getId());
		forecast.setQuestion(question.get());
		
		double payout = calculatePayout(forecast);
		forecast.setPayout(payout);
		forecastRepository.save(forecast);	
		
		return forecast;
	}
}
