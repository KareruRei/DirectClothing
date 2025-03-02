package com.directclothing.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.directclothing.dto.CartUpdateDTO;
import com.directclothing.dto.CartUpdateRequest;
import com.directclothing.service.business.Cart;
import com.directclothing.service.business.Catalog;
import com.directclothing.service.business.DirectClothing;
import com.directclothing.service.people.Customer;



@Controller
public class MainController {
    
    // Setting up a dummy object for the clothing system and customer
    @Autowired private DirectClothing clothingSystem;
    @Autowired private Customer customer1;
    
        

    @GetMapping("/")
    public String home(Model model) {

        Catalog[] catalogs = Catalog.sortCatalogs(clothingSystem.getCatalogs().values());

        model.addAttribute("catalogs", catalogs);
        model.addAttribute("cartItems", customer1.getCart().getItems().values());
        model.addAttribute("cartSize", customer1.getCart().getCartSize());
        model.addAttribute("featured", catalogs[0].getMonthlySpecials().values());

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
            customer1.addToCart(catalog, data);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(qtyInStock);
        }

        return ResponseEntity.ok(customer1.getCart().getCartSize());
    }

    @PostMapping(value = "/item-removed", consumes="text/plain", produces = "application/json")
    @ResponseBody
    public ResponseEntity<CartUpdateDTO> removeItemFromCart(@RequestBody String data) {

        try {
            customer1.removeFromCart(data);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        CartUpdateDTO cartUpdate = new CartUpdateDTO(data, 0, 0.0f, 0.0f, customer1.getCart().getFinalPrice(), customer1.getCart().getCartSize());

        return ResponseEntity.ok(cartUpdate);
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

    @PostMapping(value = "/change-qty", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<CartUpdateDTO> changeQuantity(@RequestBody CartUpdateRequest data) {
        String itemID = data.getItemID();
        int newQty = data.getNewQty();

        int qtyInStock = customer1.getCart().getItems().get(itemID).getItem().getProduct().getQuantityInStock();

        if (qtyInStock < newQty)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        customer1.changeCartItemQty(itemID, newQty);
        Cart.CartItem cartItem = customer1.getCart().getItems().get(itemID);

        CartUpdateDTO cartUpdate = new CartUpdateDTO(itemID, newQty, cartItem.getRawPrice(), cartItem.getFinalPrice(), customer1.getCart().getFinalPrice(), customer1.getCart().getCartSize());

        return ResponseEntity.ok(cartUpdate);
    }

    @GetMapping("/payment-page")
    public String directToPaymentPage(Model model) {

        model.addAttribute("cartItems", customer1.getCart().getItems().values());
        model.addAttribute("totalAmount", customer1.getCart().getFinalPrice());

        return "paymentpage";
    }
    
}
