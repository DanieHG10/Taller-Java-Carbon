package edu.carbonfootprint.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BuildingTest {
    @Test
    void shouldCalculateBuildingCarbonFootprint() {
        Building building = new Building("B-1", "Campus", 1000, 1000, 10, "Bogota");

        double expected = (1000 * EmissionFactors.KG_CO2_PER_KWH)
                + (10 * EmissionFactors.KG_CO2_PER_NATURAL_GAS_THERM);

        assertEquals(expected, building.getCarbonFootprint(), 0.001);
    }

    @Test
    void shouldRejectNegativeKwh() {
        assertThrows(IllegalArgumentException.class,
                () -> new Building("B-1", "Campus", 1000, -1, 10, "Bogota"));
    }
}
