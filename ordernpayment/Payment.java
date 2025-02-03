package ordernpayment;

public class Payment {
    private float amount = 0.00f;
    private Status paymentStatus;
    private Mode mode;


    public Payment(float amount, Status status) {
        this.amount = amount;
        this.paymentStatus = status;
        if (this instanceof CheckPayment) {this.mode = Mode.CHECK;}
        else if (this instanceof CreditCardPayment) {this.mode = Mode.CREDIT_CARD;}
    }
    public String toString() {return "P "+Float.toString(amount);}

    // Setter Methods
    public void setAmount(float myFlt) {amount = myFlt;}
    public void setStatus(Status status) {paymentStatus = status;}
    
    // Getter Methods
    public float getAmount() {return amount;}
    public Status getStatus() {return paymentStatus;}
    public Mode getMode() {return mode;}
    
    
    public void verify() {}




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
    public static enum Mode { CHECK, CREDIT_CARD }
}