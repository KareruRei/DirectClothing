import general.*;
import people.*;
import business.*;
import ordernpayment.*;


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
        Supplier supplier1 = new Supplier("Smith & Son, Inc.", supplierAddress1, "0937-436-2491");
        Supplier supplier2 = new Supplier("Specter Fabric, Inc.", supplierAddress2, "0925-214-6523");

        // Creating objects of the item class
        Product tshirt = new Product("Medium-size, Cotton Fabric T-shirt", "10XP3XQ07VVMCVB9", 439, supplier1);
        Product pants = new Product("Gray Slim Fit Khaki pants", "1JTHTJM4VUP20GV9", 547, supplier1);
        Product jacket = new Product("Beige Oversized hoodie jacket", "1ANJCY4437WRWHQ9", 401, supplier1);
        Product cardigan = new Product("Loose Knit Sweater Cardigan", "1IF7MF9IPDD4VET9", 387, supplier2);
        Product skirt = new Product("Long Cotton Skirt Casual", "12CZB14MEC8LIYD9", 461, supplier2);
        Product polo = new Product("XL Beige Polo Shirt", "1S51UGMNMPFAF099", 526, supplier2);


        // Creating objects of the catalog class
        Catalog.Item[] catalogItems1 = {
            new Catalog.Item(tshirt, 699.99f, Catalog.Section.NORMAL_ITEM, 0.00f),
            new Catalog.Item(pants, 599.99f, Catalog.Section.NORMAL_ITEM, 0.00f),
            new Catalog.Item(polo, 749.99f, Catalog.Section.NORMAL_ITEM, 0.00f),
            new Catalog.Item(jacket, 1199.99f, Catalog.Section.MONTHLY_SPECIAL, 20.00f),
            new Catalog.Item(cardigan, 799.99f, Catalog.Section.MONTHLY_SPECIAL, 15.00f),
            new Catalog.Item(skirt, 499.99f, Catalog.Section.CLOSEOUT_ITEM, 50.00f)
        };
        Catalog.Item[] catalogItems2 = {
            new Catalog.Item(jacket, 1199.99f, Catalog.Section.NORMAL_ITEM, 0.00f),
            new Catalog.Item(cardigan, 799.99f, Catalog.Section.NORMAL_ITEM, 0.00f),
            new Catalog.Item(tshirt, 699.99f, Catalog.Section.MONTHLY_SPECIAL, 25.00f),
            new Catalog.Item(pants, 599.99f, Catalog.Section.MONTHLY_SPECIAL, 20.00f),
            new Catalog.Item(polo, 749.99f, Catalog.Section.CLOSEOUT_ITEM, 60.00f)
        };

        Catalog catalog1 = new Catalog("DirectClothing Catalog 1", "2URJYRLU1PP5", catalogItems1, Date.create(2024, 2, 14));
        Catalog catalog2 = new Catalog("DirectClothing Catalog 2", "2G7Q5EOBNDG5", catalogItems2, Date.create(2024, 11, 27));

        // Setting up an order taker
	    Employee oEC = new Employee("Sergio Perez", 241856, "0953-182-3512", Employee.Type.OEC, 587812002);
	    Employee cSR = new Employee("Charles Leclerc", 785121, "0951-246-8914", Employee.Type.CSR, 958127437);
        
        // Setting up order lines
        OrderLine orderLine1 = new OrderLine(1, catalogItems1[0], Date.now(), catalog1); 
        OrderLine orderLine2 = new OrderLine(3, catalogItems2[3], Date.now(), catalog2);
        OrderLine orderLine3 = new OrderLine(2, catalogItems2[4], Date.now(), catalog2);

        // Setting up an order
        OrderLine[] orderedItems = {orderLine1, orderLine2, orderLine3};
        Order order1 = new Order("2LIAOIBLSGG7YHW2", Date.now(), orderedItems, customer1, Order.Status.COMPLETED, oEC);

        // Setting up payment
        CheckPayment thePayment = new CheckPayment(order1.calcAmountOwed(), Payment.Status.PENDING, customer1, 
                                                   Payment.SupportedBanks.BPI, clothingSystem, 313472);


        // USING GETTER METHODS  ---------------------------------------------------------------

        System.out.println("\n=====  CUSTOMER INFORMATION  ===========================================================================\n");
        System.out.println("Name:               "+customer1.getName());
        System.out.println("ID:                 "+customer1.getID());
        System.out.println("Phone No.:          "+customer1.getPhone());
        System.out.println("Shipping Address:   "+customer1.getShippingAddress());
        System.out.println("Billing Address:    "+customer1.getBillingAddress());
            
        System.out.println("\n=====  SUPPLIERS INFORMATION  ===========================================================================\n");
        System.out.println("Name:               "+supplier1.getName());
        System.out.println("Address:            "+supplier1.getAddress());
        System.out.println("Phone No.:          "+supplier1.getPhone());
        System.out.print("\n");
        System.out.println("Name:               "+supplier2.getName());
        System.out.println("Address:            "+supplier2.getAddress());
        System.out.println("Phone No.:          "+supplier2.getPhone());
            
        System.out.println("\n=====  ITEMS INFORMATION  ===========================================================================");
        Product[] myItemList = {tshirt, pants, jacket, cardigan, polo, skirt};
        for (Product anItem : myItemList) {
            System.out.print("\n");
            System.out.println("> Item Description:   "+anItem.getDescription());
            System.out.println("  Item ID:            "+anItem.getItemID());
            System.out.println("  Supplier:           "+anItem.getSupplier().getName());
            System.out.println("  Quantity in Stock:  "+anItem.getQuantityInStock());
        }
            
        System.out.println("\n=====  CATALOG INFORMATION  ===========================================================================\n");
        Catalog[] catalogList = {catalog1, catalog2};
        for (Catalog aCatalog : catalogList) {
            System.out.println("CATALOG NAME:       "+aCatalog.getName());
            System.out.println("CATALOG KEY:        "+aCatalog.getKey());
            System.out.println("DATE PRODUCED:      "+aCatalog.getDateProduced()+"\n");
            System.out.println(" > Normal Items:");
            aCatalog.getNormalItems().values().forEach( 
                (v) -> {
                    System.out.println("   - Item Description:   "+v.getItem().getDescription());
                    System.out.println("     Item Price:         P "+v.getPrice()+"\n");
                }
            );
            System.out.println(" > Monthly Specials:");
            aCatalog.getMonthlySpecials().values().forEach(
                (v) -> {
                    System.out.println("   - Item Description:   "+v.getItem().getDescription());
                    System.out.println("     Item Price:         P "+v.getPrice());
                    System.out.println("     Item Discount:      "+v.getDiscount()+"% OFF");
                    System.out.println("     Discounted Price:   P "+Catalog.solveDiscountedPrice(v.getPrice(), v.getDiscount())+"\n");
                }
            );
            System.out.println(" > Close-out Items:");
            aCatalog.getCloseOutItems().values().forEach(
                (v) -> {
                    System.out.println("   - Item Description:   "+v.getItem().getDescription());
                    System.out.println("     Item Price:         P "+v.getPrice());
                    System.out.println("     Item Discount:      "+v.getDiscount()+"% OFF");
                    System.out.println("     Discounted Price:   P "+Catalog.solveDiscountedPrice(v.getPrice(), v.getDiscount())+"\n");
                }
            );
        }
            
        System.out.println("=====  EMPLOYEE INFORMATION  ===========================================================================\n");
        System.out.println("Name:               "+oEC.getName());
        System.out.println("ID:                 "+oEC.getID());
	    System.out.println("Type:               "+oEC.getType());
        System.out.println("SSN:                "+oEC.getSSN());
        System.out.print("\n");
        System.out.println("Name:               "+cSR.getName());
        System.out.println("ID:                 "+cSR.getID());
	    System.out.println("Type:               "+cSR.getType());
        System.out.println("SSN:                "+cSR.getSSN());
            
        System.out.println("\n=====  ORDER LINE INFORMATION  ===========================================================================");
        for (OrderLine ordered : orderedItems) {
            System.out.print("\n");
            System.out.println("- Item Description:   "+ordered.getCatalogItem().getItem().getDescription());
            System.out.println("  Amount Ordered:     "+ordered.getQuantity());
            System.out.println("  From Catalog:       "+ordered.getFromCatalog().getName());
            System.out.println("  Date Filled:        "+ordered.getDateFilled());
        }

        System.out.println("\n=====  ORDER INFORMATION  ===========================================================================\n");
        System.out.println(order1.printOrder());

        System.out.println("\n=====  PAYMENT INFORMATION  ===========================================================================\n");
        System.out.println("Customer:           "+thePayment.getDrawer());
        System.out.println("Amount:             P "+thePayment.getAmount());
        System.out.println("Mode:               "+thePayment.getMode());
        System.out.println("Bank:               "+thePayment.getDrawee());
        System.out.println("Check Number:       "+thePayment.getCheckNum());
        System.out.println("Payment Status:     "+thePayment.getStatus());

        System.out.println("\n=======================================================================================================\n\n");
    }
}
