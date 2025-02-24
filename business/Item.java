package business;

public class Item {

    private Product theItem;
    private float price;
    private float discount = 0.00f;
    private String SKU; // new Storage Keeping Unit variable
    private Catalog.Section section = Catalog.Section.NORMAL_ITEM;
    private Catalog fromCatalog;

    public Item(Product theItem, float price, Catalog.Section section, float discount, String SKU) {
        this.theItem = theItem;
        this.price = price;
        this.discount = discount;
        this.section = section;
        this.SKU = SKU;
    }

    public String toString() {return this.theItem.getDescription();}

    // Setter Methods
    public void setItem(Product theItem) {this.theItem = theItem;}
    public void setPrice(float price) {this.price = price;}
    public void setDiscount(float discount) {this.discount = discount;}
    public void setSection(Catalog.Section section) {this.section = section;}
    public void setSKU(String sku) {this.SKU = sku;} // sku setter
    public void setFromCatalog(Catalog cat) {this.fromCatalog = cat;}

    // Getter Methods
    public Product getItem() {return theItem;}
    public float getPrice() {return price;}
    public float getDiscount() {return discount;}
    public Catalog.Section getSection() {return section;}
    public String getSKU() {return SKU;} // sku getter
    public Catalog getFromCatalog() {return this.fromCatalog;}
}
