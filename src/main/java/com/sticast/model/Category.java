package com.sticast.model;

import java.util.ArrayList;
import com.sticast.database.DBCategory;

public class Category {
	private String category;
	private ArrayList<Question> questionsList;
		
	public Category() {
		super();
		setQuestionsList(new ArrayList<Question>());
	}

	/****************************
	 *  Functions & Procedures  *
	 ****************************/
	
	//Retrieves all questions 
	public void getAllQuestions(){
		questionsList = new ArrayList <Question>();
		DBCategory dbCategoria = new DBCategory();
		dbCategoria.getAllQuestions();
		
		for(int i=0; i<dbCategoria.getQuestionsList().size(); i++){
            Question domanda = new Question(dbCategoria.getQuestionsList().get(i));
	        questionsList.add(domanda);	
		}	
	}
	
	//Retrieves all questions for a given category 
		public void getAllQuestions(String category){
			questionsList = new ArrayList <Question>();
			DBCategory dbCategoria = new DBCategory();
			dbCategoria.getAllQuestions(category);
			
			for(int i=0; i<dbCategoria.getQuestionsList().size(); i++){
	            Question domanda = new Question(dbCategoria.getQuestionsList().get(i));
		        questionsList.add(domanda);	
			}	
		}
		
	//Retrieves all question's categories
	public ArrayList<Category> getAllCategories(){		
		ArrayList<Category> elenco_categorie = new ArrayList<Category>();		
		DBCategory dbCategoria = new DBCategory();
		ArrayList<DBCategory> elenco_categorieDB = dbCategoria.getAllCategories();
		
		for(int i=0; i < elenco_categorieDB.size(); i++){
			Category categoria = new Category();
			categoria.setCategory(elenco_categorieDB.get(i).getCategory());
			elenco_categorie.add(categoria);
		}
		
		return elenco_categorie;
	}
	
	/***********************
	 *  Get & Set methods  *
	 ***********************/
	
	public ArrayList<Question> getQuestionsList() {
		return questionsList;
	}

	public void setQuestionsList(ArrayList<Question> questionsList) {
		this.questionsList = questionsList;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}	
}