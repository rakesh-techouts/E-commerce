package com.techouts.dao;

import com.techouts.HibernateUtil;
import com.techouts.entity.Cart;
import com.techouts.entity.Product;
import com.techouts.entity.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CartDao {
    public static boolean addProduct(Users user, Product product) {
        Cart cart;
        try (Session session = HibernateUtil.getSession()) {
            cart = session.createNativeQuery(
                            "SELECT * FROM cart WHERE user_id = :userId", Cart.class)
                    .setParameter("userId", user.getId())
                    .uniqueResult();
            System.out.println(cart.getCartId());
            if (cart != null) {
                Transaction transaction = session.beginTransaction();
                cart.addProduct(product);
                session.merge(cart);
                transaction.commit();
                return true;
            }
        }
        return false;
    }
    public static List<Product> getAllProducts(Users user) {
        try (Session session = HibernateUtil.getSession()) {
            Cart cart = session.createQuery(
                            "select c from Cart c left join fetch c.products where c.user.id = :userId",
                            Cart.class)
                    .setParameter("userId", user.getId())
                    .uniqueResult();

            return cart.getProducts();
        }
    }
}