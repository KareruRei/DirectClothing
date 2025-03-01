package people;
import business.Cart;
import business.Catalog;
import business.Product;
import general.Address;
import order.Order;
import order.OrderLine;


public class Customer extends Person {
    private Address shippingAddress;
    private Address billingAddress;
    private Cart myCart;

    public Customer(String name, int ID, String phone, Address shippingAddress, Address billingAddress) {
        super(name, ID, phone);
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
    }

    // Setter Methods
    public void setShippingAddress(Address myAddress) {shippingAddress = myAddress;}
    public void setBillingAddress(Address myAddress) {billingAddress = myAddress;}

    // Getter Methods
    public Address getShippingAddress() {return shippingAddress;}
    public Address getBillingAddress() {return billingAddress;}

    public void placeOnlineOrder(Catalog whatCatalog, OrderLine[] whatItems) {
        
    }

    public void sendOrderForm(Catalog whatCatalog, Product[] whatItems) {

    }

    public boolean makePayment(double amount, String paymentMethod) {
        // Payment payment = new Payment(amount, paymentMethod);
        // return payment.verify();

        return false;
    }

    public boolean confirmOrder(Order customerOrder) {
        // System.out.println("Order Details:");
        // System.out.println(customerOrder);

        // Scanner scanner = new Scanner(System.in);
        // System.out.println("Do you confirm the order? (yes/no)");
        // String confirmation = scanner.nextLine();

        // if (confirmation.equalsIgnoreCase("yes")) {
        //     if (makePayment(customerOrder.getTotalAmount(), customerOrder.getPayMethod())) {
        //         return true;
        //     }
        // }
        return false;
    }
}