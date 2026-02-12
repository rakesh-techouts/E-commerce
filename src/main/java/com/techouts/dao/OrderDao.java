package com.techouts.dao;

import com.techouts.HibernateUtil;
import com.techouts.entity.Cart;
import com.techouts.entity.CartItem;
import com.techouts.entity.Orders;
import com.techouts.entity.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;

public class OrderDao {
    public static boolean createOrder(int userId, String address, String paymentMethod, double amount, LocalDateTime date) {
        try(Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            Users user =  session.get(Users.class, userId);

            Orders order=new Orders();
            order.setAddress(address);
            order.setPaymentMethod(paymentMethod);
            order.setTotalPrice(amount);
            order.setOrderDateTime(date);
            order.setUser(user);

            Cart cart=CartDao.getCartByUser(session,user);
            addItemsToOrder(order,cart);
            session.persist(order);

            cart.getCartItems().clear();
            session.merge(user);

            tx.commit();
            return true;
        }
    }
    public static void addItemsToOrder(Orders order, Cart cart) {
        for(CartItem item:cart.getCartItems()) {
            order.addProduct(item.getProduct());
        }
    }
}
