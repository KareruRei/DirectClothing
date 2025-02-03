package people;

public class Employee extends Person {
    private int SSN;
    private Type type;        


    public Employee(String name, int ID, String phone, Type myType, int SSN) {
        super(name, ID, phone);
	    this.type = myType;
        this.SSN = SSN;
    }

    // Setter Methods
    public void setSSN(int mySSN) {SSN = mySSN;}
    public void setType(Type myType) {this.type = myType;}

    // Getter Methods
    public int getSSN() {return SSN;}
    public Type getType() {return this.type;}


    public void placeOrder() {}




    public static enum Type { OEC, CSR }
}