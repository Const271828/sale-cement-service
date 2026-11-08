package org.example.cementsale;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        final double START_DISCOUNT = 0.50;
        final double STEP = 0.05;
        final double PRICE = 10.0;

        Path txtPath = Path.of("data/discount_day.txt");
        Path noExtPath = Path.of("data/discount_day_without_ext");
        Path outputPath = Path.of("data/report.txt");

        List<Order> orders = new ArrayList<>();
        orders.addAll(OrderReaderFactory.forFile(txtPath).readAll());
        orders.addAll(OrderReaderFactory.forFile(noExtPath).readAll());

        OrderProcessor processor = new OrderProcessor(START_DISCOUNT, STEP, PRICE);
        Map<String, BigDecimal> totals = processor.process(orders);

        ReportWriter writer = new ReportWriter();
        writer.write(outputPath, totals);
    }
}