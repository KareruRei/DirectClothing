package ordernpayment;
import business.Catalog;
import general.Date;

public class OrderLine {
    private int quantity;
    private Catalog.Item theItem;
	private Date dateFilled;
	private Catalog fromCatalog;


    public OrderLine(int quantity, Catalog.Item theItem, Date dateFilled, Catalog fromCatalog) {
        this.quantity = quantity;
        this.theItem = theItem;
        this.dateFilled = dateFilled;
        this.fromCatalog = fromCatalog;
    }

    // Setter Methods
    public void setQuantity(int myQuantity) {quantity = myQuantity; }
    public void setCatalogItem(Catalog.Item myItem) {theItem = myItem;}
    public void setDateFilled(Date myDate) {dateFilled = myDate;}
    public void setFromCatalog(Catalog myCatalog) {fromCatalog = myCatalog;}

    // Getter Methods
    public int getQuantity() {return quantity;}
    public Catalog.Item getCatalogItem() {return theItem;}
    public Date getDateFilled() {return dateFilled;}
    public Catalog getFromCatalog() {return fromCatalog;}
        
    public float calcRawPrice() {
        String id = theItem.getItem().getItemID();
        return fromCatalog.getItemPrice(id) * quantity;
    }
    public float calcOrderLinePrice() {
        String id = theItem.getItem().getItemID();
        return fromCatalog.getDiscountedPrice(id) * quantity;
    }
    public void fillOrder() {}
}