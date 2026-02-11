package com.techouts.dao;

import com.techouts.HibernateUtil;
import com.techouts.entity.Cart;
import com.techouts.entity.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDao {
    public static boolean addUser(Users user) {
        Transaction transaction=null;
        try(Session session=HibernateUtil.getSession()) {
            transaction=session.beginTransaction();
            Cart cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
            session.persist(user);
            transaction.commit();
            return true;
        }catch(Exception e) {
            transaction.rollback();
            return false;
        }
    }
    public static boolean isEmailExists(String email) {
        Session session = HibernateUtil.getSession();
        Long count = session.createQuery("SELECT count(u.id) from Users u where u.email = :email", Long.class)
                .setParameter("email", email)
                .uniqueResult();
        return count != null && count.longValue() > 0;
    }
    public static Users getUserByEmailAndPassword(String email, String password) {
        String hql = "FROM Users u WHERE u.email = :email AND u.password = :password";
        Users user = null;
        try(Session session = HibernateUtil.getSession()) {
            user= session.createQuery(hql, Users.class)
                    .setParameter("email", email)
                    .setParameter("password", password).uniqueResult();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return user;
    }
}
