package edu.carbonfootprint.repository;

import edu.carbonfootprint.model.CarbonAsset;
import edu.carbonfootprint.model.CarbonAssetFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Clase encargada unicamente del manejo de archivos de texto.
 */
public class CarbonAssetRepository {
    public void save(List<? extends CarbonAsset> assets, Path path) throws IOException {
        Path parent = path.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        List<String> lines = assets.stream()
                .map(CarbonAsset::toTextLine)
                .toList();
        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    public List<CarbonAsset> load(Path path) throws IOException {
        return Files.readAllLines(path, StandardCharsets.UTF_8)
                .stream()
                .filter(line -> !line.isBlank())
                .map(CarbonAssetFactory::fromTextLine)
                .toList();
    }
}
