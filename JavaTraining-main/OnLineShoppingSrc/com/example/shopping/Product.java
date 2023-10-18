package com.example.shopping;

import java.io.*;
import java.util.*;

//Here we are entering product details
public class Product implements Serializable {

    private static final long serialVersionUID = 4266471637363408444L;

    private String name;
    private String description;
    private double price;
    private int quantity;

    public Product(String name, String description, double price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public Product() {

    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private int id;
    // other properties and methods

//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null || getClass() != obj.getClass()) {
//            return false;
//        }
//        Product other = (Product) obj;
//        return id == other.id;
//    }
//
    @Override
    public String toString() {
        return "Product: " + name +
                "\nDescription: " + description +
                "\nPrice: $" + price +
                "\nQuantity: " + quantity + "\n";
    }
}

