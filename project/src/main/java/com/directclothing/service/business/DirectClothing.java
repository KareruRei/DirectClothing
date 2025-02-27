package com.directclothing.service.business;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import com.directclothing.service.order.Order;


// This class acts as a dummy class to store catalogs
public final class DirectClothing {
    private double funds = 1000.00f;
    private HashMap<String, Catalog> currentCatalogs = new HashMap<>();  // Key: catalog key
    private Queue<Order> orderQueue = new LinkedList<>();


    public String toString() {return "DirectClothing Design";}


    public synchronized void enqueueOrder(Order newOrder) {orderQueue.add(newOrder);}
    public synchronized Order dequeueOrder() {return orderQueue.poll();}
    public synchronized boolean orderQueueIsEmpty() {return orderQueue.isEmpty();}
    public synchronized Order peekOrder() {return orderQueue.peek();}

    public synchronized String getOrderQueueString() {return orderQueue.toString();}

    // Setter Methods
    public void setFunds(double newFunds) {funds = newFunds;}

    // Getter Methods
    public double getFunds() {return funds;}
    public HashMap<String, Catalog> getCatalogs() {return currentCatalogs;}

    // Add to funds or take from funds
    public void addToFunds(float amount) {funds += amount;}
    public void takeFromFunds(float amount) {funds -= amount;}

    // Add to list of catalogs or take from list of catalogs
    public void addToCatalogs(Catalog newCatalog) {currentCatalogs.put(newCatalog.getKey(), newCatalog);}
    public void takeFromCatalogs(String catalogKey) {
        if (this.currentCatalogs.containsKey(catalogKey)) {
            this.currentCatalogs.remove(catalogKey);
        }
    }

}