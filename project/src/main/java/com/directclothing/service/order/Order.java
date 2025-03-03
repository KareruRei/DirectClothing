package com.directclothing.service.order;

import com.directclothing.service.general.Date;
import com.directclothing.service.people.Customer;

import java.util.Random;


public class Order {
    private String orderID;
    private Date dateFilled;
    private OrderLine[] itemsOrdered;
    private Customer customer;
    private Status orderStatus;
 
    public Order(Date date, OrderLine[] items, Customer customer, Status status) {
        this.orderID = Order.generateOrderID();
        this.dateFilled = date;
        this.itemsOrdered = items;
        this.customer = customer;
        this.orderStatus = status;
    }
 
    // Setter Methods
    public void setOrderID(String myOrderID) {
        orderID = myOrderID;
    }
    public void setDateFilled(Date date) {
        dateFilled = date;
    }
    public void setItemsOrdered(OrderLine[] myItems) {
        itemsOrdered = myItems;
    }
    public void setCustomer(Customer myCustomer) {
        customer = myCustomer;
    }
    public void setStatus(Status current) {
        orderStatus = current;
    }
 
    // Getter Methods
    public String getOrderID() {
        return orderID;
    }
    public Date getDateFilled() {
        return dateFilled;
    }
    public OrderLine[] getItemsOrdered() {
        return itemsOrdered;
    }
    public Customer getCustomer() {
        return customer;
    }
    public Status getStatus() {
        return orderStatus;
    }
    
   
    public float calcAmountOwed() {
        float totalAmount = 0.00f;
        for (OrderLine orderLine : this.itemsOrdered) {
            totalAmount += orderLine.calcOrderLinePrice();
        }
        return totalAmount;
    }
 
    public void cancelOrder() {
        this.orderStatus = Status.CANCELLED;
    }

    public static String generateOrderID() {
        Random rng = new Random();
        String id = "";

        boolean digitOrChar;
        int ascii;

        for (int i=0; i < 8; i++) {
            digitOrChar = rng.nextInt(10) > 5;

            if (digitOrChar)
                ascii = rng.nextInt(48, 58);
            else
                ascii = rng.nextInt(65, 91);

            id += (char) ascii;
        }

        return id;
    }

 
    public static enum Status {
        PENDING,
        AWAITING_PAYMENT,
        AWAITING_FULFILLMENT,
        COMPLETED,
        CANCELLED
    }
}