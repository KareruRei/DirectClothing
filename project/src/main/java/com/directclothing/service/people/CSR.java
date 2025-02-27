package com.directclothing.service.people;

public class CSR extends Employee implements Worker {
    
    public CSR(String name, int ID, String phone, int SSN) {
        super(name, ID, phone, SSN);
    }

    @Override
    public void doWork() {}
    @Override
    public void completeWork() {}
    @Override
    public boolean isOccupied() {return false;}
}
