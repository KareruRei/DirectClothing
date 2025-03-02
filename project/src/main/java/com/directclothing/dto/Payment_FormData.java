package com.directclothing.dto;

public class Payment_FormData {
    private String creditCard;
    private String creditCardNumber;
    private String cvv;
    private String cardHolderName;
    
    private String drawer;
    private String accountNum;
    private String payee;
    private String routingNum;
    private String checkNum;

    public Payment_FormData(String creditCard, String creditCardNumber, String cvv, String cardHolderName) {
        this.creditCard = creditCard;
        this.creditCardNumber = creditCardNumber;
        this.cvv = cvv;
        this.cardHolderName = cardHolderName;
    }

    public Payment_FormData(String drawer, String accountNum, String payee, String routingNum, String checkNum) {
        this.drawer = drawer;
        this.accountNum = accountNum;
        this.payee = payee;
        this.routingNum = routingNum;
        this.checkNum = checkNum;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setDrawer(String drawer) {
        this.drawer = drawer;
    }

    public String getDrawer() {
        return drawer;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getPayee() {
        return payee;
    }

    public void setRoutingNum(String routingNum) {
        this.routingNum = routingNum;
    }

    public String getRoutingNum() {
        return routingNum;
    }

    public void setCheckNum(String checkNum) {
        this.checkNum = checkNum;
    }

    public String getCheckNum() {
        return checkNum;
    }
}
