package com.directclothing.dto;

public class CartUpdateRequest {
    private String itemID;
    private int newQty;

    public CartUpdateRequest(String itemID, int newQty) {
        this.itemID = itemID;
        this.newQty = newQty;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }
    public void setNewQty(int newQty) {
        this.newQty = newQty;
    }

    public String getItemID() {
        return itemID;
    }
    public int getNewQty() {
        return newQty;
    }
}
