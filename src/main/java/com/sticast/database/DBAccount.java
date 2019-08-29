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
@Table(name="account")
public class DBAccount implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, length=10)
    private Integer id;
    @Column(unique=true, nullable=false, length=45)
    private String username;
    @Column(nullable=false, length=45)
    private String password;
    @Column(nullable=false, length=100)
    private String name;
    @Column(unique=true, nullable=false, length=60)
    private String email;
    @Column(name= "balance", nullable=false, precision=22)
    private double budget;
    @Column(nullable=false, length=1)
    private boolean isAdmin;
    
    @Transient
    private boolean rSisEmpty;
    @Transient
    private ArrayList<DBFollow> followList;
    @Transient
    private ArrayList<DBForecast> forecastsList;

    /***********************
	 *     Constructors    *
	 ***********************/
    
    public DBAccount() {
        super();
    }
    
    public DBAccount(String userID, Integer id) {
		loadAccountFromDB(userID, id);	
	}
	
	public DBAccount(String username, String password, String name, String email) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
	}

	public DBAccount(String username) {
		this.username = username;
	}
		
	public DBAccount(Integer user_id, Double new_bilancio) {
		updateBudgetInDB(user_id,new_bilancio);
	}
	
	/****************************
	 *  Functions & Procedures  *
	 ****************************/
	
	public int saveAccountInDB() {
		Session session = new SessionCreator().getSession();
		Transaction tx = null;
		
		try{
			tx = session.beginTransaction();
			session.save(this);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) tx.rollback();
			ex.printStackTrace();
			return 0;
		} 
		finally {
			session.getSessionFactory().close();
		}
			return 1;
	}
	
	public int updateBudgetInDB(Integer user_id, Double new_bilancio) {
		Session session = new SessionCreator().getSession();
		Transaction tx = null;
		DBAccount temp = null;
				
		try{ 
			tx = session.beginTransaction();
			temp = session.load(DBAccount.class, user_id);
			temp.setBudget(new_bilancio);
			session.update(temp);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) tx.rollback();
			ex.printStackTrace();
			return 0;
		} finally {
			session.getSessionFactory().close();
		}
			return 1;	
	}
	
	public int updateAccountInDB(Integer id) {
		Session session = new SessionCreator().getSession();
		Transaction tx = null;
		DBAccount temp = null;
				
		try{ 
			tx = session.beginTransaction();
			temp = session.load(DBAccount.class, id);
			temp.setUsername(this.username);
			temp.setPassword(this.password);
			temp.setName(this.name);
			temp.setEmail(this.email);
			session.update(temp);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) tx.rollback();
			ex.printStackTrace();
			return 0;
		} finally {
			session.getSessionFactory().close();
		}
			return 1;	
	}
	
	public void loadAccountFromDB(String username, Integer userID) {
	    Session session = new SessionCreator().getSession();
	    Transaction tx = null; 
	    DBAccount temp = null;
	    
	    try {
	      tx = session.beginTransaction();
	      
	      if (userID != 0) {
	        temp = (DBAccount) session.createQuery("FROM DBAccount where id = "+userID).uniqueResult();
	      } 
	      
	      else if (username != null) {
	        temp = (DBAccount) session.createQuery("FROM DBAccount where username = :user").setParameter("user", username).uniqueResult();
	      }
	      
	      if (temp != null) {
	        this.id = temp.id;
	        this.username = temp.username;
	        this.password = temp.password;
	        this.name = temp.name;
	        this.email = temp.email;
	        this.budget = temp.budget;
	        this.isAdmin = temp.isAdmin;
	        this.setRSisEmpty(false);
	      } else setRSisEmpty(true);
	      
	      tx.commit();
	    } catch (HibernateException ex) {
	      if (tx != null) tx.rollback();
	      ex.printStackTrace();
	      
	    } 
	    finally {
           session.getSessionFactory().close();
	    }  
	  }
		
	public void getAllForecast(Integer id){
		Session session = new SessionCreator().getSession();
		Transaction tx = null;
		forecastsList = new ArrayList<DBForecast>();

		try {
			tx = session.beginTransaction();			
			
			String hql = "from DBForecast where account_id = :id ORDER BY timestamp DESC";
			List<?> resultList = session.createQuery(hql).setParameter("id", id).list();
			for(int i=0; i<resultList.size(); i++) {
				forecastsList.add((DBForecast) resultList.get(i));
			}
			
			tx.commit();			
		} catch (HibernateException ex) {
			if (tx != null) tx.rollback();
			ex.printStackTrace();
		} finally {
			session.getSessionFactory().close();
		}
	}
	
	public void getAllFollow(Integer id2) {
		Session session = new SessionCreator().getSession();
		Transaction tx = null;
		DBFollow fol = null;
		DBQuestion ques = null;
		followList = new ArrayList<DBFollow>();

		try {
			tx = session.beginTransaction();			
			
			String hqlQuery1 = "from DBFollow where account_id = :id";
			String hqlQuery2 = "from DBQuestion where id = :idq";
			
			List<?> list = session.createQuery(hqlQuery1).setParameter("id", id2).list();
			
			for(int i=0; i<list.size(); i++) {
				fol = (DBFollow) list.get(i);
				ques = (DBQuestion) session.createQuery(hqlQuery2).setParameter("idq", fol.getQuestion_id()).getSingleResult();
				fol.setQuestion_text(ques.getQuestion());
				followList.add(fol);
			}	
			
			tx.commit();			
		} catch (HibernateException ex) {
			if (tx != null) tx.rollback();
			ex.printStackTrace();
		} 
		session.getSessionFactory().close();	
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String aUsername) {
        username = aUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String aPassword) {
        password = aPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        name = aName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String aEmail) {
        email = aEmail;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double aBalance) {
        budget = aBalance;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean aIsAdmin) {
        isAdmin = aIsAdmin;
    }

    public boolean getRSisEmpty() {
        return rSisEmpty;
    }

    public void setRSisEmpty(boolean aRSisEmpty) {
        rSisEmpty = aRSisEmpty;
    }

    public ArrayList<DBFollow> getFollowList() {
        return followList;
    }

    public void setFollowList(ArrayList<DBFollow> aFollowList) {
        followList = aFollowList;
    }

    public ArrayList<DBForecast> getForecastsList() {
        return forecastsList;
    }

    public void setForecastsList(ArrayList<DBForecast> aForecastsList) {
        forecastsList = aForecastsList;
    }

}