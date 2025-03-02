package com.directclothing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.directclothing.service.business.Product;

@Configuration
public class ProductConfig {

    @Bean
    public Product tshirt() {
        return new Product("Medium-size, Cotton Fabric T-shirt", "10XP3XQ07VVMCVB9", 439, "img/Cotton Fabric T-shirt.jpg");
    }

    @Bean
    public Product pants() {
        return new Product("Gray Slim Fit Khaki pants", "1JTHTJM4VUP20GV9", 547, "img/Gray Slim Fit Khaki pants.jpg");
    }

    @Bean
    public Product jacket() {
        return new Product("Beige Oversized hoodie jacket", "1ANJCY4437WRWHQ9", 401, "img/Beige Oversized hoodie jacket.webp");
    }

    @Bean
    public Product cardigan() {
        return new Product("Loose Knit Sweater Cardigan", "1IF7MF9IPDD4VET9", 387, "img/Loose Knit Sweater Cardigan.jpg");
    }

    @Bean
    public Product skirt() {
        return new Product("Long Cotton Skirt Casual", "12CZB14MEC8LIYD9", 461, "img/Long Cotton Skirt Casual.jpg");
    }

    @Bean
    public Product polo() {
        return new Product("XL Beige Polo Shirt", "1S51UGMNMPFAF099", 526, "img/XL Beige Polo Shirt.webp");
    }

    @Bean
    public Product jeans() {
        return new Product("Slim Fit Blue Jeans", "123ABC456DEF", 599, "img/Slim Fit Blue Jeans.jpg");
    }

    @Bean
    public Product dress() {
        return new Product("Floral Summer Dress", "789GHI012JKL", 729, "img/Floral Summer Dress.jpg");
    }

    @Bean
    public Product shorts() {
        return new Product("Casual Cotton Shorts", "345MNO678PQR", 399, "img/Casual Cotton Shorts.jpg");
    }

    @Bean
    public Product blazer() {
        return new Product("Formal Black Blazer", "567STU890VWX", 899, "img/Formal Black Blazer.jpg");
    }

    @Bean
    public Product sweater() {
        return new Product("Wool Blend Sweater", "901YZA234BCD", 649, "img/Wool Blend Sweater.jpg");
    }

    @Bean
    public Product leggings() {
        return new Product("High Waist Leggings", "567EFG890HIJ", 479, "img/High Waist Leggings.jpg");
    }

    @Bean
    public Product overalls() {
        return new Product("Denim Overalls", "135KLM246NOP", 699, "img/Denim Overalls.jpg");
    }

    @Bean
    public Product trenchCoat() {
        return new Product("Long Trench Coat", "789QRS012TUV", 1029, "img/Long Trench Coat.webp");
    }

    @Bean
    public Product jumpsuit() {
        return new Product("Elegant Black Jumpsuit", "345WXY678ZAB", 859, "img/Elegant Black Jumpsuit.jpg");
    }
}
