package com.example.rental;
public abstract class Vehicle implements VehicleOperations {

    private String licensePlate;
    private String make;
    private String model;
    private boolean isAvailable;

    public Vehicle(String licensePlate, String make, String model) {
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.isAvailable = true;
    }
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}

