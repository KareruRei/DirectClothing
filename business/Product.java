package business;

import people.Supplier;


public class Product {
    private String description;
    private String itemID;
	private int quantityInStock;
    private Supplier supplier;
    private float price;
    private String imageLink;

    public Product(String desc, String id, int qty, Supplier supp) {
        this.description = desc;
        this.itemID = id;
        this.quantityInStock = qty;
        this.supplier = supp;
    }
    public String toString() {return this.description;}

    // Setter Methods
    public void setDescription(String myDesc) {description = myDesc;}
    public void setItemID(String myID) {itemID = myID;}
    public void setQuantityInStock(int stock) {quantityInStock = stock;}
    public void setSupplier(Supplier mySupplier) {supplier = mySupplier;}
    public void setPrice(float rawPrice) {price = rawPrice;}
    public void setImageLink(String image) {imageLink = image;}

    // Getter Methods
    public String getDescription() {return description;}
    public String getItemID() {return itemID;}
    public int getQuantityInStock() {return quantityInStock;}
    public Supplier getSupplier() {return supplier;}
    public float getPrice() {return price;}
    public String getImageLink() {return imageLink;}

    public int addInventory(int newStock) {
        quantityInStock += newStock;
        return quantityInStock;
    }
    public int sellInventory(int soldStock) {
        quantityInStock -= soldStock;
        return quantityInStock;
    }
}