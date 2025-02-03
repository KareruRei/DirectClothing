package ordernpayment;

import general.Date;
import people.Customer;
import people.Employee;

public class Order {
    private String orderID;
    private Date dateOrdered;
    private OrderLine[] itemsOrdered;
    private Customer customer;
    private Status orderStatus;
    private Employee placedBy;


    public Order(String id, Date date, OrderLine[] items, Customer customer, Status status, Employee placedBy) {
        this.orderID = id;
        this.dateOrdered = date;
        this.itemsOrdered = items;
        this.customer = customer;
        this.orderStatus = status;
        this.placedBy = placedBy;
    }
    public String toString() {return this.orderID;}

    // Setter Methods
    public void setOrderID(String myOrderID) {orderID = myOrderID;}
    public void setDateOrdered(Date date) {dateOrdered = date;}
    public void setItemsOrdered(OrderLine[] myItems) {itemsOrdered = myItems;}
    public void setCustomer(Customer myCustomer) {customer = myCustomer;}
    public void setStatus(Status current) {orderStatus = current;}
    public void setPlacedBy(Employee placedBy) {this.placedBy = placedBy;}
    
    // Getter Methods
    public String getOrderID() {return orderID;}
    public Date getDateOrdered() {return dateOrdered;}
    public OrderLine[] getItemsOrdered() {return itemsOrdered;}
    public Customer getCustomer() {return customer;}
    public Status getStatus() {return orderStatus;}
    public Employee getPlacedBy() {return placedBy;}
    
    
    public float calcAmountOwed() {
        float totalAmount = 0.00f;
        for (OrderLine orderLine : this.itemsOrdered) {totalAmount += orderLine.calcOrderLinePrice();}
        return totalAmount;
    }

    public String printOrder() {
        String itemsOrderedDescription = "";
        for (int i=0; i < itemsOrdered.length; ++i) {
            itemsOrderedDescription += "\n"+Integer.toString(i+1)+". ITM:  "+itemsOrdered[i].getCatalogItem().getItem().getDescription()+"\n";
            if (i < 10) {
                itemsOrderedDescription += "   QTY:  "+itemsOrdered[i].getQuantity()+"\n";
                itemsOrderedDescription += "   PRC:  P "+itemsOrdered[i].calcRawPrice()+"\n";
                itemsOrderedDescription += "   DSC:  "+itemsOrdered[i].getCatalogItem().getDiscount()+"% OFF"+"\n";
                itemsOrderedDescription += "   AMT:  P "+itemsOrdered[i].calcOrderLinePrice()+"\n";
            } else {
                itemsOrderedDescription += "    QTY: "+itemsOrdered[i].getQuantity()+"\n";
                itemsOrderedDescription += "    PRC:  P "+itemsOrdered[i].calcRawPrice()+"\n";
                itemsOrderedDescription += "    DSC:  "+itemsOrdered[i].getCatalogItem().getDiscount()+"% OFF"+"\n";
                itemsOrderedDescription += "    AMT:  P "+itemsOrdered[i].calcOrderLinePrice()+"\n";
            }
        }
        itemsOrderedDescription += "\nTOTAL AMT:  P "+calcAmountOwed();
        
        String fullOrder = "";
        fullOrder += "CUSTOMER ID:   "+this.customer.getName();
        fullOrder += "\nBILL TO:       "+this.customer.getBillingAddress();
        fullOrder += "\nSHIP TO:       "+this.customer.getShippingAddress();
        fullOrder += "\n"+itemsOrderedDescription+"\n";
        fullOrder += "\nORDER STATUS:  "+this.orderStatus;
        fullOrder += "\nDATE ORDERED:  "+this.dateOrdered;
        fullOrder += "\nORDER ID:      "+this.orderID;
        fullOrder += "\nPLACED BY:     "+this.placedBy;
        
        return fullOrder;
    }

    public void makePayment() {}
    public void cancelOrder() {}




    public static enum Status {
        PENDING,
        AWAITING_PAYMENT,
        AWAITING_FULFILLMENT,
        COMPLETED,
        CANCELLED
    }
}