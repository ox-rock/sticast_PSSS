package com.sticast.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sticast.database.DBAccount;
import com.sticast.database.DBCategory;
import com.sticast.database.DBComment;
import com.sticast.database.DBFollow;
import com.sticast.database.DBForecast;
import com.sticast.database.DBQuestion;
import com.sticast.database.DBShare;

public class SessionCreator {
	
	private Session session;
	
	public SessionCreator() {
		super();
	}
	
	public Session getSession() {
		
		try {
			Configuration con = new Configuration().configure();
			con.addAnnotatedClass(DBAccount.class);
			con.addAnnotatedClass(DBCategory.class);
			con.addAnnotatedClass(DBComment.class);
			con.addAnnotatedClass(DBFollow.class);
			con.addAnnotatedClass(DBForecast.class);
			con.addAnnotatedClass(DBQuestion.class);
			con.addAnnotatedClass(DBShare.class);
			SessionFactory sf = con.buildSessionFactory();
			session = sf.openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
	
}
