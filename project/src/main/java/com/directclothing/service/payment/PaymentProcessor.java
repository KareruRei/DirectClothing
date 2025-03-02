package com.directclothing.service.payment;

import com.directclothing.service.business.DirectClothing;

public class PaymentProcessor extends Thread {
    private final DirectClothing business;
    
    public PaymentProcessor(DirectClothing business) {
        this.business = business;
    }

    @Override
    public void run() {
        while (true) {

            synchronized (business) {
                if (!business.paymentQueueIsEmpty()) {
                    business.dequeuePayment().verify();
                }
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
