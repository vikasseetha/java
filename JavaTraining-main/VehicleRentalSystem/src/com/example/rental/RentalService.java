package com.example.rental;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class RentalService implements RentalCostCalculator {
    private List<Vehicle> vehicles;
    private List<Rental> rentals;

    public RentalService() {
        vehicles = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    /**
     *Here we are adding vehicle object details to list of vehicles type
     * @param vehicle
     */
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    /**
     * Here it checking vehicle is available or not and add into available vehicles if we added any extra vehicles into it
     * @return available list of vehicles
     */

    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.isAvailable()) {
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;
    }

    /**
     * Here we are getting vehicle details by license plate
     * @param licensePlate
     * @return
     */
    public Vehicle getVehicleByLicensePlate(String licensePlate) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getLicensePlate().equals(licensePlate)) {
                return vehicle;
            }
        }
        return null;
    }

    /**
     * it gives rental details based on rental id
     * @param rentalId
     * @return
     */
    public Rental getRentalById(String rentalId) {
        for (Rental rental : rentals) {
            if (rental.getId().equals(rentalId)) {
                return rental;
            }
        }
        return null;
    }

    /**
     * It is gives rent vehicles for customeres for rental
     * @param customer
     * @param vehicle
     * @param startTime
     * @param endTime
     * @return
     */
    public Rental rentVehicle(Customer customer, Vehicle vehicle, LocalDateTime startTime, LocalDateTime endTime) {
        if (vehicle.isAvailable()) {
            Rental rental = new Rental(vehicle, customer, startTime, endTime);
            rentals.add(rental);
            vehicle.setAvailable(false);
            return rental;
        } else {
            return null;
        }
    }

    /**
     * here it is calculated rent cost based on hours
     * @param rental
     * @return
     */
    @Override
    public BigDecimal calculateRentalCost(Rental rental) {
        Duration duration = Duration.between(rental.getStartTime(), rental.getEndTime());
        long hours = duration.toHours();
        BigDecimal hourlyRate = BigDecimal.valueOf(10);
        return hourlyRate.multiply(BigDecimal.valueOf(hours));
    }

    /**
     * it is retrun vehicle if available else retun
     * @param rental
     * @return boolean
     */
    public boolean returnVehicle(Rental rental) {
        if (rentals.contains(rental)) {
            rentals.remove(rental);
            rental.getRentedVehicle().setAvailable(true);
            return true;
        } else {
            return false;
        }
    }
}
