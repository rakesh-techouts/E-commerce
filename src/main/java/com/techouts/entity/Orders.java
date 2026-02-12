package com.techouts.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Orders {

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Users user;
    @ManyToMany
    private List<Product> products = new ArrayList<>();
    private LocalDateTime orderDateTime;
    private String address;
    private String paymentMethod;

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getAddress() {
        return address;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private double totalPrice;

    public List<Product> getProducts() {
        return products;
    }

    public int getId() {
        return id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }
}
