package com.directclothing.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.directclothing.service.business.Catalog;
import com.directclothing.service.business.DirectClothing;


@Configuration
public class BusinessConfig {

    @Autowired Catalog maleCatalog;
    @Autowired Catalog femaleCatalog;
    
    @Bean
    public DirectClothing clothingSystem() {
        DirectClothing clothingSystem = new DirectClothing();

        clothingSystem.addToCatalogs(maleCatalog);
        clothingSystem.addToCatalogs(femaleCatalog);

        return clothingSystem;
    }

}
