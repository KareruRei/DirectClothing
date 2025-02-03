package people;

public class Person {
    private String name;
    private int ID;
    private String phone;

    
    public Person(String name, int ID, String phone) {
        this.name = name;
        this.ID = ID;
        this.phone = phone;
    }
    public String toString() {return this.name;}

    // Setter Methods
    public void setName(String name) {this.name = name;}
    public void setID(int ID) {this.ID = ID;}
    public void setPhone(String phone) {this.phone = phone;}

    // Getter Methods
    public String getName() {return name;}
    public int getID() {return ID;}
    public String getPhone() {return phone;}
}