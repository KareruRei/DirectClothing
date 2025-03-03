package com.directclothing.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.directclothing.service.payment.PaymentProcessor;
import com.directclothing.service.business.DirectClothing;


@Configuration
public class PaymentConfig {

    @Autowired DirectClothing clothingSystem;
    
    @Bean
    public PaymentProcessor paymentProcessor() {
        return new PaymentProcessor(clothingSystem);
    }
}
