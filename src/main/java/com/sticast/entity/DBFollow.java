package com.sticast.database;

import java.io.Serializable;

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
@Table(name="follow")
public class DBFollow implements Serializable {

	private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="Id")
    private Integer Id;
    @Column(name="account_id", nullable=false)
    private Integer account_id;
    @Column(name="question_id", nullable=false)
    private Integer question_id;
    
    @Transient
    private String question_text;

    /** Default constructor. */
    public DBFollow() {
        super();
    }
    
    public DBFollow(Integer account_id2, Integer question_id) {
		this.account_id = account_id2;
		this.question_id = question_id;
	}
    
	/****************************
	 *  Functions & Procedures  *
	 ****************************/	
	
	public int saveFollowInDB() {
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
		} finally {
		session.getSessionFactory().close();
		}
			return 1;	
		
		
		
	}

	public int deleteFollowInDB() {
		
		Session session = new SessionCreator().getSession();
		Transaction tx = null;
		
		try{
			tx = session.beginTransaction();
			String hql = "from DBFollow where account_id = :id1 and question_id = :id2";
			DBFollow followTemp = (DBFollow) session.createQuery(hql).setParameter("id1", this.getAccount_id()).setParameter("id2", this.getQuestion_id()).getSingleResult();
			session.delete(followTemp);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) tx.rollback();
			ex.printStackTrace();
			return 0;
		} finally {
		session.getSessionFactory().close();
		}	return 1;	
		
		
		
	}

	public Integer checkFollowInDB() {
		Integer count = 0;
		Session session = new SessionCreator().getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();			
			
			String hql = "select count(*) from DBFollow where account_id = :id1 and question_id = :id2";
			Long prova = (Long) session.createQuery(hql).setParameter("id1", this.account_id).setParameter("id2", this.question_id).getSingleResult();
			count = prova.intValue();
			tx.commit();			
		} catch (HibernateException ex) {
			if (tx != null) tx.rollback();
			ex.printStackTrace();
			return 0;
		} finally {
		session.getSessionFactory().close();
		}	return count;
		
		
		
	}
	
	
	/***********************
	 *  Get & Set methods  *
	 ***********************/

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getAccount_id() {
		return account_id;
	}

	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}

	public Integer getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Integer question_id) {
		this.question_id = question_id;
	}

	public String getQuestion_text() {
		return question_text;
	}

	public void setQuestion_text(String question_text) {
		this.question_text = question_text;
	}

}
