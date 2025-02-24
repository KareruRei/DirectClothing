package ordernpayment;

import business.OrderLine;
import general.Address;
import general.Date;
import people.Customer;

public class OrderForm {
    private Customer customer;
    private OrderLine[] items;
    private Address shippingAddress;
    private Address billingAddress;
    private Date orderDate;

    public OrderForm(Customer customer, Catalog catalog, OrderLine[] items, Address shippingAddress, Address billingAddress, Date orderDate) {
        this.customer = customer;
        this.items = items;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
        this.orderDate = orderDate; 
    }
  
    public Customer getCustomer() { return customer; }
    public OrderLine[] getItems() { return items; }
    public Address getShippingAddress() { return shippingAddress; }
    public Address getBillingAddress() { return billingAddress; }
    public Date getOrderDate() { return orderDate; }

    public void setCustomer(Customer customer) { this.customer = customer; }
    public void setItems(OrderLine[] items) { this.items = items; }
    public void setShippingAddress(Address shippingAddress) { this.shippingAddress = shippingAddress; }
    public void setBillingAddress(Address billingAddress) { this.billingAddress = billingAddress; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    /**
     * Returns a string representation of the order form details.
     * The string includes customer name, items, shipping address,
     * billing address, and order date.
     *
     * @return A string representation of the order form details.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order Form Details:\n");
        sb.append("Customer: ").append(customer.getName()).append("\n");
        sb.append("Items: \n");
        sb.append("Shipping Address: ").append(shippingAddress).append("\n");
        sb.append("Billing Address: ").append(billingAddress).append("\n");
        sb.append("Order Date: ").append(orderDate).append("\n");
        return sb.toString();
    }
}