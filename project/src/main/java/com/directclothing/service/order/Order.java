package com.directclothing.service.order;

import com.directclothing.service.general.Date;
import com.directclothing.service.people.Customer;

public class Order {
    private String orderID;
    private Date dateFilled;
    private OrderLine[] itemsOrdered;
    private Customer customer;
    private Status orderStatus;
 
    public Order(String id, Date date, OrderLine[] items, Customer customer, Status status) {
        this.orderID = id;
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
 
    public static enum Status {
        PENDING,
        AWAITING_PAYMENT,
        AWAITING_FULFILLMENT,
        COMPLETED,
        CANCELLED
    }
}