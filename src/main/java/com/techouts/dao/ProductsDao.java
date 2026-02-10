package com.techouts.dao;

import com.techouts.HibernateUtil;
import com.techouts.entity.Product;
import org.hibernate.Session;

import java.util.ArrayList;

public class ProductsDao {
    public static ArrayList<Product> getAllProducts() {
        ArrayList<Product> products=null;
        try(Session session = HibernateUtil.getSession()) {
            products =(ArrayList<Product>) session.createQuery("from Product",Product.class).list();
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
