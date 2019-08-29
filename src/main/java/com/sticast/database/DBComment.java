package com.sticast.database;

import java.io.Serializable;
import java.sql.Timestamp;
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
@Table(name="comment")
public class DBComment implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(unique=true, nullable=false, length=10)
    private Integer id;
    @Column(name="account_id", nullable=false)
    private Integer account_id;
    @Column(name="question_id", nullable=false)
    private Integer question_id;
    @Column(nullable=false, length=500)
    private String text;
    @Column(nullable=false)
    private String timestamp;
    
    @Transient
    private String name_account;


	/** Default constructor. */
    public DBComment() {
        super();
    }
    
    /****************************
	 *  Functions & Procedures  *
	 ****************************/
		
	public int saveCommentInDB(Integer question_id, Integer account_id , String text) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Session session = new SessionCreator().getSession();
		DBComment commentTemp = new DBComment();
		commentTemp.setAccount_id(account_id);
		commentTemp.setQuestion_id(question_id);
		commentTemp.setText(text);
		commentTemp.setTimestamp(timestamp.toString());
		Transaction tx = null;
		
		try{
			tx = session.beginTransaction();
			session.save(commentTemp);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) tx.rollback();
			ex.printStackTrace();
			return 0;
		} finally {
		session.getSessionFactory().close();
		}	return 1;
		
		
		
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

    public String getText() {
        return text;
    }

    public void setText(String aText) {
        text = aText;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String string) {
        timestamp = string;
    }

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer id) {
        account_id = id;
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer id) {
        question_id = id;
    }
    
    public String getName_account() {
		return name_account;
	}

	public void setName_account(String name_account) {
		this.name_account = name_account;
	}

}