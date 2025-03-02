package com.directclothing.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.directclothing.service.business.Product;
import com.directclothing.service.general.Address;
import com.directclothing.service.people.Customer;
import com.directclothing.service.people.Supplier;


@Configuration
@Import(ProductConfig.class)
public class PeopleConfig {

    private final Address billingAndShippingAddress = new Address();

    private final Product[] supplier1Products;
    private final Product[] supplier2Products;

    // Creating objects of supplier class
    private final Address supplierAddress1 = new Address("8751 Paseo de Roxas", 
                                            "9/F, Equitable Bank Tower", 
                                            "Makati", 
                                            1473, 
                                            "Metro Manila", 
                                            "Philippines");
    private final Address supplierAddress2 = new Address("551 Padre Faura St.", 
                                            "Ermita 1000", 
                                            "Manila", 
                                            2021, 
                                            "Metro Manila", 
                                            "Philippines");

    @Autowired
    public PeopleConfig(Product tshirt, Product pants, Product jacket, Product cardigan, Product skirt, 
                          Product polo, Product jeans, Product dress, Product shorts, Product blazer, 
                          Product sweater, Product leggings, Product overalls, Product trenchCoat, Product jumpsuit) {

        this.supplier1Products = new Product[] {tshirt, pants, jacket, cardigan, skirt, polo, jeans, dress};
        this.supplier2Products = new Product[] {shorts, blazer, sweater, leggings, overalls, trenchCoat, jumpsuit};
    }

    @Bean
    public Supplier supplier1() {
        return new Supplier("Smith & Son, Inc.", supplierAddress1, 85732, "0937-436-2491", supplier1Products);
    }
    
    @Bean
    public Supplier supplier2() {
        return new Supplier("Specter Fabric, Inc.", supplierAddress2, 94712, "0925-214-6523", supplier2Products);
    }

    @Bean
    public Customer customer1() {
        return new Customer("Gabb Agot", 140317, "0915-3769-436", billingAndShippingAddress, billingAndShippingAddress);
    }
}