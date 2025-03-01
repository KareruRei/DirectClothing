package com.directclothing.service.business;
  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List; // tbf idk if im even gonna be using these often

import com.directclothing.service.general.Date;
import com.directclothing.service.order.Order;
import com.directclothing.service.order.OrderLine;
import com.directclothing.service.people.Customer;
import com.directclothing.service.people.Employee;

public class Cart {
  private HashMap<String, CartItem> items = new HashMap<>();
  private int cartSize = 0;
  private Customer customer; // references customer

  public Cart(Customer customer)  { // initialize the catalog
    this.customer = customer;
  }


  private String generateOrderID() {
    return "Order:" + System.currentTimeMillis();
  }
  public void clearCart() {
    items.clear();
  }

  public void addItem(Catalog catalog, String itemID, int quantity) {

      Item item = catalog.getNormalItems().get(itemID);

      
      if (item == null) { // for future references this is if item/itemid does not exist/not set
          item = catalog.getMonthlySpecials().get(itemID);
      }
      if (item == null) {
          item = catalog.getCloseOutItems().get(itemID);
      }

      if (item != null) {
        if (item.getProduct().getQuantityInStock() < quantity) {
          throw new IllegalStateException("Not enough stock for this item.");
        }

        if (items.containsKey(itemID)) { // if item exists in cart, updates the quantity
          CartItem cartItem = items.get(itemID);
          cartItem.setQuantity(cartItem.getQuantity() + quantity);
          if (cartItem.getQuantity() < 1) {
            cartItem.setQuantity(1);
          }
        }
        else { // create a new CartItem and adds it to the item collection
          cartSize++;
          items.put(itemID, new CartItem(item, quantity));
        }
      } // if item does not exist idk how to implement a warning/message for "This item does not exist" without terminal
      else {
        throw new IllegalStateException("This item does not exist");
      }
  }

  public void removeItem(String itemID) {
    items.remove(itemID);
    cartSize--;
  }

  public void updateItemQuantity(String itemID, int newQuantity) {
    if (items.containsKey(itemID)) {
      CartItem cartItem = items.get(itemID);
      if (newQuantity <= 0) {
        removeItem(itemID); 
      } else {
        cartItem.setQuantity(newQuantity);
      }
    }
  }

  public float getRawPrice() {
    float total = 0.0f;
    for (CartItem cartItem : items.values()) {
      total += cartItem.getRawPrice();
    }

    String formatted = String.format("%.2f", total);
    return Float.parseFloat(formatted);
  }

  public float getFinalPrice() {
    float total = 0.0f;

    for (CartItem cartItem : items.values()) {
      total += cartItem.getFinalPrice();
    }
    
    String formatted = String.format("%.2f", total);
    return Float.parseFloat(formatted);
  }

  public int getItemCount() {
    int totalItemAmount = 0;
    for (CartItem cartItem : items.values()) {
      totalItemAmount += cartItem.getQuantity();
    }
    return totalItemAmount;
  }

  public List<CartItem> viewCartItems() {
    return new ArrayList<>(items.values());
  }

  public int getCartSize() {
    return cartSize;
  }

  public HashMap<String, CartItem> getItems() {
    return items;
  }

  public Order checkOut(Employee placedBy) { // ok so this is supposed to connect to order HOPEFULLY 
    if (items.isEmpty()) {
      throw new IllegalStateException ("Cannot checkout with an empty cart.");
    }
    String orderID = generateOrderID();
    Date dateOrdered = Date.now();
    OrderLine[] orderLines = new OrderLine[items.size()]; // creates an array equal to the size of items in cart
    
    int index = 0;
    for (CartItem cartItem : items.values()) { // an ORDERLINE FOR EACH ITEM IS CRAZY / gets each cartitem in the item collection
      orderLines[index++] = new OrderLine(cartItem.getQuantity(), cartItem.getItem(), Date.now()); // creates ANOTHER ORDERLINE OBJECT for each cartitem to get quantity
    }

    Order order = new Order(orderID, dateOrdered, orderLines, customer, Order.Status.PENDING, placedBy); // creates the order HOPEFULLY IDK IF THIS WORK HASHDASDAS
    clearCart(); // this should clear the cart after the checkout

    return order; // this should return the order that was just created
  }

    public static class CartItem {
      private Item item;
      private int quantity;
  
      public CartItem(Item item, int quantity) {
          this.item = item;
          this.quantity = quantity;
      }
    
      public void setItem(Item item) {
        this.item = item;
      }
      public Item getItem() {
        return item;
      }  
      public int getQuantity() {
        return quantity;
      }
      public void setQuantity(int quantity) {
        this.quantity = quantity;
      }
      public float getRawPrice() {
        float price = item.getPrice() * quantity;

        String formatted = String.format("%.2f", price);
        return Float.parseFloat(formatted);
      }
      public float getFinalPrice() {
        float price = item.getDiscountedPrice() * quantity;

        String formatted = String.format("%.2f", price);
        return Float.parseFloat(formatted);
      }
    }
  }
