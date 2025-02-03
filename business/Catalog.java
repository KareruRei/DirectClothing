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
            id = item.getItem().getItemID();

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
            id = item.getItem().getItemID();

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
            id = item.getItem().getItemID();

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
    
    // Gets the time difference in months between two dates
    public int getMonthDelta(Date otherDate) {
        return this.dateProduced.getDate().getMonthValue() - otherDate.getDate().getMonthValue();
    }




    public static enum Section {
        NORMAL_ITEM,
        MONTHLY_SPECIAL,
        CLOSEOUT_ITEM
    }
    public static class Item {
        private Product theItem;
        private float price;
        private float discount = 0.00f;
        private Section section = Section.NORMAL_ITEM;

        public Item(Product theItem, float price, Section section, float discount) {
            this.theItem = theItem;
            this.price = price;
            this.discount = discount;
            this.section = section;
        }

        public String toString() {return this.theItem.getDescription();}

        // Setter Methods
        public void setItem(Product theItem) {this.theItem = theItem;}
        public void setPrice(float price) {this.price = price;}
        public void setDiscount(float discount) {this.discount = discount;}
        public void setSection(Section section) {this.section = section;}

        // Getter Methods
        public Product getItem() {return theItem;}
        public float getPrice() {return price;}
        public float getDiscount() {return discount;}
        public Section getSection() {return section;}
    }
}