package com.sticast.service.impl;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sticast.entity.Forecast;
import com.sticast.entity.Question;
import com.sticast.repository.CommentRepository;
import com.sticast.repository.QuestionRepository;
import com.sticast.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
	QuestionRepository questionRepository;
    
    @Autowired
	CommentRepository commentRepository;

	@Override
	public ArrayList<Question> getAllQuestions(String category) {	
		if(category.equals("all"))
			return questionRepository.findAll();
		else
		    return questionRepository.findByCategoryName(category);
	}

	@Override
	public Optional<Question> getQuestion(Integer questionID) {
		return questionRepository.findById(questionID);
	}

	@Override
	public void updateShareValue(Forecast forecast) {
		
		Question oldQuestion = forecast.getQuestion();
		
		if(forecast.getAnswer().equals("yes")) {
		    oldQuestion.setYesShareQuantity(oldQuestion.getYesShareQuantity()+forecast.getQuantity()); 
		    oldQuestion.setYesValue( Math.exp(oldQuestion.getYesShareQuantity()/100.0)/(Math.exp(oldQuestion.getYesShareQuantity()/100.0) + Math.exp(oldQuestion.getNoShareQuantity()/100.0))); 
		    oldQuestion.setNoValue(1-oldQuestion.getYesValue());
		}
		else {
			oldQuestion.setNoShareQuantity(oldQuestion.getNoShareQuantity() + forecast.getQuantity());
			oldQuestion.setNoValue(Math.exp(oldQuestion.getNoShareQuantity()/100.0)/(Math.exp(oldQuestion.getNoShareQuantity()/100.0) + Math.exp(oldQuestion.getYesShareQuantity()/100.0)));
			oldQuestion.setYesValue(1-oldQuestion.getNoValue());
		}
		
		questionRepository.save(oldQuestion);
		
	}
}