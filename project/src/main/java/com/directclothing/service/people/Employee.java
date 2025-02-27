package com.directclothing.service.people;


public abstract class Employee extends Person {
    protected int SSN;
    protected int processStep = 0;


    public Employee(String name, int ID, String phone, int SSN) {
        super(name, ID, phone);
        this.SSN = SSN;
    }

    public void startWork() {processStep = 1;}

    // Setter Methods
    public void setSSN(int mySSN) {SSN = mySSN;}

    // Getter Methods
    public int getSSN() {return SSN;}
}