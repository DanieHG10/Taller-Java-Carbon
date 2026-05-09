package edu.carbonfootprint.model;

public class Bicycle extends CarbonAsset {
    private final String frameMaterial;
    private final double annualKilometers;
    private final double manufacturingKgCo2e;
    private final int usefulLifeYears;

    public Bicycle(String id, String name, String frameMaterial, double annualKilometers,
                   double manufacturingKgCo2e, int usefulLifeYears) {
        super(id, name);
        this.frameMaterial = requireText(frameMaterial, "frameMaterial");
        this.annualKilometers = requireNonNegative(annualKilometers, "annualKilometers");
        this.manufacturingKgCo2e = requireNonNegative(manufacturingKgCo2e, "manufacturingKgCo2e");
        if (usefulLifeYears <= 0) {
            throw new IllegalArgumentException("usefulLifeYears must be positive");
        }
        this.usefulLifeYears = usefulLifeYears;
    }

    public String getFrameMaterial() {
        return frameMaterial;
    }

    public double getAnnualKilometers() {
        return annualKilometers;
    }

    public double getManufacturingKgCo2e() {
        return manufacturingKgCo2e;
    }

    public int getUsefulLifeYears() {
        return usefulLifeYears;
    }

    public boolean isLowCarbonTransport() {
        return getCarbonFootprint() < 100;
    }

    @Override
    public double getCarbonFootprint() {
        return manufacturingKgCo2e / usefulLifeYears;
    }

    @Override
    public String getType() {
        return "BICYCLE";
    }

    @Override
    public String toTextLine() {
        return String.join("|",
                getType(), getId(), getName(), frameMaterial,
                String.valueOf(annualKilometers),
                String.valueOf(manufacturingKgCo2e),
                String.valueOf(usefulLifeYears));
    }
}
