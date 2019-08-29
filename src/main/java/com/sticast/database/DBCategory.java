package com.sticast.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sticast.utils.SessionCreator;


@Entity
@Table(name="category")
public class DBCategory implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, length=10)
    private Integer id;
    @Column(nullable=false, length=50)
    private String category;
  
    @Transient
    private ArrayList<DBQuestion> questionsList;


    public DBCategory(){	
		super();
		questionsList = new ArrayList<DBQuestion>();		
	}

	/****************************
	 *  Functions & Procedures  *
	 ****************************/
	
	//Retrieves all questions (from db) for a given category
	public void getAllQuestions(String parameter){
		questionsList = new ArrayList<DBQuestion>();
		Session session = new SessionCreator().getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();			
			
			String hqlQuery1 = "from DBCategory where category = :parameter";
			String hqlQuery2 = "from DBQuestion where category_id = :idq";
			
			DBCategory cat = (DBCategory) session.createQuery(hqlQuery1).setParameter("parameter", parameter).getSingleResult();
			List<?> list = session.createQuery(hqlQuery2).setParameter("idq", cat.getId()).list();
			
			for(int i=0; i<list.size(); i++) {
				questionsList.add((DBQuestion)list.get(i));
			}	
			
			tx.commit();			
		} catch (HibernateException ex) {
			if (tx != null) tx.rollback();
			ex.printStackTrace();
		} finally {
			session.getSessionFactory().close();
		}
		
	}
	
	//Retrieves all questions (from db) 
	public void getAllQuestions(){
		
		questionsList = new ArrayList<DBQuestion>();
		Session session = new SessionCreator().getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();			
			
			String hqlQuery = "from DBQuestion";
			List<?> list = session.createQuery(hqlQuery).list();
			
			for(int i=0; i<list.size(); i++) {
				questionsList.add((DBQuestion)list.get(i));
			}	
			
			tx.commit();			
		} catch (HibernateException ex) {
			if (tx != null) tx.rollback();
			ex.printStackTrace();
		} finally {
			session.getSessionFactory().close();
		}
		
	}

	//Retrieves all categories (from db)
	public ArrayList<DBCategory> getAllCategories() {		
		ArrayList<DBCategory> elenco_categorie = new ArrayList<DBCategory>();
		
		Session session = new SessionCreator().getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();			
			
			String hqlQuery = "from DBCategory";
			List<?> list = session.createQuery(hqlQuery).list();
			
			for(int i=0; i<list.size(); i++) {
				elenco_categorie.add((DBCategory)list.get(i));
			}	
			
			tx.commit();			
		} catch (HibernateException ex) {
			if (tx != null) tx.rollback();
			ex.printStackTrace();
		} finally {
			session.getSessionFactory().close();
		}
			return elenco_categorie;
	}
    
	/***********************
	 *  Get & Set methods  *
	 ***********************/
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer aId) {
        id = aId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String aCategory) {
        category = aCategory;
    }

    public ArrayList<com.sticast.database.DBQuestion> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(ArrayList<DBQuestion> aDbQuestion) {
        questionsList = aDbQuestion;
    }

}