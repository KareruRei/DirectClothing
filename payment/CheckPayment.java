<<<<<<<< HEAD:project/src/main/java/com/directclothing/service/payment/CheckPayment.java
package com.directclothing.service.payment;
import com.directclothing.service.people.Customer;
import com.directclothing.service.business.DirectClothing;
import com.directclothing.service.order.Order;
========
package payment;
import people.Customer;
import business.DirectClothing;
import order.Order;
>>>>>>>> 28df495fed64b7cfa567d74146e2c40fbb5052cb:payment/CheckPayment.java

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


    public void verify(Order theOrder) {
        /*
        @Override
        public void verify(Order theOrder) {
        System.out.println("Verifying Check Payment with Order...");

        if (drawer == null || drawee == null || payee == null || checkNumber <= 0 || theOrder == null) {
            System.out.println("Verification failed. Invalid check details or order (null or invalid values).");
            setStatus(Status.REFUSED);
            return;
        }

        if (drawer.getDirectClothing() != payee) {
            System.out.println("Verification failed. Invalid check.");
            setStatus(Status.REFUSED);
            return;
        }

        return false;

        idk if tama to :')
        */
    }
}  
