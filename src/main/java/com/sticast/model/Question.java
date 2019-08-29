package com.sticast.model;

import java.util.ArrayList;
import com.sticast.database.DBQuestion;

public class Question {
	private Integer id;
	private String question;
	private Double yesValue;
	private Double noValue;
	private Integer yesShareQuantity;
	private Integer noShareQuantity;
	private Boolean isOpen;
	private Integer forecastCount;
	private String expirationDate;
	private ArrayList<Comment> commentsList;
	private ArrayList<Share> playersList;
		
	public Question() {
		super();
	}

	public Question(DBQuestion dbQuestion) {
		this.question = dbQuestion.getQuestion();
		this.id = dbQuestion.getId();
		this.forecastCount = dbQuestion.getForecastCount();
		this.isOpen = dbQuestion.getIsOpen();
		this.expirationDate = dbQuestion.getExpirationDate();
	}

	public Question(Integer id2) {
		DBQuestion dbQuestion = new DBQuestion(id2);
		this.id = id2;
		this.yesShareQuantity = dbQuestion.getYesShareQuantity();
		this.noShareQuantity = dbQuestion.getNoShareQuantity();
		this.yesValue = dbQuestion.getYesValue();
		this.noValue = dbQuestion.getNoValue();
		this.question = dbQuestion.getQuestion();
		this.isOpen = dbQuestion.getIsOpen();
	}

	/****************************
	 *  Functions & Procedures  *
	 ****************************/
	
	//Retrieves all comments for a given question
	public void getAllComments(Integer question_id) {
		commentsList = new ArrayList <Comment>();
		DBQuestion dbQuestion = new DBQuestion();
		dbQuestion.getAllComment(question_id);
		
		for(int i=0; i<dbQuestion.getCommentsList().size(); i++){
            Comment comment = new Comment(dbQuestion.getCommentsList().get(i));
            commentsList.add(comment);	
		}
	}
	
	//Retries all players for a given question
	public void getAllPlayers(Integer question_id) {
		playersList = new ArrayList<Share>();
		DBQuestion dbQuestion = new DBQuestion();
		dbQuestion.getAllPlayersInDB(question_id);
			
		for(int i=0; i<dbQuestion.getPlayersList().size(); i++){
	        Share share = new Share(dbQuestion.getPlayersList().get(i));
	        playersList.add(share);	
		}		
	}	

	public void closeQuestion(Integer question_id) {
	    DBQuestion dbQuestion = new DBQuestion();
		dbQuestion.closeQuestionInDB(question_id);		
	}	
	
	public void updateShareDetails(Integer yesShareQnt, Integer noShareQnt, Integer quantity, String type) {
		if(type.equals("yes")) {
		    yesShareQuantity =  yesShareQnt + quantity;
		    yesValue = Math.exp(yesShareQuantity/100.0)/(Math.exp(yesShareQuantity/100.0) + Math.exp(noShareQuantity/100.0));
		    noValue = 1-yesValue;
		}
		else {
			noShareQuantity = noShareQnt + quantity;
			noValue = Math.exp(noShareQuantity/100.0)/(Math.exp(noShareQuantity/100.0) + Math.exp(yesShareQuantity/100.0));
			yesValue = 1-noValue;
		}
		DBQuestion question = new DBQuestion();
		question.updateQuestionInDB(id, yesValue, noValue, yesShareQuantity, noShareQuantity);
		
	}
		
	/***********************
	 *  Get & Set methods  *
	 ***********************/
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Double getYesValue() {
		return yesValue;
	}

	public void setYesValue(Double yesValue) {
		this.yesValue = yesValue;
	}

	public Double getNoValue() {
		return noValue;
	}

	public void setNoValue(Double noValue) {
		this.noValue = noValue;
	}

	public Integer getYesShareQuantity() {
		return yesShareQuantity;
	}

	public void setYesShareQuantity(Integer yesShareQuantity) {
		this.yesShareQuantity = yesShareQuantity;
	}

	public Integer getNoShareQuantity() {
		return noShareQuantity;
	}

	public void setNoShareQuantity(Integer noShareQuantity) {
		this.noShareQuantity = noShareQuantity;
	}
	
	public Integer getForecastCount() {
		return forecastCount;
	}

	public void setForecastCount(Integer forecastCount) {
		this.forecastCount = forecastCount;
	}

	public Boolean getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}
	
	public ArrayList<Comment> getCommentsList() {
		return commentsList;
	}

	public void setCommentsList(ArrayList<Comment> commentsList) {
		this.commentsList = commentsList;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public ArrayList<Share> getAllPlayers() {
		return playersList;
	}

	public void setAllPlayers(ArrayList<Share> allPlayers) {
		this.playersList = allPlayers;
	}
	
}