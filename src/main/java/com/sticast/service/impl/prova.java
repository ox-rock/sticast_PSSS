package com.sticast.service.impl;

import com.sticast.entity.Question;

public class prova {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question question = new Question();
		question.setYesShareQuantity(10);
		question.setNoShareQuantity(0);
		
		QuestionServiceImpl test = new QuestionServiceImpl();
		
		Double yesQ = test.calculateShareValue(question);
		
		System.out.println(yesQ);
		System.out.println(1-yesQ);
	}

}