package com.directclothing.service.payment;

public class Payment {
    private float amount = 0.00f;
    private Status paymentStatus;
    private Method payMethod;


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
    
    // Getter Methods
    public float getAmount() {return amount;}
    public Status getStatus() {return paymentStatus;}
    public Method getMethod() {return payMethod;}
    

    
    public static enum Status {
        PENDING,
        VERIFIED,
        REFUSED,
        REFUNDED,
        COMPLETE
    }
    public static enum Method { CHECK, CREDIT_CARD }
}