package org.example.cementsale;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportWriter {

    public void write(Path path, Map<String, BigDecimal> totals) throws IOException {
        List<String> lines = new ArrayList<>();
        totals.forEach((company, sum) ->
                lines.add(company + " - " + sum.setScale(2, RoundingMode.HALF_UP).toPlainString()));
        Files.write(path, lines);
    }
}