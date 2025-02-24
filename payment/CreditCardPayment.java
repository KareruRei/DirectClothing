package payment;

import java.util.Random;

import order.Order;


public class CreditCardPayment extends Payment {
    private String creditCard;
    private String creditCardNumber;
    private String cvv;
    private String cardholderName;
    private String generatedCode;
    private String inputCode;
    

    public CreditCardPayment(float amt, Status status, String creditCard, String creditCardNum) {
        super(amt, status);
        this.creditCard = creditCard;
        this.creditCardNumber = creditCardNum;
        this.cvv = cvv; // card verfication value THE 4 DIGITS ON THE BACK
        // this.cardholderName; // i probably wont even be using this 
    }

    // Setter Methods
    public void setCreditCard(String myCredCard) {creditCard = myCredCard;}
    public void setCreditCardNum(String myNum) {creditCardNumber = myNum;}
    public void setCvv(String myCvv) {cvv = myCvv;}
    public void setCardholderName(String cardName) {cardholderName = cardName;}

    // Getter Methods
    public String getCreditCard() {return creditCard;}
    public String getCreditCardNum() {return creditCardNumber;}
    public String getCvv() { return cvv;}
    public String getcardName() {return cardholderName;}

    public void verify(Order theOrder) {

    }
    public static String generateConfirmationCode() {
        Random random = new Random();
        String generatedCode = String.format("%06d", 100000 + random.nextInt(900000)); // Generates a 6-digit code
        return generatedCode;
    }
    private boolean isValidCVV(String cvv) {
        return cvv.length() == 3 || cvv.length() == 4;
    }
    
    public boolean validateConfirmationCode(String inputCode) {
        return generatedCode != null && generatedCode.equals(inputCode); // ok so how do i inputcode
    }
}