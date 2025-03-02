package com.directclothing.service.people;

import com.directclothing.service.order.Order;

public class CSR extends Employee implements Worker {
    private Order orderToProcess;
    
    public CSR(String name, int ID, String phone, int SSN) {
        super(name, ID, phone, SSN);
    }


    @Override
    public void setWorkload(Order workload) {
        this.orderToProcess = workload;
    }
    @Override
    public void startWork() {}
    @Override
    public void doWork() {}
    @Override
    public void completeWork() {}
    @Override
    public boolean isOccupied() {return false;}
}
