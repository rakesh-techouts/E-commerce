package com.techouts.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cart_id;
    @OneToOne(cascade = CascadeType.ALL)
    private Users user;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    //Getters and Setters
    public Users getUser() {
        return user;
    }
    public void setUser(Users user) {
        this.user = user;
    }
    public int getCartId() {
        return cart_id;
    }
    public List<CartItem> getCartItems() {
        return this.cartItems;
    }
    public void setProducts(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
    public void addProduct(CartItem cartItem) {
        this.cartItems.add(cartItem);
    }
}
