package com.example.rental;


public class Car extends Vehicle {
    private int numberOfSeats;

    public Car(String licensePlate, String make, String model, int numberOfSeats) {
        super(licensePlate, make, model);
        this.numberOfSeats = numberOfSeats;
    }

    // Car-specific properties and methods
}
