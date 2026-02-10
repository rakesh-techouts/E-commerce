package com.techouts;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory factory= new Configuration().configure().buildSessionFactory();
    public static Session getSession(){
        return factory.openSession();
    }
}
