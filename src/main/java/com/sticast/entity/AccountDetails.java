package com.sticast.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="account_details")
public class AccountDetails {
  
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(unique=true, nullable=false, length=10)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    private Account account;
    
    @ManyToOne
    @JoinColumn(name="question_id", nullable=false)
    private Question question;
    
    @Column(nullable=false, length=10)
    private Integer yesShareQuantity;
    
    @Column(nullable=false, length=10)
    private Integer noShareQuantity;
    
    @Column(nullable=false, length=1)
    private Integer isFollowed;

    /** Default constructor. */
    public AccountDetails() {
        super();
    }

    public AccountDetails(Integer yesShareQuantity, Integer noShareQuantity, Integer isFollowed) {
        super();
        this.yesShareQuantity = yesShareQuantity;
        this.noShareQuantity = noShareQuantity;
        this.isFollowed = isFollowed;
    }
    
    public AccountDetails(Account account, Question question, Integer yesShareQuantity, Integer noShareQuantity, Integer isFollowed) {
        super();
        this.question = question;
        this.account = account;
        this.yesShareQuantity = yesShareQuantity;
        this.noShareQuantity = noShareQuantity;
        this.isFollowed = isFollowed;
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

	public Integer getIsFollowed() {
		return isFollowed;
	}

	public void setIsFollowed(Integer isFollowed) {
		this.isFollowed = isFollowed;
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
    
}