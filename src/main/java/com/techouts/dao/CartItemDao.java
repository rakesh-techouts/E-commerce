package com.techouts.dao;

import com.techouts.HibernateUtil;
import com.techouts.entity.Cart;
import com.techouts.entity.CartItem;
import com.techouts.entity.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CartItemDao {
    public static void increaseItem(int cartItemId){
        try(Session session = HibernateUtil.getSession()){
            session.beginTransaction();
            CartItem cartItem=getCartItemById(session,cartItemId);
            cartItem.setQuantity(cartItem.getQuantity()+1);
            session.merge(cartItem);
            session.getTransaction().commit();
        }
    }
    public static void decreaseItem(int cartItemId){
        try(Session session = HibernateUtil.getSession()){
            session.beginTransaction();
            CartItem cartItem=getCartItemById(session,cartItemId);
            if(cartItem.getQuantity()>1){
                cartItem.setQuantity(cartItem.getQuantity()-1);
                session.merge(cartItem);
                session.getTransaction().commit();
            }
        }
    }
    public static CartItem getCartItemById(Session session,int cartItemId){
        return session.get(CartItem.class,cartItemId);
    }
}
