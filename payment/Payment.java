<<<<<<<< HEAD:project/src/main/java/com/directclothing/service/payment/Payment.java
package com.directclothing.service.payment;
========
package payment;
>>>>>>>> 28df495fed64b7cfa567d74146e2c40fbb5052cb:payment/Payment.java

public class Payment {
    private float amount = 0.00f;
    private Status paymentStatus;
    private Method payMethod;


    public Payment(float amount, Status status) {
        this.amount = amount;
        this.paymentStatus = status;

        if (this instanceof CheckPayment) {this.payMethod = Method.CHECK;}
        else if (this instanceof CreditCardPayment) {this.payMethod = Method.CREDIT_CARD;}
    }
    public String toString() {return "Php. "+Float.toString(amount);}

    // Setter Methods
    public void setAmount(float myFlt) {amount = myFlt;}
    public void setStatus(Status status) {paymentStatus = status;}
    
    // Getter Methods
    public float getAmount() {return amount;}
    public Status getStatus() {return paymentStatus;}
    public Method getMethod() {return payMethod;}
    
    
    // public abstract void verify();
    //implementation of CheckPayment and CreditCardPayment
    //CheckPayment placeholder
    //CreditCardPayment placeholder


    public static enum SupportedBanks {
        Metrobank, Union_Bank, BPI, BDO,
        PNB, AUB, RBank, Security_Bank,
        Citibank, Landbank
    }
    public static enum Status {
        PENDING,
        VERIFIED,
        REFUSED,
        REFUNDED,
        COMPLETE
    }
    public static enum Method { CHECK, CREDIT_CARD }
}