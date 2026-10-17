package org.example.cementsale;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportWriter {

    public void write(Path path, Map<String, Double> totals) throws IOException {
        List<String> lines = new ArrayList<>();
        totals.forEach((company, sum) ->
                lines.add(company + " - " + String.format("%.2f", sum)));
        Files.write(path, lines);
    }
}