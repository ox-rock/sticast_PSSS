package com.sticast.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="question")
public class Question {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, length=10)
    private Integer id;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="category_id", nullable=false)
    private Category category;
    
    @Column(name = "text", nullable=false, length=300)   
    private String text;
    
    @Column(nullable=false, precision=22)
    private Double yesValue;
    
    @Column(nullable=false, precision=22)
    private Double noValue;
    
    @Column(name="yesShare", nullable=false, length=10)
    private Integer yesShareQuantity;
    
    @Column(name="noShare", nullable=false, length=10)
    private Integer noShareQuantity;
    
    @Column(nullable=false, length=3)
    private Integer isOpen;
    
    @Column(nullable=false, length=45)
    private String creationDate;
    
    @Column(nullable=false, length=45)
    private String expirationDate;
    
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private List<Comment> commenstList;
    

	/*******************************
	 *  Constructors               *
	 *******************************/

    public Question() {
        super();
    }
    
    public Question(Integer id) {
        super();
        this.id = id;
    }
	
	/*******************************
	 *  Getters & Setters          *
	 *******************************/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Comment> getCommenstList() {
		return commenstList;
	}

	public void setCommenstList(ArrayList<Comment> commenstList) {
		this.commenstList = commenstList;
	}
}