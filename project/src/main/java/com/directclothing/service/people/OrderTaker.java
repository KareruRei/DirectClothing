package com.directclothing.service.people;

import com.directclothing.service.order.Order;
import com.directclothing.service.order.OrderLine;


public class OrderTaker extends Employee implements Worker {
    private Order orderToProcess;

    public OrderTaker(String name, int ID, String phone, int SSN) {
        super(name, ID, phone, SSN);
    }


    @Override
    public void startWork() {processStep = 1;}

    @Override
    public void setWorkload(Order order) {this.orderToProcess = order;}

    public Order getOrderToProcess() {return orderToProcess;}


    @Override
    public void doWork() {

        if (orderToProcess.getStatus() == Order.Status.CANCELLED) {
            orderToProcess = null;
            processStep = 0;
        }

        System.out.println(getName() + ": Doing Work on Order ID -> " + orderToProcess.getOrderID());
        switch (processStep) {
            case 1: processInfo(); break;
            case 2: waitForPayment(); break;
            case 3: checkInventory(); break;
            case 4: allocateStock(); break;
            case 5: completeWork(); break;
            default: break;
        }
    }

    @Override
    public void completeWork() {
        orderToProcess = null;
        processStep = 0;

        orderToProcess.setStatus(Order.Status.COMPLETED);
    }
    
    @Override
    public boolean isOccupied() {return orderToProcess != null;}


    public void processInfo() {
        processStep++;
    }


    public void waitForPayment() {
        if (orderToProcess.getStatus() == Order.Status.AWAITING_FULFILLMENT)
            processStep++;
    }

    public void checkInventory() {
        for (OrderLine line : orderToProcess.getItemsOrdered())
            if (line.getCatalogItem().getProduct().getQuantityInStock() < line.getQuantity()) {
                orderToProcess.setStatus(Order.Status.CANCELLED);
            }
        
        processStep++;
    }

    public void allocateStock() {
        for (OrderLine line : orderToProcess.getItemsOrdered())
            line.getCatalogItem().getProduct().sellInventory(line.getQuantity());

        processStep++;
    }
}
