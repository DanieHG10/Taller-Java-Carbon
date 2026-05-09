package edu.carbonfootprint.model;

public class Building extends CarbonAsset {
    private final double squareMeters;
    private final double annualKwh;
    private final double annualNaturalGasTherms;
    private final String city;

    public Building(String id, String name, double squareMeters, double annualKwh,
                    double annualNaturalGasTherms, String city) {
        super(id, name);
        this.squareMeters = requirePositive(squareMeters, "squareMeters");
        this.annualKwh = requireNonNegative(annualKwh, "annualKwh");
        this.annualNaturalGasTherms = requireNonNegative(annualNaturalGasTherms, "annualNaturalGasTherms");
        this.city = requireText(city, "city");
    }

    public double getSquareMeters() {
        return squareMeters;
    }

    public double getAnnualKwh() {
        return annualKwh;
    }

    public double getAnnualNaturalGasTherms() {
        return annualNaturalGasTherms;
    }

    public String getCity() {
        return city;
    }

    public double calculateElectricityFootprint() {
        return annualKwh * EmissionFactors.KG_CO2_PER_KWH;
    }

    public double calculateGasFootprint() {
        return annualNaturalGasTherms * EmissionFactors.KG_CO2_PER_NATURAL_GAS_THERM;
    }

    public double calculateCarbonIntensityBySquareMeter() {
        return getCarbonFootprint() / squareMeters;
    }

    @Override
    public double getCarbonFootprint() {
        return calculateElectricityFootprint() + calculateGasFootprint();
    }

    @Override
    public String getType() {
        return "BUILDING";
    }

    @Override
    public String toTextLine() {
        return String.join("|",
                getType(), getId(), getName(),
                String.valueOf(squareMeters),
                String.valueOf(annualKwh),
                String.valueOf(annualNaturalGasTherms),
                city);
    }
}
