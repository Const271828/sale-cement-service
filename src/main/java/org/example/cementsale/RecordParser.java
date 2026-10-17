package org.example.cementsale;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public final class RecordParser {

    private RecordParser() {
    }

    public static Order parse(String line, String delimiter) {

        String[] parts = line.split(Pattern.quote(delimiter), -1);

        LocalDateTime dateTime = LocalDateTime.parse(parts[0].trim());
        String company = parts[1].trim();
        long weight = Long.parseLong(parts[2].trim());
        return new Order(dateTime, company, weight);
    }
}