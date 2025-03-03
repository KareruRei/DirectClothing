package com.directclothing.service.payment;


import com.directclothing.service.order.Order;


public class Payment {
    private float amount = 0.00f;
    private Status paymentStatus;
    private Method payMethod;
    private Order orderToPayFor;


    public Payment(float amount) {
        this.amount = amount;
        this.paymentStatus = Status.PENDING;

        if (this instanceof CheckPayment) {this.payMethod = Method.CHECK;}
        else if (this instanceof CreditCardPayment) {this.payMethod = Method.CREDIT_CARD;}
    }
    public Payment() {}

    // Setter Methods
    public void setAmount(float myFlt) {amount = myFlt;}
    public void setStatus(Status status) {paymentStatus = status;}
    public void setOrderToPayFor(Order newOrder) {orderToPayFor = newOrder;}
    
    // Getter Methods
    public float getAmount() {return amount;}
    public Status getStatus() {return paymentStatus;}
    public Method getMethod() {return payMethod;}
    public Order getOrderToPayFor() {return orderToPayFor;}
    

    
    public static enum Status {
        PENDING,
        VERIFIED,
        REFUSED,
        REFUNDED,
        COMPLETE
    }
    public static enum Method { CHECK, CREDIT_CARD }
}