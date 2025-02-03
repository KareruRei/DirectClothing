package ordernpayment;
import people.Customer;
import business.DirectClothing;

public class CheckPayment extends Payment {
    private Customer drawer; // The customer who wrote the check
    private SupportedBanks drawee; // The bank instructed to pay funds
    private DirectClothing payee; // The business getting paid
    private int checkNumber;

    public CheckPayment(float amt, Status status, Customer drawer, SupportedBanks drawee, DirectClothing payee, int checkNumber) {
        super(amt, status);
        this.drawer = drawer;
        this.drawee = drawee;
        this.payee = payee;
        this.checkNumber = checkNumber;
    }

    // Setter Methods
    public void setDrawer(Customer theCustomer) {drawer = theCustomer;}
    public void setDrawee(SupportedBanks myBank) {drawee = myBank;}
    public void setPayee(DirectClothing myPayee) {payee = myPayee;}
    public void setCheckNum(int myNum) {checkNumber = myNum;}

    // Getter Methods
    public Customer getDrawer() {return drawer;}
    public SupportedBanks getDrawee() {return drawee;}
    public DirectClothing getPayee() {return payee;}
    public int getCheckNum() {return checkNumber;}


    public void verify(Order theOrder) {}
}  
