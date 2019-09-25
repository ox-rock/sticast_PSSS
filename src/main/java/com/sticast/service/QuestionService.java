package com.sticast.service;

import java.util.ArrayList;
import java.util.Optional;
import com.sticast.entity.Forecast;
import com.sticast.entity.Question;

public interface QuestionService {

	public ArrayList<Question> getAllQuestions(String category);
	public Optional<Question> getQuestion(Integer questionID);
	public void updateShareValue(Forecast forecast);
}
