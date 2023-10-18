package com.example.rental;

public class Motorcycle extends Vehicle {
    private boolean hasSideCar;

    public Motorcycle(String licensePlate, String make, String model, boolean hasSideCar) {
        super(licensePlate, make, model);
        this.hasSideCar = hasSideCar;
    }
}
