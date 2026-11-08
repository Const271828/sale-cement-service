package org.example.cementsale;

import java.io.IOException;
import java.util.List;

public interface OrderReader {
    List<Order> readAll() throws IOException;
}