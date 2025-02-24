package ordernpayment;
import business.Item;
import general.Date;

public class OrderLine {
    private int quantity;
    private Item theItem;
	private Date dateFilled;


    public OrderLine(int quantity, Item theItem, Date dateFilled) {
        this.quantity = quantity;
        this.theItem = theItem;
        this.dateFilled = dateFilled;
    }

    // Setter Methods
    public void setQuantity(int myQuantity) {quantity = myQuantity; }
    public void setCatalogItem(Item myItem) {theItem = myItem;}
    public void setDateFilled(Date myDate) {dateFilled = myDate;}

    // Getter Methods
    public int getQuantity() {return quantity;}
    public Item getCatalogItem() {return theItem;}
    public Date getDateFilled() {return dateFilled;}
        
    public float calcRawPrice() {
        String id = theItem.getItem().getItemID();
        return theItem.getFromCatalog().getItemPrice(id) * quantity;
    }
    public float calcOrderLinePrice() {
        String id = theItem.getItem().getItemID();
        return theItem.getFromCatalog().getDiscountedPrice(id) * quantity;
    }
    public void fillOrder() {}
}