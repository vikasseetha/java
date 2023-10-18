package com.example.shopping;

import java.util.*;

public class ShoppingCart {
    private Map<Product, Integer> items;

    public ShoppingCart() {
        items = new HashMap<>();
    }
//Here this method is used for add products with items
    public void addItem(Product product, int quantity) {
        if (items.containsKey(product)) {
            int currentQuantity = items.get(product);
            items.put(product, currentQuantity + quantity);
        } else {
            items.put(product, quantity);
        }
    }

    public Map<Product, Integer> getItems() {
        return items;
    }
//This method is used for calculating the price
    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += product.getPrice() * quantity;
        }
        return totalPrice;
    }

    /**
     * This method is used for removing the item by 1 quantity if it is available otehrwise it will print message
     * @param product
     */
    public void removeItem(Product product) {
        if (items.containsKey(product)) {
            int currentQuantity = items.get(product);
            if (currentQuantity == 1) {
                items.remove(product);
            } else {
                items.put(product, currentQuantity - 1);
            }
            System.out.println("Product removed from the shopping cart.");
        } else {
            System.out.println("Product not found in the shopping cart.");
        }
    }}

