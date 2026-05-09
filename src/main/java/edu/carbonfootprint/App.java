package edu.carbonfootprint;

import edu.carbonfootprint.model.Bicycle;
import edu.carbonfootprint.model.Building;
import edu.carbonfootprint.model.Car;
import edu.carbonfootprint.model.CarbonAsset;
import edu.carbonfootprint.model.CarbonFootprint;
import edu.carbonfootprint.repository.CarbonAssetRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<CarbonAsset> assets = List.of(
                new Building("B-001", "Edificio Administrativo", 1200, 18000, 320, "Bogota"),
                new Car("C-001", "Vehiculo Familiar", "Ford", "Explorer", 10500, 32),
                new Bicycle("BI-001", "Bicicleta Urbana", "Aluminio", 2800, 160, 10)
        );

        ArrayList<CarbonFootprint> carbonObjects = new ArrayList<>(assets);
        System.out.println("=== Reporte polimorfico de huella de carbono ===");
        printReport(carbonObjects);

        CarbonAssetRepository repository = new CarbonAssetRepository();
        Path file = Path.of("data", "carbon_objects.txt");

        try {
            repository.save(assets, file);
            System.out.println("\nObjetos almacenados en: " + file.toAbsolutePath());

            List<CarbonAsset> loadedAssets = repository.load(file);
            System.out.println("\n=== Objetos leidos desde archivo ===");
            printReport(new ArrayList<>(loadedAssets));
        } catch (IOException exception) {
            System.err.println("Error al manejar archivo: " + exception.getMessage());
        }
    }

    private static void printReport(ArrayList<? extends CarbonFootprint> carbonObjects) {
        for (CarbonFootprint item : carbonObjects) {
            System.out.printf("%s -> %.2f kg CO2e/año%n",
                    item.getIdentification(),
                    item.getCarbonFootprint());
        }
    }
}
