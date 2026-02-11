package com.techouts.dao;

import com.techouts.HibernateUtil;
import com.techouts.entity.Cart;
import com.techouts.entity.CartItem;
import com.techouts.entity.Product;
import com.techouts.entity.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Map;

public class CartDao {
    public static boolean addProduct(Users user, Product product) {
        Cart cart;
        try (Session session = HibernateUtil.getSession()) {
            cart = session.createNativeQuery(
                            "SELECT * FROM cart WHERE user_id = :userId", Cart.class)
                    .setParameter("userId", user.getId())
                    .uniqueResult();
            if (cart != null) {
                boolean alreadyExists = cart.getCartItems()
                        .stream().allMatch(cartItem -> cartItem.getProduct().getId() == product.getId());
                if (alreadyExists) {
                    return false;
                }
                Transaction transaction = session.beginTransaction();
                cart.addProduct(new CartItem(product,1));
                session.merge(cart);
                transaction.commit();
                return true;
            }
        }
        return false;
    }
    public static List<CartItem> getAllProducts(Users user) {
        try (Session session = HibernateUtil.getSession()) {
            Cart cart = session.createQuery(
                            "SELECT c FROM Cart c WHERE c.user.id = :userId",
                            Cart.class)
                    .setParameter("userId", user.getId())
                    .uniqueResult();
            return cart.getCartItems();
        }
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
}