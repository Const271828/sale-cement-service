package org.example.cementsale;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        final double START_DISCOUNT = 0.50;
        final double STEP = 0.05;
        final double PRICE = 10.0;

        Path path = Path.of("data/discount_day.txt");
        Path outputPath = Path.of("data/report.txt");

        OrderReader reader = OrderReaderFactory.forFile(path);
        List<Order> orders = reader.readAll();

        OrderProcessor processor = new OrderProcessor(START_DISCOUNT, STEP, PRICE);
        Map<String, Double> totals = processor.process(orders);

        ReportWriter writer = new ReportWriter();
        writer.write(outputPath, totals);
    }
}