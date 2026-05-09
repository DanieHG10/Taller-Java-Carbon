package edu.carbonfootprint.model;

/**
 * Representa un carro de gasolina cuya huella anual depende de millas recorridas y rendimiento.
 */
public class Car extends CarbonAsset {
    private final String brand;
    private final String model;
    private final double annualMiles;
    private final double milesPerGallon;

    public Car(String id, String name, String brand, String model, double annualMiles, double milesPerGallon) {
        super(id, name);
        this.brand = requireText(brand, "brand");
        this.model = requireText(model, "model");
        this.annualMiles = requireNonNegative(annualMiles, "annualMiles");
        this.milesPerGallon = requirePositive(milesPerGallon, "milesPerGallon");
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getAnnualMiles() {
        return annualMiles;
    }

    public double getMilesPerGallon() {
        return milesPerGallon;
    }

    public double calculateAnnualGallonsConsumed() {
        return annualMiles / milesPerGallon;
    }

    @Override
    public double getCarbonFootprint() {
        double co2 = calculateAnnualGallonsConsumed() * EmissionFactors.KG_CO2_PER_GALLON_GASOLINE;
        return co2 / EmissionFactors.VEHICLE_CO2_TO_GHG_RATIO;
    }

    @Override
    public String getType() {
        return "CAR";
    }

    @Override
    public String toTextLine() {
        return String.join("|",
                getType(), getId(), getName(), brand, model,
                String.valueOf(annualMiles),
                String.valueOf(milesPerGallon));
    }
}
