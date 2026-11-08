package org.example.cementsale;

import java.nio.file.Path;

public final class OrderReaderFactory {

    private OrderReaderFactory() { }

    public static OrderReader forFile(Path path) {
        String name = path.getFileName().toString();

        if (name.endsWith(".txt")) {
            return new DelimitedFileAdapter(path, "|"); //выбирвем для файла .txt и "|"
        }
        if (!name.contains(".")) {
            return new DelimitedFileAdapter(path, "#");//выбирвем для файла без расширения и "#"
        }
        throw new IllegalArgumentException("Неподдерживаемое имя файла: " + name);
    }
}