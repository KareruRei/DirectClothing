package com.directclothing.dto;

import com.directclothing.service.payment.CheckPayment;
import com.directclothing.service.payment.CreditCardPayment;


public class EnumModel {
    private CheckPayment.SupportedBanks supportedBanks;
    private CreditCardPayment.SupportedCreditCards supportedCreditCards;

    public void setSupportedBanks(CheckPayment.SupportedBanks bank) {
        this.supportedBanks = bank;
    }
    public CheckPayment.SupportedBanks getSupportedBanks(CheckPayment.SupportedBanks bank) {
        return this.supportedBanks;
    }

    public void setSupportedCreditCards(CreditCardPayment.SupportedCreditCards cc) {
        this.supportedCreditCards = cc;
    }
    public CreditCardPayment.SupportedCreditCards getSupportedCreditCards() {
        return this.supportedCreditCards;
    }
}
