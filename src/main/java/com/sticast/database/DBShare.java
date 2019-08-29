package com.sticast.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sticast.utils.SessionCreator;


@Entity
@Table(name="share")
public class DBShare implements Serializable {

	private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column(name="account_id", nullable=false)
    private Integer account_id;
    @Column(name="question_id", nullable=false)
    private Integer question_id;
    @Column(nullable=false, length=10)
    private Integer yesShareQuantity;
    @Column(nullable=false, length=10)
    private Integer noShareQuantity;
    

    /** Default constructor. */
    public DBShare() {
        super();
    }

    public DBShare(Integer user_id, Integer domanda) {
    	
    	Session session = new SessionCreator().getSession();
		Transaction tx = null;
		DBShare temp = null;
		
		try{
			tx = session.beginTransaction();
			String hqlQuery = "from DBShare where account_id = :aid and question_id = :qid";
			temp = (DBShare) session.createQuery(hqlQuery).setParameter("aid", user_id).setParameter("qid", domanda).uniqueResult();
			if (temp != null) {
				this.yesShareQuantity = temp.getYesShareQuantity();
				this.noShareQuantity = temp.getNoShareQuantity();
			} else {
				this.yesShareQuantity=0;
				this.noShareQuantity=0;
			}
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) tx.rollback();
			//ex.printStackTrace();
		} 
		session.getSessionFactory().close();
		
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
    
}