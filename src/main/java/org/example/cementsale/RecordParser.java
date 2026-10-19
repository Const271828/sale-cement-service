package org.example.cementsale;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public final class RecordParser {

    private RecordParser() {
    }

    public static Order parse(String line, String delimiter) {

        String[] parts = line.split(Pattern.quote(delimiter), -1);

        if (parts.length != 3) {
            throw new IllegalArgumentException(
                    "Ожидалось 3 поля (дата|компания|вес), но найдено " + parts.length + " в строке: " + line);
        }

        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(parts[0].trim());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Некорректная дата в строке: " + line, e);
        }

        String company = parts[1].trim();
        if (company.isEmpty()) {
            throw new IllegalArgumentException(
                    "Пустое название компании в строке: " + line);
        }

        long weight;
        try {
            weight = Long.parseLong(parts[2].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Некорректный вес в строке: " + line, e);
        }
        return new Order(dateTime, company, weight);
    }
}