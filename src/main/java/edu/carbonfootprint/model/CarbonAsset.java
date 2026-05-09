package edu.carbonfootprint.model;

import java.util.Objects;

/**
 * Clase abstracta que concentra atributos comunes y permite demostrar herencia.
 * Las clases concretas heredan id, nombre, validaciones y comportamiento comun.
 */
public abstract class CarbonAsset implements CarbonFootprint {
    private final String id;
    private final String name;

    protected CarbonAsset(String id, String name) {
        this.id = requireText(id, "id");
        this.name = requireText(name, "name");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getIdentification() {
        return getType() + " [id=" + id + ", name=" + name + "]";
    }

    public abstract String getType();

    /**
     * Representacion en texto plano para persistencia.
     */
    public abstract String toTextLine();

    protected static String requireText(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " cannot be null");
        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
        if (trimmed.contains("|")) {
            throw new IllegalArgumentException(fieldName + " cannot contain pipe character");
        }
        return trimmed;
    }

    protected static double requireNonNegative(double value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be negative");
        }
        return value;
    }

    protected static double requirePositive(double value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be positive");
        }
        return value;
    }
}
