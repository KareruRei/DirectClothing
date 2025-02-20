package com.directclothing.service.people;

import com.directclothing.service.ordernpayment.Order;
import com.directclothing.service.ordernpayment.OrderLine;


public class OrderTaker extends Employee {

    public OrderTaker(String name, int ID, String phone, int SSN) {
        super(name, ID, phone, SSN);
    }


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
            if (line.getCatalogItem().getItem().getQuantityInStock() < line.getQuantity()) {
                // do stuff
                return;
            }
        
        processStep++;
    }

    public void allocateStock() {
        for (OrderLine line : orderToProcess.getItemsOrdered())
            line.getCatalogItem().getItem().sellInventory(line.getQuantity());
    }

    public void completeOrder() {
        // do stuff

        orderToProcess = null;
        processStep = 0;
    }
    
}
