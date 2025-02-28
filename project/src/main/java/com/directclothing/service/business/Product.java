package com.directclothing.service.business;

import com.directclothing.service.people.Supplier;


public class Product {
    private String description;
    private String productID;
	private int quantityInStock;
    private Supplier supplier;
    private float price;
    private String imageLink;

    public Product(String desc, String id, int qty, Supplier supp, String imageLink) {
        this.description = desc;
        this.productID = id;
        this.quantityInStock = qty;
        this.supplier = supp;
        this.imageLink = imageLink;
    }
    public String toString() {return this.description;}

    // Setter Methods
    public void setDescription(String myDesc) {description = myDesc;}
    public void setProductID(String myID) {productID = myID;}
    public void setQuantityInStock(int stock) {quantityInStock = stock;}
    public void setSupplier(Supplier mySupplier) {supplier = mySupplier;}
    public void setPrice(float rawPrice) {price = rawPrice;}
    public void setImageLink(String image) {imageLink = image;}

    // Getter Methods
    public String getDescription() {return description;}
    public String getProductID() {return productID;}
    public int getQuantityInStock() {return quantityInStock;}
    public Supplier getSupplier() {return supplier;}
    public float getPrice() {return price;}
    public String getImageLink() {return imageLink;}

    public int addInventory(int newStock) {
        quantityInStock += newStock;
        return quantityInStock;
    }
    public int sellInventory(int soldStock) {
        quantityInStock -= soldStock;
        return quantityInStock;
    }
}