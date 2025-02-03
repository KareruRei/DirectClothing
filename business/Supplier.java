package business;
import general.Address;

public class Supplier {
    private String name;
    private Address address;
    private String phone;
    

    public Supplier(String name, Address address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
    public String toString() {return this.name;}

    // Setter Methods
    public void setName(String myName) {name = myName;}
    public void setAddress(Address myAddress) {address = myAddress;}
    public void setPhone(String myPhone) {phone = myPhone;}

    // Getter Methods
    public String getName() {return name;}
    public Address getAddress() {return address;}
    public String getPhone() {return phone;}
}