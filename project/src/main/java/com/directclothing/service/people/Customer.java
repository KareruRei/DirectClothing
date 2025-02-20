package com.directclothing.service.people;

import com.directclothing.service.business.Catalog;
import com.directclothing.service.business.Product;
import com.directclothing.service.general.Address;


public class Customer extends Person {
    private Address shippingAddress;
    private Address billingAddress;

    
    public Customer(String name, int ID, String phone, Address shippingAddress, Address billingAddress) {
        super(name, ID, phone);
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
    }

    // Setter Methods
    public void setShippingAddress(Address myAddress) {shippingAddress = myAddress;}
    public void setBillingAddress(Address myAddress) {billingAddress = myAddress;}

    // Getter Methods
    public Address getShippingAddress() {return shippingAddress;}
    public Address getBillingAddress() {return billingAddress;}

    
    public void placeOnlineOrder(Catalog whatCatalog, Product[] whatItems) {}
    public void sendOrderForm(Catalog whatCatalog, Product[] whatItems) {}
    public void makePayment() {}
    
}