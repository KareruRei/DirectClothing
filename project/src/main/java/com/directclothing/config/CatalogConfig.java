package com.directclothing.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.directclothing.service.business.Catalog;
import com.directclothing.service.business.Item;
import com.directclothing.service.business.Product;
import com.directclothing.service.general.Date;

@Configuration
@Import(ProductConfig.class)
public class CatalogConfig {

    private final Item[] maleCatalogItems;
    private final Item[] femaleCatalogItems;

    // Constructor injection
    @Autowired
    public CatalogConfig(Product tshirt, Product pants, Product jacket, Product cardigan, 
                        Product skirt, Product polo, Product jeans, Product dress, 
                        Product shorts, Product blazer, Product sweater, 
                        Product leggings, Product overalls, Product trenchCoat, Product jumpsuit, 
                        Product hoodie, Product chinos, Product bomberJacket, Product turtleneck, 
                        Product wrapSkirt, Product henleyShirt, Product cargoPants, Product maxiDress, 
                        Product denimShorts, Product peacoat, Product fleeceSweater, Product joggers, 
                        Product dungarees, Product parkaCoat, Product romper) {

        // Male catalog items
        this.maleCatalogItems = new Item[] {
            new Item(tshirt, 699.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "A001"),
            new Item(pants, 599.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "A002"),
            new Item(jacket, 1199.99f, Catalog.Section.MONTHLY_SPECIAL, 20.00f, "A003"),
            new Item(polo, 749.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "A004"),
            new Item(jeans, 599.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "A005"),
            new Item(shorts, 399.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "A006"),
            new Item(blazer, 899.99f, Catalog.Section.CLOSEOUT_ITEM, 25.00f, "A007"),
            new Item(sweater, 649.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "A008"),
            new Item(overalls, 699.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "A009"),
            
            // New male catalog items
            new Item(hoodie, 439.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "A016"),
            new Item(chinos, 547.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "A017"),
            new Item(bomberJacket, 1199.99f, Catalog.Section.MONTHLY_SPECIAL, 20.00f, "A018"),
            new Item(henleyShirt, 526.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "A019"),
            new Item(cargoPants, 599.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "A020"),
            new Item(denimShorts, 399.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "A021")
        };

        // Female catalog items
        this.femaleCatalogItems = new Item[] {
            new Item(cardigan, 799.99f, Catalog.Section.MONTHLY_SPECIAL, 15.00f, "A010"),
            new Item(skirt, 499.99f, Catalog.Section.CLOSEOUT_ITEM, 50.00f, "A011"),
            new Item(dress, 729.99f, Catalog.Section.MONTHLY_SPECIAL, 10.00f, "A012"),
            new Item(leggings, 479.99f, Catalog.Section.MONTHLY_SPECIAL, 15.00f, "A013"),
            new Item(trenchCoat, 1029.99f, Catalog.Section.MONTHLY_SPECIAL, 20.00f, "A014"),
            new Item(jumpsuit, 859.99f, Catalog.Section.CLOSEOUT_ITEM, 30.00f, "A015"),
            
            // New female catalog items
            new Item(wrapSkirt, 461.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "A022"),
            new Item(turtleneck, 387.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "A023"),
            new Item(maxiDress, 729.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "A024"),
            new Item(peacoat, 899.99f, Catalog.Section.CLOSEOUT_ITEM, 25.00f, "A025"),
            new Item(fleeceSweater, 649.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "A026"),
            new Item(joggers, 479.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "A027"),
            new Item(dungarees, 699.99f, Catalog.Section.NORMAL_ITEM, 0.00f, "A028"),
            new Item(parkaCoat, 1029.99f, Catalog.Section.MONTHLY_SPECIAL, 20.00f, "A029"),
            new Item(romper, 859.99f, Catalog.Section.CLOSEOUT_ITEM, 30.00f, "A030")
        };
    }

    // Beans for Catalogs
    @Bean
    public Catalog maleCatalog() {
        return new Catalog("Male", "2URJYRLU1PP5", maleCatalogItems, Date.create(2024, 2, 14));
    }

    @Bean
    public Catalog femaleCatalog() {
        return new Catalog("Female", "2G7Q5EOBNDG5", femaleCatalogItems, Date.create(2024, 11, 27));
    }
}
