package org.example.cementsale;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DelimitedFileAdapter implements OrderReader {

    private final Path path;
    private final String delimiter;

    public DelimitedFileAdapter(Path path, String delimiter) {
        this.path = path;
        this.delimiter = delimiter;
    }

    @Override
    public List<Order> readAll() throws IOException {
        List<Order> result = new ArrayList<>();
        for (String line : Files.readAllLines(path)) {
            if (!line.isBlank()) {
                result.add(RecordParser.parse(line, delimiter));
            }
        }
        return result;
    }
}