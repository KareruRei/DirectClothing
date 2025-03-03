package com.directclothing.service.payment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.directclothing.service.order.Order;


public class CreditCardPayment extends Payment implements PaymentInterface {
    private final SupportedCreditCards creditCard;
    private final String creditCardNumber;
    private final String cvv;
    private final String cardHolderName;
    

    public CreditCardPayment(Order order, float amt, SupportedCreditCards creditCard, String creditCardNum, String cvv, String cardHolderName) {
        super(amt);
        this.creditCard = creditCard;
        this.creditCardNumber = creditCardNum;
        this.cvv = cvv; // card verfication value THE 4 DIGITS ON THE BACK
        this.cardHolderName = cardHolderName;
        setOrderToPayFor(order);
    }

    // Getter Methods
    public SupportedCreditCards getCreditCard() {return creditCard;}
    public String getCreditCardNum() {return creditCardNumber;}
    public String getCvv() { return cvv;}
    public String getCardHolderName() { return cardHolderName; }


    @Override
    public boolean verify() {
        return isValidCVV() && isValidCreditCardNumber() && isValidCardHolderName();
    }

    @Override
    public void continueOrderProcess() {
        getOrderToPayFor().setStatus(Order.Status.AWAITING_FULFILLMENT);
    }

    @Override
    public void cancelOrderProcess() {
        getOrderToPayFor().setStatus(Order.Status.CANCELLED);
    }


    public boolean isValidCVV() {
        return cvv.length() == 3 || cvv.length() == 4;
    }

    public boolean isValidCreditCardNumber() {
        Pattern credCardNumFormat = Pattern.compile("^(\\d{4}[- ]){3}\\d{4}|\\d{16}$");   
        Matcher matcher = credCardNumFormat.matcher(creditCardNumber);
  
        return matcher.matches();        
    }

    public boolean isValidCardHolderName() {
        Pattern nameFormat = Pattern.compile("[^a-zA-Z ]");
        Matcher matcher = nameFormat.matcher(cardHolderName);
        
        return !matcher.matches();
    }



    public static enum SupportedCreditCards {
        PayMaya("PayMaya"),
        VISA("Visa Card"), 
        MasterCard("Mastercard"), 
        AMEX("American Express"), 
        UnionPay("UnionPay"), 
        JCB("Japan Credit Bureau");

        private final String displayName;

        SupportedCreditCards(String displayName) {this.displayName = displayName;}

        public String getDisplayName() {return displayName;}
    }
}