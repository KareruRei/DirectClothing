package com.directclothing.controller;

public class CartUpdateDTO {
    private String itemID;
    private int newQty;
    private float rawPrice;
    private float discountedPrice;
    private float totalPrice;
    private int cartSize;

    public CartUpdateDTO(String itemID, int newQty, float rawPrice, float discountedPrice, float totalPrice, int cartSize) {
        this.itemID = itemID;
        this.newQty = newQty;
        this.rawPrice = rawPrice;
        this.discountedPrice = discountedPrice;
        this.totalPrice = totalPrice;
        this.cartSize = cartSize;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }
    public void setNewQty(int newQty) {
        this.newQty = newQty;
    }
    public void setRawPrice(float rawPrice) {
        this.rawPrice = rawPrice;
    }
    public void setDiscountedPrice(float discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
    public void setCartSize(int cartSize) {
        this.cartSize = cartSize;
    }

    

    public String getItemID() {
        return itemID;
    }
    public int getNewQty() {
        return newQty;
    }
    public float getRawPrice() {
        return rawPrice;
    }
    public float getDiscountedPrice() {
        return discountedPrice;
    }
    public float getTotalPrice() {
        return totalPrice;
    }
    public int getCartSize() {
        return cartSize;
    }
}