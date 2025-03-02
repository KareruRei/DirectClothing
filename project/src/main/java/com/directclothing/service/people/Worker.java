package com.directclothing.service.people;

import com.directclothing.service.order.Order;

public interface Worker {
    public void setWorkload(Order order);
    public void startWork();
    public void doWork();
    public void completeWork();
    public boolean isOccupied();
}