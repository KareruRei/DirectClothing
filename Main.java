import business.Catalog;
import business.DirectClothing;
import business.Item;
import business.Product;
import general.Address;
import general.Date;
import order.Order;
import order.OrderLine;
import people.Customer;
import people.OrderTaker;
import people.Supplier;
import people.WorkerThread;


public class Main {

    // MAIN PROGRAM     ---------------------------------------------------------------------------------
    public static void main(String[] args) {

        // Setting up a dummy object for the clothing system
        DirectClothing clothingSystem = new DirectClothing();

        // Creating object of customer class
        Address billingAndShippingAddress = new Address("224B F. Cruz St.", 
                                                        "Phase 2 Zephyr Homes Malibay", 
                                                        "Pasay", 
                                                        1300, 
                                                        "Metro Manila", 
                                                        "Philippines");
        Customer customer1 = new Customer("Christian Gabriel Agot", 
                                          224317, 
                                          "0915-376-9436", 
                                          billingAndShippingAddress, 
                                          billingAndShippingAddress);

        // Creating objects of supplier class
        Address supplierAddress1 = new Address("8751 Paseo de Roxas", 
                                               "9/F, Equitable Bank Tower", 
                                               "Makati", 
                                               1473, 
                                               "Metro Manila", 
                                               "Philippines");
        Address supplierAddress2 = new Address("551 Padre Faura St.", 
                                               "Ermita 1000", 
                                               "Manila", 
                                               2021, 
                                               "Metro Manila", 
                                               "Philippines");
        Supplier supplier1 = new Supplier("Smith & Son, Inc.", supplierAddress1, 81340, "0937-436-2491");
        Supplier supplier2 = new Supplier("Specter Fabric, Inc.", supplierAddress2,  37019, "0925-214-6523");

        // Creating objects of the item class
        Product tshirt = new Product("Medium-size, Cotton Fabric T-shirt", "10XP3XQ07VVMCVB9", 439, supplier1);
        Product pants = new Product("Gray Slim Fit Khaki pants", "1JTHTJM4VUP20GV9", 547, supplier1);
        Product jacket = new Product("Beige Oversized hoodie jacket", "1ANJCY4437WRWHQ9", 401, supplier1);
        Product cardigan = new Product("Loose Knit Sweater Cardigan", "1IF7MF9IPDD4VET9", 387, supplier2);
        Product skirt = new Product("Long Cotton Skirt Casual", "12CZB14MEC8LIYD9", 461, supplier2);
        Product polo = new Product("XL Beige Polo Shirt", "1S51UGMNMPFAF099", 526, supplier2);


        // Creating objects of the catalog class
        Item[] catalogItems1 = {
            new Item(tshirt, 699.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "001"),
            new Item(pants, 599.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "002"),
            new Item(polo, 749.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "003"),
            new Item(jacket, 1199.99f, Catalog.Section.MONTHLY_SPECIAL, 20.00f, "004"),
            new Item(cardigan, 799.99f, Catalog.Section.MONTHLY_SPECIAL, 15.00f, "005"),
            new Item(skirt, 499.99f, Catalog.Section.CLOSEOUT_ITEM, 50.00f, "006")
        };
        Item[] catalogItems2 = {
            new Item(jacket, 1199.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "007"),
            new Item(cardigan, 799.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "008"),
            new Item(tshirt, 699.99f, Catalog.Section.MONTHLY_SPECIAL, 25.00f, "009"),
            new Item(pants, 599.99f, Catalog.Section.MONTHLY_SPECIAL, 20.00f, "010"),
            new Item(polo, 749.99f, Catalog.Section.CLOSEOUT_ITEM, 60.00f, "011")
        };

        Catalog catalog1 = new Catalog("DirectClothing Catalog 1", "2URJYRLU1PP5", catalogItems1, Date.create(2024, 2, 14));
        Catalog catalog2 = new Catalog("DirectClothing Catalog 2", "2G7Q5EOBNDG5", catalogItems2, Date.create(2024, 11, 27));

        // Setting up an order taker
	    OrderTaker oEC = new OrderTaker("Sergio Perez", 241856, "0953-182-3512", 587812002);
        
        // Setting up order lines
        OrderLine orderLine1 = new OrderLine(1, catalogItems1[0], Date.now()); 
        OrderLine orderLine2 = new OrderLine(3, catalogItems2[3], Date.now());
        OrderLine orderLine3 = new OrderLine(2, catalogItems2[4], Date.now());

        // Setting up an order
        OrderLine[] orderedItems = {orderLine1, orderLine2, orderLine3};
        Order order1 = new Order("2LIAOIBLSGG7YHW2", Date.now(), orderedItems, customer1, Order.Status.COMPLETED, oEC);


        // -------------------------------------------------------------------------------------------------------------

        WorkerThread workerThread = new WorkerThread(clothingSystem, new OrderTaker[]{oEC});
        workerThread.start();

        while (true) {
            
            synchronized (clothingSystem) {
                clothingSystem.enqueueOrder(order1);
    
                System.out.println("| MAIN THREAD -> " + clothingSystem.getOrderQueueString());
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
