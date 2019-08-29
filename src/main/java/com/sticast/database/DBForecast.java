package com.sticast.database;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name ="forecast")
public class DBForecast implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(unique=true, nullable=false, length=10)
    private Integer id;
    @Column(name="account_id", nullable=false)
    private Integer account_id;
    @Column(name="question_id", nullable=false)
    private Integer question_id;
    @Column(nullable=false, length=3)
    private String answer;
    @Column(nullable=false, length=10)
    private Integer quantity;
    @Column(nullable=false)
    private String timestamp;

    

    /** Default constructor. */
    public DBForecast() {
        super();
    }
    
    /****************************
	 *  Functions & Procedures  *
	 ****************************/
	
	//Saves (in db) a forecast made by an user
	public int saveForecastInDB(Integer user_id, Integer id2, String tipo, Integer qnt) {
		Session session = new SessionCreator().getSession();
		Transaction tx = null;

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());		
		DBForecast forecastTemp = new DBForecast();
		forecastTemp.setAccount_id(user_id);
		forecastTemp.setQuestion_id(id2);
		forecastTemp.setAnswer(tipo);
		forecastTemp.setQuantity(qnt);
		forecastTemp.setTimestamp(timestamp.toString());
		
		try{
			tx = session.beginTransaction();
			session.save(forecastTemp);
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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}


//@Entity
//@Table(name = "forecast")
//public class DBForecast {
//	@Id
//	@Column(name = "id")
//	private Integer id;
//	
//	@Column(name = "question_id")
//	private Integer question_id;
//	
//	@Column(name = "account_id")
//	private Integer account_id;
//	
//	@Column(name = "answer")
//	private String 	answer;
//	
//	@Column(name = "quantity")
//	private Integer quantity;
//	
//	@Column(name = "timestamp")
//	private String 	timestamp;
//	
//	public DBForecast() {
//		super();
//	}
//
//	/****************************
//	 *  Functions & Procedures  *
//	 ****************************/
//	
//	//Saves (in db) a forecast made by an user
//	public int saveForecastInDB(Integer user_id, Integer id2, String tipo, Integer qnt) {
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		//String query = "INSERT INTO forecast (question_id, account_id, answer, quantity, timestamp) VALUES("+id2+","+user_id+",'"+tipo+"',"+qnt+",'"+timestamp+"');";
//	
//		Session session = null;
//		Transaction tx = null;
//		
//		try {
//			Configuration con = new Configuration().configure().addAnnotatedClass(DBForecast.class);
//			SessionFactory sf = con.buildSessionFactory();
//			session = sf.openSession();
//			tx = session.beginTransaction();
//			session.createQuery("INSERT INTO forecast (question_id, account_id, answer, quantity, timestamp) VALUES("+id2+","+user_id+",'"+tipo+"',"+qnt+",'"+timestamp+"')");
//
//			tx.commit();
//		} catch (HibernateException ex) {
//			if (tx != null) tx.rollback();
//			ex.printStackTrace();
//			return 0;
//		} finally {
//			session.close();
//		}
//		
//		return 1;
//		/*
//		try {
//			return DBConnectionManager.updateQuery(query);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			return 0;
//		}
//	*/}
//
//	/***********************
//	 *  Get & Set methods  *
//	 ***********************/
//	
//	public Integer getQuestion_id() {
//		return question_id;
//	}
//
//	public void setQuestion_id(Integer question_id) {
//		this.question_id = question_id;
//	}
//
//	public String getAnswer() {
//		return answer;
//	}
//
//	public void setAnswer(String answer) {
//		this.answer = answer;
//	}
//
//	public Integer getQuantity() {
//		return quantity;
//	}
//
//	public void setQuantity(Integer quantity) {
//		this.quantity = quantity;
//	}
//
//	public String getTimestamp() {
//		return timestamp;
//	}
//
//	public void setTimestamp(String timestamp) {
//		this.timestamp = timestamp;
//	}
//}