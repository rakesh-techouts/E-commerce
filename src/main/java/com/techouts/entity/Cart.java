package com.techouts.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="cart")
public class Cart {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int cart_id;
    @OneToOne(cascade = CascadeType.ALL)
    private Users user;
    @ManyToMany
    private List<Product> products=new ArrayList<>();

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    public int getCartId(){
        return cart_id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }
}
