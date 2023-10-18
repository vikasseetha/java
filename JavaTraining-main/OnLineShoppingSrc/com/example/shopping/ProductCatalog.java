package com.example.shopping;


import java.io.*;
import java.util.*;

/**
 * This class is used for save products in map save those products into file
 */
public class ProductCatalog {
    public Map<String, Product> products;
    Scanner scanner = new Scanner(System.in);

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
    public ProductCatalog() {
        this.products = new HashMap<>();
    }



public void saveProducts(String fileName) {
        Main main=new Main();
    Map<String, Product> existingProducts =main.loadProducts(fileName);
    existingProducts.putAll(products);
    try (FileOutputStream fos = new FileOutputStream(fileName);
         ObjectOutputStream oos = new ObjectOutputStream(fos)) {
        oos.writeObject(existingProducts);
        System.out.println("Product catalog saved successfully.");
    } catch (IOException e) {
        System.out.println("Error saving product catalog: " + e.getMessage());
    }
}
//    public Product getProduct(String productName) {
//        for (Product product : products.values()) {
//            if (product.getName().equalsIgnoreCase(productName)) {
//                return product;
//            }
//        }
//        return null;  // Product not found
//    }

}