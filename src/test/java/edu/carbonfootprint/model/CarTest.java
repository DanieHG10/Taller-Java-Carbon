package edu.carbonfootprint.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CarTest {
    @Test
    void shouldCalculateCarCarbonFootprint() {
        Car car = new Car("C-1", "Auto", "Mazda", "3", 320, 32);

        double gallons = 10;
        double expected = (gallons * EmissionFactors.KG_CO2_PER_GALLON_GASOLINE)
                / EmissionFactors.VEHICLE_CO2_TO_GHG_RATIO;

        assertEquals(expected, car.getCarbonFootprint(), 0.001);
    }

    @Test
    void shouldRejectZeroMilesPerGallon() {
        assertThrows(IllegalArgumentException.class,
                () -> new Car("C-1", "Auto", "Mazda", "3", 100, 0));
    }
}
