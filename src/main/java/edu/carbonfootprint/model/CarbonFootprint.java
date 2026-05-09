package edu.carbonfootprint.model;

/**
 * Contrato que obliga a cualquier fuente emisora a calcular su huella de carbono anual.
 */
public interface CarbonFootprint {
    /**
     * @return huella de carbono anual expresada en kilogramos de CO2 equivalente.
     */
    double getCarbonFootprint();

    /**
     * @return texto corto que identifica el objeto en reportes.
     */
    String getIdentification();
}
