package business;
import java.util.HashMap;
import ordernpayment.Order;

// This class acts as a dummy class to store catalogs
final public class DirectClothing {
    private float funds = 1000.00f;
    private HashMap<String, Catalog> currentCatalogs; // Key: catalog key
    private HashMap<Integer, Order> orders; // Key: customer id


    public String toString() {return "DirectClothing Design";}

    // Setter Methods
    public void setFunds(float newFunds) {funds = newFunds;}

    // Getter Methods
    public float getFunds() {return funds;}
    public HashMap<String, Catalog> getCatalogs() {return currentCatalogs;}
    public HashMap<Integer, Order> getOrders() {return orders;}

    // Add to funds or Take from funds
    public void addToFunds(float amount) {funds += amount;}
    public void takeFromFunds(float amount) {funds -= amount;}
    // Add to list of catalogs/orders
    public void addToCatalogs(Catalog newCatalog) {currentCatalogs.put(newCatalog.getKey(), newCatalog);}
    public void addToOrders(Order newOrder) {orders.put(newOrder.getCustomer().getID(), newOrder);}
    
    public void takeFromCatalogs(String catalogKey) {
        if (this.currentCatalogs.containsKey(catalogKey)) {
            this.currentCatalogs.remove(catalogKey);
        }
    }
    public void takeFromOrders(int id) {
        if (this.orders.containsKey(id)) {
            this.orders.remove(id);
        }
    }
}