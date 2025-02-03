package ordernpayment;

public class CreditCardPayment extends Payment {
    private String creditCard;
    private String creditCardNumber;

    public CreditCardPayment(float amt, Status status, String creditCard, String creditCardNum) {
        super(amt, status);
        this.creditCard = creditCard;
        this.creditCardNumber = creditCardNum;
    }

    // Setter Methods
    public void setCreditCard(String myCredCard) {creditCard = myCredCard;}
    public void setCreditCardNum(String myNum) {creditCardNumber = myNum;}

    // Getter Methods
    public String getCreditCard() {return creditCard;}
    public String getCreditCardNum() {return creditCardNumber;}


    public void verify(Order theOrder) {}
}