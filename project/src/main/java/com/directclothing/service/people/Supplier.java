package com.directclothing.service.people;

import java.util.HashMap;

import com.directclothing.service.business.Product;
import com.directclothing.service.general.Address;

public class Supplier extends Person {
    private Address address;
    private HashMap<String, Product> products = new HashMap<>();  // Key: product id


    public Supplier(String name, Address address, int ID, String phone, Product[] products) {
        super(name, ID, phone);
        this.address = address;

        for (Product product : products) {
            this.products.put(product.getProductID(), product);
            product.setSupplier(this);
        }
    }

    // Setter Methods
    public void setAddress(Address myAddress) {address = myAddress;}
    public void setProducts(HashMap<String, Product> myProducts) {products = myProducts;}
    public void setProducts(Product[] myProducts) {
        products.clear();
        
        for (Product product : myProducts) {
            product.setSupplier(this);
            products.put(product.getProductID(), product);
        }
    }
    
    // Getter Methods
    public Address getAddress() {return address;}
    public HashMap<String, Product> getProducts() {return products;}

    public void addProduct(Product newProduct) {products.put(newProduct.getProductID(), newProduct);}
    public void removeProduct(String productID) {products.remove(productID);}
}