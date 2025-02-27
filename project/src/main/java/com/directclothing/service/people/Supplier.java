package com.directclothing.service.people;
import com.directclothing.service.general.Address;

public class Supplier extends Person {
    private Address address;


    public Supplier(String name, Address address, int ID, String phone) {
        super(name, ID, phone);
        this.address = address;
    }

    // Setter Methods
    public void setAddress(Address myAddress) {address = myAddress;}

    // Getter Methods
    public Address getAddress() {return address;}
}