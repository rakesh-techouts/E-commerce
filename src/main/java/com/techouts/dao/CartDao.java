package com.techouts.dao;

import com.techouts.HibernateUtil;
import com.techouts.entity.Cart;
import com.techouts.entity.CartItem;
import com.techouts.entity.Product;
import com.techouts.entity.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class CartDao {
    public static boolean addProduct(Users user, Product product) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            Cart cart=getCartByUser(session , user);
            if (cart != null && product != null) {
                for(CartItem items : cart.getCartItems()) {
                    if(items.getProduct().getId() == product.getId()){
                        items.setQuantity(items.getQuantity()+1);
                        session.merge(cart);
                        transaction.commit();
                        return true;
                    }
                }
                CartItem cartItem = new CartItem(product,1);
                cart.getCartItems().add(cartItem);
                session.merge(cart);
                transaction.commit();
                return true;
            }
        }
        return false;
    }
    public static List<CartItem> getAllCartItems(Users user) {
        try (Session session = HibernateUtil.getSession()) {
            return getCartByUser(session , user).getCartItems();
        }
    }
    public static Cart getCart(Users user){
        try (Session session = HibernateUtil.getSession()) {
            return getCartByUser(session , user);
        }
    }
    public static Cart  getCartByUser(Session session,Users user) {
        return session.createNativeQuery(
                        "SELECT * FROM cart WHERE user_id = :userId", Cart.class)
                .setParameter("userId", user.getId())
                .uniqueResult();
    }
    public static boolean removeProductFromCart(Users user, int cartItemId) {
        Cart cart;
        try (Session session = HibernateUtil.getSession()) {
            cart = session.createNativeQuery(
                            "SELECT * FROM cart WHERE user_id = :userId", Cart.class)
                    .setParameter("userId", user.getId())
                    .uniqueResult();
            if (cart != null) {
                if(cart.getCartItems().remove(session.get(CartItem.class, cartItemId))) {
                    Transaction transaction = session.beginTransaction();
                    session.merge(cart);
                    transaction.commit();
                    return true;
                }
            }
            return false;
        }
    }
    public static boolean deleteAllCartItems(Users user) {
        try (Session session = HibernateUtil.getSession()) {
            Cart cart=getCartByUser(session , user);
            if(cart != null) {
                Transaction transaction = session.beginTransaction();
                cart.getCartItems().clear();
                session.merge(user);
                //session.merge(cart);
                transaction.commit();
                return true;
            }
        }
        return false;
    }
}