package com.helper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryProvider {
	public static SessionFactory factory;
	 
	//creating method to get factory
	public static SessionFactory getFactory() {
		if(factory==null) {
			factory=new Configuration().configure().buildSessionFactory();
		}
		return factory;
	}
	//another method to close the factory
	public void closeFactory() {
		if(factory.isOpen()) {
			factory.close();
		}
	}
}
