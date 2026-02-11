package com.techouts.dao;

import com.techouts.HibernateUtil;
import com.techouts.entity.Product;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ProductsDao {
    public static List<Product> getAllProducts() {
        List<Product> products;
        try(Session session = HibernateUtil.getSession()) {
            products = session.createQuery("from Product",Product.class).list();
        }
        return products;
    }
    public static Product getProduct(int id) {
        Product product=null;
        try(Session session = HibernateUtil.getSession()) {
            product=(Product) session.get(Product.class,id);
        }
        return product;
    }
}
