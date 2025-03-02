package com.directclothing.service.payment;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.directclothing.service.general.Address;


public class CreditCardPayment extends Payment implements PaymentInterface {
    private SupportedCreditCards creditCard;
    private String creditCardNumber;
    private String cvv;
    private String generatedCode;
    private String inputCode;
    

    public CreditCardPayment(float amt, Status status, SupportedCreditCards creditCard, String creditCardNum, String cvv, Address billingAddress) {
        super(amt, status);
        this.creditCard = creditCard;
        this.creditCardNumber = creditCardNum;
        this.cvv = cvv; // card verfication value THE 4 DIGITS ON THE BACK

        this.generatedCode = generateConfirmationCode();
    }
    public CreditCardPayment() {}

    // Setter Methods
    public void setCreditCard(SupportedCreditCards myCredCard) {creditCard = myCredCard;}
    public void setCreditCardNum(String myNum) {creditCardNumber = myNum;}
    public void setCvv(String myCvv) {cvv = myCvv;}

    // Getter Methods
    public SupportedCreditCards getCreditCard() {return creditCard;}
    public String getCreditCardNum() {return creditCardNumber;}
    public String getCvv() { return cvv;}


    @Override
    public boolean verify() {
        return isValidCVV() && isValidCreditCardNumber() && validateConfirmationCode();
    }

    public boolean isValidCVV() {
        return cvv.length() == 3 || cvv.length() == 4;
    }

    public boolean isValidCreditCardNumber() {
        Pattern credCardNumFormat = Pattern.compile("^(\\d{4}[- ]){3}\\d{4}|\\d{16}$");   
        Matcher matcher = credCardNumFormat.matcher(creditCardNumber);

        return matcher.matches();        
    }
    
    public boolean validateConfirmationCode() {
        return generatedCode.equals(inputCode); // ok so how do i inputcode
    }


    public static String generateConfirmationCode() {
        Random random = new Random();
        String generatedCode = String.format("%06d", 100000 + random.nextInt(900000)); // Generates a 6-digit code

        return generatedCode;
    }
}