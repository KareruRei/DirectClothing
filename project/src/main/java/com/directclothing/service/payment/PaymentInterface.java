package com.directclothing.service.payment;


public interface PaymentInterface {
    public boolean verify();
    public void continueOrderProcess();
    public void cancelOrderProcess();
}
