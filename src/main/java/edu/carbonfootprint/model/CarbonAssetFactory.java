package edu.carbonfootprint.model;

/**
 * Fabrica responsable de reconstruir objetos desde una linea de texto.
 */
public final class CarbonAssetFactory {
    private CarbonAssetFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static CarbonAsset fromTextLine(String line) {
        String[] parts = line.split("\\|", -1);
        if (parts.length == 0) {
            throw new IllegalArgumentException("Empty line");
        }

        return switch (parts[0]) {
            case "BUILDING" -> buildBuilding(parts);
            case "CAR" -> buildCar(parts);
            case "BICYCLE" -> buildBicycle(parts);
            default -> throw new IllegalArgumentException("Unknown type: " + parts[0]);
        };
    }

    private static Building buildBuilding(String[] parts) {
        validateLength(parts, 7, "BUILDING");
        return new Building(
                parts[1],
                parts[2],
                Double.parseDouble(parts[3]),
                Double.parseDouble(parts[4]),
                Double.parseDouble(parts[5]),
                parts[6]);
    }

    private static Car buildCar(String[] parts) {
        validateLength(parts, 7, "CAR");
        return new Car(
                parts[1],
                parts[2],
                parts[3],
                parts[4],
                Double.parseDouble(parts[5]),
                Double.parseDouble(parts[6]));
    }

    private static Bicycle buildBicycle(String[] parts) {
        validateLength(parts, 7, "BICYCLE");
        return new Bicycle(
                parts[1],
                parts[2],
                parts[3],
                Double.parseDouble(parts[4]),
                Double.parseDouble(parts[5]),
                Integer.parseInt(parts[6]));
    }

    private static void validateLength(String[] parts, int expected, String type) {
        if (parts.length != expected) {
            throw new IllegalArgumentException(type + " requires " + expected + " fields");
        }
    }
}
