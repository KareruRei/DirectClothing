package com.directclothing.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.directclothing.service.business.Cart;
import com.directclothing.service.business.Catalog;
import com.directclothing.service.business.DirectClothing;
import com.directclothing.service.business.Item;
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
        private static Product tshirt = new Product("Medium-size, Cotton Fabric T-shirt", "10XP3XQ07VVMCVB9", 439, supplier1, "img/Cotton Fabric T-shirt.jpg");
        private static Product pants = new Product("Gray Slim Fit Khaki pants", "1JTHTJM4VUP20GV9", 547, supplier1, "img/Gray Slim Fit Khaki pants");
        private static Product jacket = new Product("Beige Oversized hoodie jacket", "1ANJCY4437WRWHQ9", 401, supplier1, "img/Beige Oversized hoodie jacket.webp");
        private static Product cardigan = new Product("Loose Knit Sweater Cardigan", "1IF7MF9IPDD4VET9", 387, supplier2, "img/Loose Knit Sweater Cardigan.jpg");
        private static Product skirt = new Product("Long Cotton Skirt Casual", "12CZB14MEC8LIYD9", 461, supplier2, "img/Long Cotton Skirt Casual.jpg");
        private static Product polo = new Product("XL Beige Polo Shirt", "1S51UGMNMPFAF099", 526, supplier2, "img/XL Beige Polo Shirt.webp");
        private static Product jeans = new Product("Slim Fit Blue Jeans", "123ABC456DEF", 599, supplier1, "img/Slim Fit Blue Jeans.jpg");
        private static Product dress = new Product("Floral Summer Dress", "789GHI012JKL", 729, supplier2, "img/Floral Summer Dress.jpg");
        private static Product shorts = new Product("Casual Cotton Shorts", "345MNO678PQR", 399, supplier1, "img/Casual Cotton Shorts.jpg");
        private static Product blazer = new Product("Formal Black Blazer", "567STU890VWX", 899, supplier2, "img/Formal Black Blazer.jpg");
        private static Product sweater = new Product("Wool Blend Sweater", "901YZA234BCD", 649, supplier1, "img/Wool Blend Sweater.avif");
        private static Product leggings = new Product("High Waist Leggings", "567EFG890HIJ", 479, supplier2, "img/High Waist Leggings.jpg");
        private static Product overalls = new Product("Denim Overalls", "135KLM246NOP", 699, supplier1, "img/Denim Overalls.jpg");
        private static Product trenchCoat = new Product("Long Trench Coat", "789QRS012TUV", 1029, supplier2, "img/Long Trench Coat.webp");
        private static Product jumpsuit = new Product("Elegant Black Jumpsuit", "345WXY678ZAB", 859, supplier1, "img/Elegant Black Jumpsuit.jpg");
        private static Product hoodie = new Product("Loose Fit Cotton Hoodie", "10XP3XQ07VVMCVB9", 439, supplier1, "img/Loose Fit Cotton Hoodie.jpg");
        private static Product chinos = new Product("Slim Fit Navy Chinos", "1JTHTJM4VUP20GV9", 547, supplier1, "img/Slim Fit Navy Chinos.jpg");
        private static Product bomberJacket = new Product("Black Bomber Jacket", "1ANJCY4437WRWHQ9", 401, supplier1, "img/Black Bomber Jacket.jpg");
        private static Product turtleneck = new Product("Ribbed Turtleneck Sweater", "1IF7MF9IPDD4VET9", 387, supplier2, "img/Ribbed Turtleneck Sweater.jpg");
        private static Product wrapSkirt = new Product("Wrap Midi Skirt", "12CZB14MEC8LIYD9", 461, supplier2, "img/Wrap Midi Skirt.jpg");
        private static Product henleyShirt = new Product("Long Sleeve Henley Shirt", "1S51UGMNMPFAF099", 526, supplier2, "img/Long Sleeve Henley Shirt.jpg");
        private static Product cargoPants = new Product("Relaxed Fit Cargo Pants", "123ABC456DEF", 599, supplier1, "img/Relaxed Fit Cargo Pants.jpg");
        private static Product maxiDress = new Product("Bohemian Maxi Dress", "789GHI012JKL", 729, supplier2, "img/Bohemian Maxi Dress.jpg");
        private static Product denimShorts = new Product("High-Waisted Denim Shorts", "345MNO678PQR", 399, supplier1, "img/High-Waisted Denim Shorts.jpg");
        private static Product peacoat = new Product("Wool Blend Peacoat", "567STU890VWX", 899, supplier2, "img/Wool Blend Peacoat.webp");
        private static Product fleeceSweater = new Product("Soft Fleece Pullover", "901YZA234BCD", 649, supplier1, "img/Soft Fleece Pullover.jpg");
        private static Product joggers = new Product("Tapered Fit Joggers", "567EFG890HIJ", 479, supplier2, "img/Tapered Fit Joggers.webp");
        private static Product dungarees = new Product("Classic Denim Dungarees", "135KLM246NOP", 699, supplier1, "img/Classic Denim Dungarees.jpg");
        private static Product parkaCoat = new Product("Waterproof Parka Coat", "789QRS012TUV", 1029, supplier2, "img/Waterproof Parka Coat.jpg");
        private static Product romper = new Product("Casual Cotton Romper", "345WXY678ZAB", 859, supplier1, "img/Casual Cotton Romper.jpg");



        private static Item[] catalogItems1 = {
            new Item(tshirt, 699.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "A001"),
            new Item(pants, 599.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "A002"),
            new Item(polo, 749.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "A003"),
            new Item(jacket, 1199.99f, Catalog.Section.MONTHLY_SPECIAL, 20.00f, "A004"),
            new Item(cardigan, 799.99f, Catalog.Section.MONTHLY_SPECIAL, 15.00f, "A005"),
            new Item(skirt, 499.99f, Catalog.Section.CLOSEOUT_ITEM, 50.00f, "A006"),
            new Item(jeans, 599.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "A007"),
            new Item(dress, 729.99f, Catalog.Section.MONTHLY_SPECIAL, 10.00f, "A008"),
            new Item(shorts, 399.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "A009"),
            new Item(blazer, 899.99f, Catalog.Section.CLOSEOUT_ITEM, 25.00f, "A010")
    };

        private static Item[] catalogItems2 = {
            new Item(sweater, 649.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "A011"),
            new Item(leggings, 479.99f, Catalog.Section.MONTHLY_SPECIAL, 15.00f, "A012"),
            new Item(overalls, 699.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "A013"),
            new Item(trenchCoat, 1029.99f, Catalog.Section.MONTHLY_SPECIAL, 20.00f, "A014"),
            new Item(jumpsuit, 859.99f, Catalog.Section.CLOSEOUT_ITEM, 30.00f, "A015")
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
        model.addAttribute("cartItems", customer1.getCart().getItems().values());
        model.addAttribute("cartSize", customer1.getCart().getCartSize());

        return "homepage";
    }

    @GetMapping("/catalog")
    public String directToCatalog(@RequestParam("key") String key, Model model) {

        model.addAttribute("catalogKey", key);
        model.addAttribute("cartItems", customer1.getCart().getItems().values());
        model.addAttribute("cartSize", customer1.getCart().getCartSize());

        Catalog chosenCatalog = clothingSystem.getCatalogs().get(key);
        model.addAttribute("normalItems", chosenCatalog.getNormalItems().values());
        model.addAttribute("monthlySpecials", chosenCatalog.getMonthlySpecials().values());
        model.addAttribute("closeOuts", chosenCatalog.getCloseOutItems().values());

        return "catalog";
    }

    @PostMapping("/item-added")
    public ResponseEntity<Integer> addItemToCart(@RequestParam("catalog") String catalogKey, @RequestBody String data) {
        
        Catalog catalog = clothingSystem.getCatalogs().get(catalogKey);
        int qtyInStock = catalog.getItemBySKU(data).getProduct().getQuantityInStock();
    
        try {
            customer1.placeInCart(catalog, data);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(qtyInStock);
        }

        return ResponseEntity.ok(customer1.getCart().getCartSize());
    }

    @PostMapping("/item-removed")
    public ResponseEntity<Integer> removeItemFromCart(@RequestBody String data) {

        try {
            customer1.removeItemFromCart(data);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customer1.getCart().getCartSize());
        }

        return ResponseEntity.ok(customer1.getCart().getCartSize());
    }

    @PostMapping("/get-customercart")
    public ResponseEntity<Cart> getCustomerCart() {
        return ResponseEntity.ok(customer1.getCart());
    }

    @GetMapping("/shopping-bag")
    public String directToShoppingBag(Model model) {

        model.addAttribute("shoppingItems", customer1.getCart().viewCartItems());        
        model.addAttribute("totalAmount", customer1.getCart().getFinalPrice());

        return "shoppingbag";
    }

    @PostMapping("/change-qty")
    public ResponseEntity<Integer> changeQuantity(@RequestParam("itemID") String itemID, @RequestParam("newQty") Integer newQty) {

        int qtyInStock = customer1.getCart().getItems().get(itemID).getItem().getProduct().getQuantityInStock();
        if (qtyInStock < newQty)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(qtyInStock);
            

        customer1.changeCartItemQty(itemID, newQty);
        return ResponseEntity.ok(customer1.getCart().getItems().get(itemID).getQuantity());
    }
    
}
