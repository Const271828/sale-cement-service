package org.example.cementsale;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OrderProcessor {

    private final double startDiscount;
    private final double step;
    private final double price;

    public OrderProcessor(double startDiscount, double step, double price) {
        this.startDiscount = startDiscount;
        this.step = step;
        this.price = price;
    }

    public Map<String, Double> process(List<Order> orders) {
        List<Order> sorted = new ArrayList<>(orders);
        sorted.sort(Comparator.comparing(Order::getDateTime));

        Map<String, Double> totals = new TreeMap<>();
        for (int i = 0; i < sorted.size(); i++) {
            Order order = sorted.get(i);
            double discount = Math.max(0, startDiscount - i * step);
            double cost = order.getWeight() * price * (1 - discount);
            totals.merge(order.getCompany(), cost, Double::sum);
        }
        return totals;
    }
}