package com.example.shopping;

import java.util.*;
import java.util.Scanner;

public class OnlineShoppingSystem {
    boolean exit = false;

    public static void removeItemFromCart(ShoppingCart shoppingCart, Scanner scanner) {
        System.out.print("Enter the name of the product to remove from cart (or 'q' to quit): ");
        String productName = scanner.nextLine();

        if (!productName.equalsIgnoreCase("q")) {
            Product selectedProduct = null;
            Map<Product, Integer> items = shoppingCart.getItems();
            for (Product product : items.keySet()) {
                if (product.getName().equalsIgnoreCase(productName)) {
                    selectedProduct = product;
                    break;
                }
            }

            if (selectedProduct != null) {
                shoppingCart.removeItem(selectedProduct);
                System.out.println("Product removed from the shopping cart.");
            } else {
                System.out.println("Product not found in the shopping cart.");
            }
        }
    }
    public static void viewShoppingCart(ShoppingCart shoppingCart) {
        Map<Product, Integer> items = shoppingCart.getItems();
        if (items.isEmpty()) {
            System.out.println("Shopping cart is empty.");
            return;
        }

        System.out.println("--- Shopping Cart ---");
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("Product: " + product.getName() +
                    "\nQuantity: " + quantity +
                    "\nTotal Price: $" + (product.getPrice() * quantity) + "\n");
        }

        System.out.println("Total Cart Price: $" + shoppingCart.getTotalPrice());
    }

    public static void placeOrder(ShoppingCart shoppingCart, OrderHistory orderHistory, Scanner scanner) {
        Map<Product, Integer> items = shoppingCart.getItems();
        if (items.isEmpty()) {
            System.out.println("Shopping cart is empty. Cannot place an empty order.");
            return;
        }
        Order order = new Order(items);
        orderHistory.addOrder(order);
        System.out.println("--- Order Confirmation ---");
        System.out.println("Order Number: " + order.getConfirmationNumber());
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("Product: " + product.getName() +
                    "\nQuantity: " + quantity +
                    "\nTotal Price: $" + (product.getPrice() * quantity) + "\n");
        }
        System.out.println("Total Order Price: $" + order.getTotalPrice());

        shoppingCart.getItems().clear();
        System.out.println("Order placed successfully.");
    }

}
