package com.directclothing.service.people;

import com.directclothing.service.order.Order;
import com.directclothing.service.order.OrderLine;


public class OrderTaker extends Employee implements Worker {
    private Order orderToProcess;

    public OrderTaker(String name, int ID, String phone, int SSN) {
        super(name, ID, phone, SSN);
    }

    public void setOrderToProcess(Order orderToProcess) {this.orderToProcess = orderToProcess;}
    public Order getOrderToProcess() {return orderToProcess;}


    @Override
    public void doWork() {
        switch (processStep) {
            case 1: processInfo(); break;
            case 2: waitForPayment(); break;
            case 3: checkInventory(); break;
            case 4: allocateStock(); break;
            case 5: completeOrder(); break;
            default: break;
        }
    }
    @Override
    public void completeWork() {

    }
    @Override
    public boolean isOccupied() {return orderToProcess != null;}


    public void processInfo() {
        // do stuff
        orderToProcess = null;
    }

    public void waitForPayment() {
        if (orderToProcess.getStatus() == Order.Status.AWAITING_FULFILLMENT)
            processStep++;
    }

    public void checkInventory() {
        for (OrderLine line : orderToProcess.getItemsOrdered())
            if (line.getCatalogItem().getProduct().getQuantityInStock() < line.getQuantity()) {
                // do stuff
                return;
            }
        
        processStep++;
    }

    public void allocateStock() {
        for (OrderLine line : orderToProcess.getItemsOrdered())
            line.getCatalogItem().getProduct().sellInventory(line.getQuantity());
    }

    public void completeOrder() {
        // do stuff

        orderToProcess = null;
        processStep = 0;
    }
    
}
