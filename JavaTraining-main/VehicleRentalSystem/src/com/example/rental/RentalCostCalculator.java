package com.example.rental;

import java.math.BigDecimal;

public interface RentalCostCalculator {
    BigDecimal calculateRentalCost(Rental rental);
}
