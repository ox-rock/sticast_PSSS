package com.sticast.service;

import java.util.ArrayList;
import java.util.Optional;

import com.sticast.entity.Category;
import com.sticast.entity.Comment;
import com.sticast.entity.Forecast;
import com.sticast.entity.Question;

public interface QuestionService {

	public ArrayList<Question> getAllQuestions(String category);
	public Optional<Question> getQuestion(Integer questionID);
	public void updateShareValue(Forecast forecast);
	public ArrayList<Category> getAllCategories();
	public void saveComment(Comment comment);
	public Double calculateShareValue(Question question);
}
