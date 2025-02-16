package people;
import ordernpayment.Order;


public abstract class Employee extends Person {
    protected int SSN;
    protected Order orderToProcess;
    protected int processStep = 0;


    public Employee(String name, int ID, String phone, int SSN) {
        super(name, ID, phone);
        this.SSN = SSN;
    }

    public abstract void doWork();

    
    public boolean isOccupied() {return orderToProcess != null;}
    public void startWork() {processStep = 1;}

    // Setter Methods
    public void setSSN(int mySSN) {SSN = mySSN;}
    public void setOrderToProcess(Order orderToProcess) {this.orderToProcess = orderToProcess;}

    // Getter Methods
    public int getSSN() {return SSN;}
    public Order getOrderToProcess() {return orderToProcess;}
}