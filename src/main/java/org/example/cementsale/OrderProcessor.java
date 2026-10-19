package org.example.cementsale;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OrderProcessor {

    private final BigDecimal startDiscount;
    private final BigDecimal step;
    private final BigDecimal price;

    public OrderProcessor(double startDiscount, double step, double price) {
        this.startDiscount = BigDecimal.valueOf(startDiscount);
        this.step = BigDecimal.valueOf(step);
        this.price = BigDecimal.valueOf(price);
    }

    public Map<String, BigDecimal> process(List<Order> orders) {
        List<Order> sorted = new ArrayList<>(orders);
        sorted.sort(Comparator.comparing(Order::getDateTime));

        Map<String, BigDecimal> totals = new TreeMap<>();
        for (int i = 0; i < sorted.size(); i++) {
            Order order = sorted.get(i);

            BigDecimal discount = startDiscount.subtract(step.multiply(BigDecimal.valueOf(i)));
            if (discount.signum() < 0) {
                discount = BigDecimal.ZERO;
            }

            BigDecimal weight = BigDecimal.valueOf(order.getWeight());
            BigDecimal cost = weight
                    .multiply(price)
                    .multiply(BigDecimal.ONE.subtract(discount));

            totals.merge(order.getCompany(), cost, BigDecimal::add);
        }
        return totals;
    }
}