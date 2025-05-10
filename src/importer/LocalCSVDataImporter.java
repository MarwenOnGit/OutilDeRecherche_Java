package importer;

import inputs.Nom;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class LocalCSVDataImporter implements DataImporter{
        private String filePath;

        public LocalCSVDataImporter(String filePath) {
            this.filePath = filePath;
        }
        @Override
        public List<Nom> importData() {
            try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
                return lines.skip(1) // Skip the header line
                        .map(line -> line.split(","))
                        .filter(values -> values.length >= 2)
                        //values[0] nom
                        //values[1] id
                        //testinggg
                        .map(values -> new Nom(values[0].trim(), values[1].trim()))
                        .toList();
            } catch (IOException e) {
                e.printStackTrace();
                return List.of();
            }
        }

}
