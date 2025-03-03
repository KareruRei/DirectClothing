package com.directclothing.service.people;

import com.directclothing.service.business.Cart;
import com.directclothing.service.business.Catalog;
import com.directclothing.service.general.Address;
import com.directclothing.service.order.Order;
import com.directclothing.service.payment.Payment;


public class Customer extends Person {
    private Address shippingAddress;
    private Address billingAddress;
    private Cart myCart;
    private int paymentAttempts;


    public Customer(String name, int ID, String phone, Address shippingAddress, Address billingAddress) {
        super(name, ID, phone);
        this.paymentAttempts = 0;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
        this.myCart = new Cart(this);
    }

    // Setter Methods
    public void setShippingAddress(Address myAddress) {shippingAddress = myAddress;}
    public void setBillingAddress(Address myAddress) {billingAddress = myAddress;}
    public void setCart(Cart myCart) {this.myCart = myCart;}
    public void setPaymentAttempts(int paymentAttempts) {this.paymentAttempts = paymentAttempts;}
    
    // Getter Methods
    public Address getShippingAddress() {return shippingAddress;}
    public Address getBillingAddress() {return billingAddress;}
    public Cart getCart() {return this.myCart;}
    public int getPaymentAttempts() {return paymentAttempts;}

    public void incrementPaymentAttempts() {this.paymentAttempts++;}
    public void resetPaymentAttempts() {this.paymentAttempts = 0;}

    
    public void addToCart(Catalog whichCat, String itemID) {
        this.myCart.addItem(whichCat, itemID, 1);
    }
    public void changeCartItemQty(String itemID, int qty) {
        this.myCart.getItems().get(itemID).setQuantity(qty);
    }
    public void removeFromCart(String itemID) {
        this.myCart.removeItem(itemID);
    }
}