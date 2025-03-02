package com.directclothing.service.order;
import com.directclothing.service.business.Item;

public class OrderLine {
    private int quantity;
    private Item theItem;


    public OrderLine(int quantity, Item theItem) {
        this.quantity = quantity;
        this.theItem = theItem;
    }

    // Setter Methods
    public void setQuantity(int myQuantity) {quantity = myQuantity; }
    public void setCatalogItem(Item myItem) {theItem = myItem;}

    // Getter Methods
    public int getQuantity() {return quantity;}
    public Item getCatalogItem() {return theItem;}
        
    public float calcRawPrice() {
        String id = theItem.getProduct().getProductID();
        return theItem.getFromCatalog().getItemPrice(id) * quantity;
    }
    public float calcOrderLinePrice() {
        String id = theItem.getProduct().getProductID();
        return theItem.getFromCatalog().getDiscountedPrice(id) * quantity;
    }
    public void fillOrder() {}
}