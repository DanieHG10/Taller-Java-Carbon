package edu.carbonfootprint.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BicycleTest {
    @Test
    void shouldCalculateBicycleCarbonFootprintByAmortizedManufacturing() {
        Bicycle bicycle = new Bicycle("BI-1", "Bici", "Acero", 1000, 150, 10);

        assertEquals(15, bicycle.getCarbonFootprint(), 0.001);
        assertTrue(bicycle.isLowCarbonTransport());
    }

    @Test
    void shouldRejectInvalidUsefulLife() {
        assertThrows(IllegalArgumentException.class,
                () -> new Bicycle("BI-1", "Bici", "Acero", 1000, 150, 0));
    }
}
