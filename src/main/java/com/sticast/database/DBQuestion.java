package com.sticast.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.sticast.utils.SessionCreator;

@Entity
@Table(name="question")
public class DBQuestion implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
    @Column(unique=true, nullable=false, length=10)
    private Integer id;
    @Column(name="category_id", nullable=false)
    private Integer category_id;
    @Column(name = "text", nullable=false, length=300)   
    private String question;
    @Column(nullable=false, precision=22)
    private Double yesValue;
    @Column(nullable=false, precision=22)
    private Double noValue;
    @Column(name="yesShare", nullable=false, length=10)
    private Integer yesShareQuantity;
    @Column(name="noShare", nullable=false, length=10)
    private Integer noShareQuantity;
    @Column(nullable=false, length=3)
    private boolean isOpen;
    @Column(nullable=false, length=10)
    private Integer forecastCount;
    @Column(nullable=false, length=45)
    private String creationDate;
    @Column(nullable=false, length=45)
    private String expirationDate;

    @Transient
    private ArrayList<DBComment> dbComment;
    @Transient
    private ArrayList<DBShare> dbShare;

    /** Default constructor. */
    public DBQuestion() {
        super();
    }
    
    public DBQuestion(Integer questionID) {
		loadQuestionFromDB(questionID);
	}
    
    /****************************
	 *  Functions & Procedures  *
	 ****************************/

	public int updateQuestionInDB(Integer userID, Double new_yes_price, Double new_no_price, Integer new_yes_share, Integer new_no_share) {	
		Session session = new SessionCreator().getSession();
		Transaction tx = null;
		DBQuestion temp = null;
				
		try{ 
			tx = session.beginTransaction();
			temp = session.load(DBQuestion.class, userID);
			temp.setYesValue(new_yes_price.doubleValue());
			temp.setNoValue(new_no_price.doubleValue());
			temp.setYesShareQuantity(new_yes_share);
			temp.setNoShareQuantity(new_no_share);
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
	
	public void getAllComment(Integer question_id){
		//E' PROPRIO NECESSARIO L'INNER JOIN? PER PRENDERE TUTTI I COMMENTI BASTA
		//SELEZIONARE NELLA TABELLA COMMENT I COMMENTI RELATIVI ALLA DOMANDA question_id
		Session session = new SessionCreator().getSession();
		Transaction tx = null;
		dbComment = new ArrayList<DBComment>();

		try {
			tx = session.beginTransaction();			
			
			String hqlQuery1 = "from DBComment where question_id = :id";
			
			List<?> list = session.createQuery(hqlQuery1).setParameter("id", question_id).list();
			
			for(int i=0; i<list.size(); i++) {
				dbComment.add((DBComment) list.get(i));
			}	
			
			tx.commit();			
		} catch (HibernateException ex) {
			if (tx != null) tx.rollback();
			ex.printStackTrace();
		} 
		session.getSessionFactory().close();
		
	}
	
	public void getAllPlayersInDB(Integer question_id) {
		dbShare = new ArrayList<DBShare>();
		Session session = new SessionCreator().getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();			
			
			String hqlQuery1 = "from DBShare where question_id = :id";
			
			List<?> list = session.createQuery(hqlQuery1).setParameter("id", question_id).list();
			
			for(int i=0; i<list.size(); i++) {
				dbShare.add((DBShare) list.get(i));
			}	
			
			tx.commit();			
		} catch (HibernateException ex) {
			if (tx != null) tx.rollback();
			ex.printStackTrace();
		} 
		session.getSessionFactory().close();
		
	}	
	
	public int closeQuestionInDB(Integer question_id) {
		Session session = new SessionCreator().getSession();
		Transaction tx = null;
		DBQuestion temp = null;
				
		try{ 
			tx = session.beginTransaction();
			temp = session.load(DBQuestion.class, question_id);
			temp.setIsOpen(false);
			session.update(temp);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) tx.rollback();
			ex.printStackTrace();
			return 0;
		} finally {
		session.getSessionFactory().close();
		}	return 1;
		

		
	}
	
	public void loadQuestionFromDB(Integer questionID) {
		Session session = new SessionCreator().getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();			
			
			DBQuestion temp = session.load(DBQuestion.class, questionID);
			
			this.question=temp.getQuestion();
			this.yesShareQuantity=temp.getYesShareQuantity();
			this.noShareQuantity=temp.getNoShareQuantity();
			this.yesValue=temp.getYesValue();
			this.noValue=temp.getNoValue();
			this.isOpen=temp.getIsOpen();
			
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

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
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

	public boolean getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public Integer getForecastCount() {
		return forecastCount;
	}

	public void setForecastCount(Integer forecastCount) {
		this.forecastCount = forecastCount;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public ArrayList<DBComment> getCommentsList() {
		return dbComment;
	}

	public void setCommentsList(ArrayList<DBComment> dbComment) {
		this.dbComment = dbComment;
	}

	public ArrayList<DBShare> getPlayersList() {
		return dbShare;
	}

	public void setPlayersList(ArrayList<DBShare> dbShare) {
		this.dbShare = dbShare;
	}
	
}