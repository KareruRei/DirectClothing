package business;

import java.util.HashMap;

import general.Date;

public class Catalog {
    private String name;
    private String key;
    private HashMap<String, Item> normalItems = new HashMap<>();
    private HashMap<String, Item> monthlySpecials = new HashMap<>();
    private HashMap<String, Item> closeOuts = new HashMap<>();
    private Date dateProduced;


    public Catalog(String name, String key, Item[] catalogItems, Date dateProduced) {
        this.name = name;
        this.key = key;
        this.dateProduced = dateProduced;
        String id;

        for (Item item : catalogItems) {
            item.setFromCatalog(this);
            id = item.getSKU(); // changed item.getProduct to item.getItem for the new SKU 

            if (item.getSection() == Section.NORMAL_ITEM) {normalItems.put(id, item);}
            else if (item.getSection() == Section.MONTHLY_SPECIAL) {monthlySpecials.put(id, item);}
            else {closeOuts.put(id, item);}
        }
    }
    public String toString() {return name;}

    // Setter Methods
    public void setName(String myName) {name = myName;}
    public void setKey(String myKey) {key = myKey;}
    public void setItems(Item[] catalogItems) {
        this.normalItems = new HashMap<>();
        this.monthlySpecials = new HashMap<>();
        this.closeOuts = new HashMap<>();
        String id;

        for (Item item : catalogItems) {
            id = item.getSKU();

            if (item.getSection() == Section.NORMAL_ITEM) {normalItems.put(id, item);}
            else if (item.getSection() == Section.MONTHLY_SPECIAL) {monthlySpecials.put(id, item);}
            else {closeOuts.put(id, item);}
        }
    }
    public void setNormalItems(Item[] catalogItems) {
        this.normalItems = new HashMap<>();
        for (Item item : catalogItems) {normalItems.put(item.getItem().getItemID(), item);}
    }

    public void setMonthlySpecials(Item[] catalogItems) {
        this.monthlySpecials = new HashMap<>();
        for (Item item : catalogItems) {monthlySpecials.put(item.getItem().getItemID(), item);}
    }

    public void setCloseOuts(Item[] catalogItems) {
        this.closeOuts = new HashMap<>();
        for (Item item : catalogItems) {closeOuts.put(item.getItem().getItemID(), item);}
    }

    public void addItems(Item[] catalogItems) {
        String id;

        for (Item item : catalogItems) {
            id = item.getSKU();

            if (item.getSection() == Section.NORMAL_ITEM) {normalItems.put(id, item);}
            else if (item.getSection() == Section.MONTHLY_SPECIAL) {monthlySpecials.put(id, item);}
            else {closeOuts.put(id, item);}
        }

    }
    public void setDateProduced(Date myDate) {dateProduced = myDate;}

    // Getter Methods
    public String getName() {return name;}
    public String getKey() {return key;}
    public HashMap<String, Item> getNormalItems() {return normalItems;}
    public HashMap<String, Item> getMonthlySpecials() {return monthlySpecials;}
    public HashMap<String, Item> getCloseOutItems() {return closeOuts;}
    public Date getDateProduced() {return dateProduced;}
    
    public float getItemPrice(String itemID) {
        if (normalItems.containsKey(itemID)) {return normalItems.get(itemID).getPrice();}
        else if (monthlySpecials.containsKey(itemID)) {return monthlySpecials.get(itemID).getPrice();}
        else if (closeOuts.containsKey(itemID)) {return closeOuts.get(itemID).getPrice();}
        
        return 0.00f;
    }
    public float getItemDiscount(String itemID) {
        if (normalItems.containsKey(itemID)) {return normalItems.get(itemID).getDiscount();}
        else if (monthlySpecials.containsKey(itemID)) {return monthlySpecials.get(itemID).getDiscount();}
        else if (closeOuts.containsKey(itemID)) {return closeOuts.get(itemID).getDiscount();}
    
        return 0.00f;
    }
    public float getDiscountedPrice(String itemID) {
        return getItemPrice(itemID) * ((100.00f - getItemDiscount(itemID))/100.00f);
    }
    public static float solveDiscountedPrice(float price, float discount) {
        return price * ((100.00f - discount)/100.00f);
    }

    // Remove Methods
    public void removeItem(String itemID) {
        if (normalItems.containsKey(itemID)) {normalItems.remove(itemID);}
        else if (monthlySpecials.containsKey(itemID)) {monthlySpecials.remove(itemID);}
        else if (closeOuts.containsKey(itemID)) {closeOuts.remove(itemID);}
    }

    public Item getItemBySKU(String sku) {  // NEW STORAGE KEEPING UNIT - SKU STUFF these r all to get an item 
        for (Item item : normalItems.values()) {
            if (item.getSKU().equals(sku))
                return item;
        }
        for (Item item : monthlySpecials.values()) {
            if (item.getSKU().equals(sku))
                return item;
        }
        for (Item item : closeOuts.values()) {
            if (item.getSKU().equals(sku))
                return item;
        }
        return null; // this returns null if no item was found with the given sku
    }
    
    // Gets the time difference in months between two dates
    public int getMonthDelta(Date otherDate) {
        return this.dateProduced.getDate().getMonthValue() - otherDate.getDate().getMonthValue();
    }


    public static enum Section {
        NORMAL_ITEM,
        MONTHLY_SPECIAL,
        CLOSEOUT_ITEM
    }
}
