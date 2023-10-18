package com.example.shopping;

import java.io.Serializable;
import java.util.Map;

/**
 * This class is all abou orders here we are adding prducts and total number of quantities and estimate price
 */
public class Order implements Serializable {

    private static final long serialVersionUID = -3073393691900025876L;

    private static int counter = 1;
    private static int lastConfirmationNumber = 0;
    private int confirmationNumber;
    private Map<Product, Integer> items;

    public Order(Map<Product, Integer> items) {
        this.items = items;
        this.confirmationNumber = generateConfirmationNumber();
         calculateTotalPrice();
    }

    private static synchronized int generateConfirmationNumber() {
        return ++lastConfirmationNumber;

    }
    private double totalPrice;

    public int getConfirmationNumber() {
        return confirmationNumber;
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private void calculateTotalPrice() {
        totalPrice = 0.0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += product.getPrice() * quantity;
        }
    }
}


