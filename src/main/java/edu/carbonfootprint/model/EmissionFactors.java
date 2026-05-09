package edu.carbonfootprint.model;

/**
 * Factores de emisión centralizados para reutilizar código y evitar números mágicos.
 */
public final class EmissionFactors {
    private EmissionFactors() {
        throw new IllegalStateException("Utility class");
    }

    /** kg CO2 por kWh de electricidad consumida. Basado en 3.94e-4 toneladas metricas/kWh. */
    public static final double KG_CO2_PER_KWH = 0.394;

    /** kg CO2 por termia de gas natural. Basado en 0.0053 toneladas metricas/termia. */
    public static final double KG_CO2_PER_NATURAL_GAS_THERM = 5.30;

    /** kg CO2 por galon de gasolina consumido. */
    public static final double KG_CO2_PER_GALLON_GASOLINE = 8.89;

    /** Ajuste para incluir CH4 y N2O en vehiculos, usando la proporcion CO2/GEI = 0.994. */
    public static final double VEHICLE_CO2_TO_GHG_RATIO = 0.994;
}
