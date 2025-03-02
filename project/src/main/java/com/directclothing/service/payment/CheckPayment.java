package com.directclothing.service.payment;

import com.directclothing.service.business.DirectClothing;
import com.directclothing.service.people.Customer;


public class CheckPayment extends Payment implements PaymentInterface {
    private final Customer drawer; // The customer who wrote the check
    private final SupportedBanks drawee; // The bank instructed to pay funds
    private final DirectClothing payee; // The business getting paid
    private final String checkNumber;
    private final String routingNumber;
    private final String accountNumber;

    private static int checkBatchNum = 1;

    public CheckPayment(float amt, Customer drawer, SupportedBanks drawee, DirectClothing payee, String accountNumber) {
        super(amt);
        this.drawer = drawer;
        this.drawee = drawee;
        this.payee = payee;
        this.accountNumber = accountNumber;
        this.checkNumber = generateCheckNum();
        this.routingNumber = Integer.toString(drawee.getRoutingNumber());
    }

    // Getter Methods
    public Customer getDrawer() {return drawer;}
    public SupportedBanks getDrawee() {return drawee;}
    public DirectClothing getPayee() {return payee;}
    public String getCheckNumber() {return checkNumber;}
    public String getRoutingNumber() {return routingNumber;}
    public String getAccountNumber() {return accountNumber;}


    @Override
    public boolean verify() {
        if (drawer == null || drawee == null || payee == null) {
            setStatus(Status.REFUSED);
            return false;
        }

        return true;
    }

    public static String generateCheckNum() {
        return String.format("%06d", checkBatchNum++);
    }

    public static enum SupportedBanks {
        Metrobank(129401887, "Metrobank"), 
        Union_Bank(128550823, "Union Bank"), 
        BPI(124005744, "BPI"), 
        BDO(122603201, "BDO"),
        PNB(127045287, "Philippine National Bank"), 
        AUB(120234908, "Asia United Bank"), 
        RCBC(126487965, "RCBC"), 
        Security_Bank(127628391, "Security Bank"),
        Citibank(128953580, "Citibank"), 
        Landbank(123464521, "Landbank");

        private final int routingNumber;
        private final String displayName;

        SupportedBanks(int routingNum, String displayName) {
            this.routingNumber = routingNum;
            this.displayName = displayName;
        }

        public int getRoutingNumber() {return routingNumber;}
        public String getDisplayName() {return displayName;}
    }
}  
