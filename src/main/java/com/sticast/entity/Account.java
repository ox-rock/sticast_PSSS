package com.sticast.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {

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

	/*******************************
	 *  Constructors               *
	 *******************************/
    
    public Account() {
    	super();
    }
    
    public Account(Integer id) {
    	super();
    	this.id = id;
    }

	/*******************************
	 *  Getters & Setters          *
	 *******************************/
    
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

}