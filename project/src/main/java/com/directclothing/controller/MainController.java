package com.directclothing.controller;

import java.lang.reflect.Array;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import com.directclothing.service.business.Catalog;
import com.directclothing.service.business.DirectClothing;
import com.directclothing.service.business.Item;
import com.directclothing.service.business.Cart;
import com.directclothing.service.business.Product;
import com.directclothing.service.general.Address;
import com.directclothing.service.general.Date;
import com.directclothing.service.people.Customer;
import com.directclothing.service.people.Supplier;

import jakarta.annotation.PostConstruct;


@Controller
public class MainController {

        // Setting up a dummy object for the clothing system
        private static DirectClothing clothingSystem = new DirectClothing();

        private static Address billingAndShippingAddress = new Address();
        private static Customer customer1 = new Customer("Gabb Agot", 140317, "0915-3769-436", billingAndShippingAddress, billingAndShippingAddress);

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
        private static Supplier supplier1 = new Supplier("Smith & Son, Inc.", supplierAddress1, 85732, "0937-436-2491");
        private static Supplier supplier2 = new Supplier("Specter Fabric, Inc.", supplierAddress2, 94712, "0925-214-6523");

        // Creating objects of the item class
        private static Product tshirt = new Product("Medium-size, Cotton Fabric T-shirt", "10XP3XQ07VVMCVB9", 439, supplier1, "img/1.jpg");
        private static Product pants = new Product("Gray Slim Fit Khaki pants", "1JTHTJM4VUP20GV9", 547, supplier1, "img/2.jpg");
        private static Product jacket = new Product("Beige Oversized hoodie jacket", "1ANJCY4437WRWHQ9", 401, supplier1, "img/3.jpg");
        private static Product cardigan = new Product("Loose Knit Sweater Cardigan", "1IF7MF9IPDD4VET9", 387, supplier2, "img/4.jpg");
        private static Product skirt = new Product("Long Cotton Skirt Casual", "12CZB14MEC8LIYD9", 461, supplier2, "img/5.jpg");
        private static Product polo = new Product("XL Beige Polo Shirt", "1S51UGMNMPFAF099", 526, supplier2, "img/6.jpg");


        // Creating objects of the catalog class
        private static Item[] catalogItems1 = {
            new Item(tshirt, 699.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "001"),
            new Item(pants, 599.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "002"),
            new Item(polo, 749.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "003"),
            new Item(jacket, 1199.99f, Catalog.Section.MONTHLY_SPECIAL, 20.00f, "004"),
            new Item(cardigan, 799.99f, Catalog.Section.MONTHLY_SPECIAL, 15.00f, "005"),
            new Item(skirt, 499.99f, Catalog.Section.CLOSEOUT_ITEM, 50.00f, "006")
        };
        private static Item[] catalogItems2 = {
            new Item(jacket, 1199.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "007"),
            new Item(cardigan, 799.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "008"),
            new Item(tshirt, 699.99f, Catalog.Section.MONTHLY_SPECIAL, 25.00f, "009"),
            new Item(pants, 599.99f, Catalog.Section.MONTHLY_SPECIAL, 20.00f, "010"),
            new Item(polo, 749.99f, Catalog.Section.CLOSEOUT_ITEM, 60.00f, "011")
        };

        private static Catalog catalog1 = new Catalog("DirectClothing Catalog 1", "2URJYRLU1PP5", catalogItems1, Date.create(2024, 2, 14));
        private static Catalog catalog2 = new Catalog("DirectClothing Catalog 2", "2G7Q5EOBNDG5", catalogItems2, Date.create(2024, 11, 27));

        private static Catalog[] catalogList = {catalog1, catalog2};



    @PostConstruct
    public void init() {
        for (Catalog cat : catalogList)
            clothingSystem.addToCatalogs(cat);

    }
        

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("catalogs", catalogList);

        return "homepage";
    }

    @GetMapping("/catalog")
    public String redirToCatalog(@RequestParam("key") String key, Model model) {

        model.addAttribute("catalogKey", key);

        for (Catalog cat : catalogList) {
            if (cat.getKey().equals(key)) {
                model.addAttribute("normalItems", cat.getNormalItems().values());
                model.addAttribute("monthlySpecials", cat.getMonthlySpecials().values());
                model.addAttribute("closeOuts", cat.getCloseOutItems().values());
                break;
            }
        }

        return "catalog";
    }

    @PostMapping("/item-added")
    public ResponseEntity<String> addItemToCart(@RequestParam("catalog") String catalogKey, @RequestBody String data) {

        try {
            customer1.placeInCart(clothingSystem.getCatalogs().get(catalogKey), data);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Item does not exist in catalog! Failed to add item to cart.");
        }

        return ResponseEntity.ok("Item successfully added to cart!");
    }
}
