package com.directclothing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.directclothing.service.business.Catalog;
import com.directclothing.service.business.DirectClothing;
import com.directclothing.service.business.Product;
import com.directclothing.service.business.Supplier;
import com.directclothing.service.general.Address;
import com.directclothing.service.general.Date;


@Controller
public class HomeController {

        // Setting up a dummy object for the clothing system
        private static DirectClothing clothingSystem = new DirectClothing();

        // Creating objects of supplier class
        private static Address supplierAddress1 = new Address("8751 Paseo de Roxas", 
                                               "9/F, Equitable Bank Tower", 
                                               "Makati", 
                                               1473, 
                                               "Metro Manila", 
                                               "Philippines");
        private static Address supplierAddress2 = new Address("551 Padre Faura St.", 
                                               "Ermita 1000", 
                                               "Manila", 
                                               2021, 
                                               "Metro Manila", 
                                               "Philippines");
        private static Supplier supplier1 = new Supplier("Smith & Son, Inc.", supplierAddress1, "0937-436-2491");
        private static Supplier supplier2 = new Supplier("Specter Fabric, Inc.", supplierAddress2, "0925-214-6523");

        // Creating objects of the item class
        private static Product tshirt = new Product("Medium-size, Cotton Fabric T-shirt", "10XP3XQ07VVMCVB9", 439, supplier1);
        private static Product pants = new Product("Gray Slim Fit Khaki pants", "1JTHTJM4VUP20GV9", 547, supplier1);
        private static Product jacket = new Product("Beige Oversized hoodie jacket", "1ANJCY4437WRWHQ9", 401, supplier1);
        private static Product cardigan = new Product("Loose Knit Sweater Cardigan", "1IF7MF9IPDD4VET9", 387, supplier2);
        private static Product skirt = new Product("Long Cotton Skirt Casual", "12CZB14MEC8LIYD9", 461, supplier2);
        private static Product polo = new Product("XL Beige Polo Shirt", "1S51UGMNMPFAF099", 526, supplier2);


        // Creating objects of the catalog class
        private static Catalog.Item[] catalogItems1 = {
            new Catalog.Item(tshirt, 699.99f, Catalog.Section.NORMAL_ITEM, 0.00f),
            new Catalog.Item(pants, 599.99f, Catalog.Section.NORMAL_ITEM, 0.00f),
            new Catalog.Item(polo, 749.99f, Catalog.Section.NORMAL_ITEM, 0.00f),
            new Catalog.Item(jacket, 1199.99f, Catalog.Section.MONTHLY_SPECIAL, 20.00f),
            new Catalog.Item(cardigan, 799.99f, Catalog.Section.MONTHLY_SPECIAL, 15.00f),
            new Catalog.Item(skirt, 499.99f, Catalog.Section.CLOSEOUT_ITEM, 50.00f)
        };
        private static Catalog.Item[] catalogItems2 = {
            new Catalog.Item(jacket, 1199.99f, Catalog.Section.NORMAL_ITEM, 0.00f),
            new Catalog.Item(cardigan, 799.99f, Catalog.Section.NORMAL_ITEM, 0.00f),
            new Catalog.Item(tshirt, 699.99f, Catalog.Section.MONTHLY_SPECIAL, 25.00f),
            new Catalog.Item(pants, 599.99f, Catalog.Section.MONTHLY_SPECIAL, 20.00f),
            new Catalog.Item(polo, 749.99f, Catalog.Section.CLOSEOUT_ITEM, 60.00f)
        };

        private static Catalog catalog1 = new Catalog("DirectClothing Catalog 1", "2URJYRLU1PP5", catalogItems1, Date.create(2024, 2, 14));
        private static Catalog catalog2 = new Catalog("DirectClothing Catalog 2", "2G7Q5EOBNDG5", catalogItems2, Date.create(2024, 11, 27));



    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("items", catalogItems1);

        return "shoppingbag";
    }
}
