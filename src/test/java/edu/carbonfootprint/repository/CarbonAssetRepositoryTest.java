package edu.carbonfootprint.repository;

import edu.carbonfootprint.model.Bicycle;
import edu.carbonfootprint.model.Building;
import edu.carbonfootprint.model.Car;
import edu.carbonfootprint.model.CarbonAsset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class CarbonAssetRepositoryTest {
    @TempDir
    Path tempDir;

    @Test
    void shouldSaveAndLoadAssetsFromTextFile() throws IOException {
        CarbonAssetRepository repository = new CarbonAssetRepository();
        Path file = tempDir.resolve("assets.txt");

        List<CarbonAsset> original = List.of(
                new Building("B-1", "Campus", 1000, 1000, 10, "Bogota"),
                new Car("C-1", "Auto", "Mazda", "3", 320, 32),
                new Bicycle("BI-1", "Bici", "Acero", 1000, 150, 10)
        );

        repository.save(original, file);
        List<CarbonAsset> loaded = repository.load(file);

        assertEquals(3, loaded.size());
        assertInstanceOf(Building.class, loaded.get(0));
        assertInstanceOf(Car.class, loaded.get(1));
        assertInstanceOf(Bicycle.class, loaded.get(2));
        assertEquals(original.get(0).getCarbonFootprint(), loaded.get(0).getCarbonFootprint(), 0.001);
    }
}
