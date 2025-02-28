package com.directclothing.service.order;

import com.directclothing.service.general.Date;
import com.directclothing.service.people.Customer;
import com.directclothing.service.people.Employee;
 
public class Order {
    private String orderID;
    private Date dateOrdered;
    private OrderLine[] itemsOrdered;
    private Customer customer;
    private Status orderStatus;
    private Employee placedBy;
 
    public Order(String id, Date date, OrderLine[] items, Customer customer, Status status, Employee placedBy) {
        this.orderID = id;
        this.dateOrdered = date;
        this.itemsOrdered = items;
        this.customer = customer;
        this.orderStatus = status;
        this.placedBy = placedBy;
    }
 
    public String toString() {
        return this.orderID;
    }
 
    // Setter Methods
    public void setOrderID(String myOrderID) {
        orderID = myOrderID;
    }
    public void setDateOrdered(Date date) {
        dateOrdered = date;
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
    public void setPlacedBy(Employee placedBy) {
        this.placedBy = placedBy;
    }
 
    // Getter Methods
    public String getOrderID() {
        return orderID;
    }
    public Date getDateOrdered() {
        return dateOrdered;
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
    public Employee getPlacedBy() {
        return placedBy;
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