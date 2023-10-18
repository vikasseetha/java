package com.example.shopping;

import java.io.FileInputStream;
import java.io.*;
import java.util.*;

public class OrderHistory {
    private List<Order> orders;



    public OrderHistory() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

  //  public List<Order> getAllOrders() {
//        return orders;
//    }

    public void saveOrderHistory(String fileName) {
        List<Order> existingOrders = loadOrderHistory(fileName);
        existingOrders.addAll(orders);

        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(existingOrders);
            System.out.println("Order history saved successfully to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving order history: " + e.getMessage());
        }
    }

    public List<Order> loadOrderHistory(String fileName) {
        File file = new File(fileName);
        List<Order> loadedOrders = new ArrayList<>();
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                Object obj = ois.readObject();
                if (obj instanceof List) {
                    loadedOrders = (List<Order>) obj;
                    System.out.println("Loaded Order History:");
                    for (Order order : loadedOrders) {
                        displayOrder(order);
                    }
                    System.out.println("Order history loaded successfully from file: " + fileName);
                } else {
                    System.out.println("Invalid order history format in file.");
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading order history from file: " + e.getMessage());
            }
        } else {
            System.out.println("File is not available: " + fileName);
        }
        return loadedOrders;
    }


    public void displayOrder(Order order) {
        System.out.println("--- Order ---");
        System.out.println("Confirmation Number: " + order.getConfirmationNumber());
        for (Map.Entry<Product, Integer> entry : order.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("Product: " + product.getName() +
                    "\nQuantity: " + quantity +
                    "\nTotal Price: $" + (product.getPrice() * quantity) + "\n");
        }
        System.out.println("Total Order Price: $" + order.getTotalPrice());
        System.out.println();
    }

}



