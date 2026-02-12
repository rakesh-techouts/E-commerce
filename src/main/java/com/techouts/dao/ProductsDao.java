package com.techouts.dao;

import com.techouts.HibernateUtil;
import com.techouts.entity.Product;
import org.hibernate.Session;
import java.util.List;

public class ProductsDao {
    public static List<Product> getAllProducts() {
        List<Product> products;
        try(Session session = HibernateUtil.getSession()) {
            products = session.createQuery("FROM Product",Product.class).list();
        }
        return products;
    }
    public static Product getProduct(int id) {
        Product product;
        try(Session session = HibernateUtil.getSession()) {
            product=session.get(Product.class,id);
        }
        return product;
    }
    public  static List<Product> getProductsByCategory(String category) {
        List<Product> products;
        try(Session session = HibernateUtil.getSession()) {
            products = session.createQuery("FROM Product p WHERE p.category= :category",Product.class).setParameter("category",category).list();
        }
        return products;
    }
}
