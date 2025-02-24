package business;
  
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList; // tbf idk if im even gonna be using these often

import ordernpayment.Order;
import ordernpayment.OrderLine;
import people.Customer;
import people.Employee;
import general.Date; //  THE ENTIRE Order CLASS STUFF HAHASDASUDAJS

public class Cart {
  private HashMap<String, CartItem> items = new HashMap<>();
  private Catalog catalog; // references from catalog to get prices and discounts
  private Customer customer; // references customer

  public Cart(Catalog catalog) // initialize the catalog
  this.catalog = catalog;
  this.customer = customer;

  public static class CartItem {
    private Item item;
    private int quantity;

    public CartItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
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
private string generateOrderID() {
  return "Order:" + System.currentTimeMillIs();
}
public void clearCart() {
  item.clear();
}
    
public void addItem(String itemID, int quantity) {
    Item item = catalog.getNormalItems().get(itemID);
    if (item == null) { // for future references this is if item/itemid does not exist/not set
        item = catalog.getMonthlySpecials().get(itemID);
    }
    if (item == null) {
        item = catalog.getCloseOutItems().get(itemID);
    }

    if (item != null) {
      if (item.containsKey(ItemID)) { // if item exists in cart, updates the quantity
        CartItem cartItem = item.get(itemID);
        cartItem setQuantity(cartItem.getQuantity() + quantity);
      }
      else { // create a new CartItem and adds it to the item collection
        item.put(itemID, new CartItem(item, quantity));
      }
    } // if item does not exist idk how to implement a warning/message for "This item does not exist" without terminal
  else {
    throw new IllegalStateException("This item does not exist"); // lmao idk if this even is suitable
  }
}

public void removeItem(String itemID) {
  item.remove(ItemID);
  }

public void updateItemQuantity(String itemID, int newQuantity) {
  if (item.containsKey(ItemID)) {
    CartItem cartItem = item.get(ItemID);
    if (newQuantity <= 0) {
      removeItem(ItemID); 
    else {
      cartItem.setQuantity(newQuantity);
    }
  }
}

public float getRawPrice() {
  float total = 0.0f;
  for (CartItem cartItem : item.value()) {
    total += cartItem.getItem().getPrice() * cartItem.getQuantity();
  }
  return total;
}

public float getFinalPrice() {
  float total = 0.0f;
  for (CartItem cartItem : item.value()) {
    total += catalog.getDiscountedPrice(id) * cartItem.getQuantity();
  }
  return total;
}

public int getItemCount() {
  int totalItemAmount = 0;
  for (CartItem cartItem : item.value()) {
    totalItemAmount += cartItem.getQuantity();
  }
  return totalItemAmount;
}

public void clearCart() {
  item.clear();
  }

public List<CartItem> viewCartItems() {
  return new ArrayList<>(item.value());
  }

public Order checkout(Employee placedBy) { // ok so this is supposed to connect to order HOPEFULLY 
  if (item.isEmpty()) {
    throw new IllegalStateException("Cannot checkout with an empty cart.");
  }
  String orderID = generateOrderID();
  Date dateOrdered = new Date();
  OrderLine[] orderLines = new OrderLine[item.size()]; // creates an array equal to the size of items in cart
  
  int index = 0;
  for (CartItem cartItem : item.value()) { // an ORDERLINE FOR EACH ITEM IS CRAZY / gets each cartitem in the item collection
    orderLines[index++] = new OrderLine(cartItem.getItem(), cartItem.getQuantity()); // creates ANOTHER ORDERLINE OBJECT for each cartitem to get quantity
  }

  Order order = new Order(orderID, dateOrdered, orderLines, customer, Order.Status.PENDING, placedBy); // creates the order HOPEFULLY IDK IF THIS WORK HASHDASDAS
  clearCart(); // this should clear the cart after the checkout

  return order; // this should return the order that was just created
  }
}
