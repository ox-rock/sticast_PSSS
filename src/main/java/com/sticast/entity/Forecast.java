package com.sticast.entity;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name ="forecast")
public class Forecast{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, length=10)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    private Account account;
    
    @ManyToOne
    @JoinColumn(name="question_id", nullable=false)
    private Question question;
    
    @Column(name="answer", nullable=false, length=3)
    private String answer;
    
    @Column(name="quantity",nullable=false, length=10)
    private Integer quantity;
    
    @Column(name="payout",nullable=false, length=10)
    private Double payout;
    
    @Column(name="timestamp", nullable=false)
    @CreationTimestamp
    private Date timestamp;
 
	/*********************************
	 *  Constructors                 *
	 *********************************/
    
    public Forecast() {
        super();
    }
    
	/*********************************
	 *  Gettters & Setters           *
	 *********************************/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Forecast [Account ID = " + account.getId() + ", Question ID = " + question.getId() + ", answer=" + answer + ", quantity="
				+ quantity + "]";
	}

	public Double getPayout() {
		return payout;
	}

	public void setPayout(Double payout) {
		this.payout = payout;
	}
}