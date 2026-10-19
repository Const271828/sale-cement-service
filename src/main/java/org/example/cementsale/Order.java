package org.example.cementsale;

import java.time.LocalDateTime;

public class Order {
    private final LocalDateTime dateTime;
    private final String company;
    private final long weight;

    public Order(LocalDateTime dateTime, String company, long weight) {
        this.dateTime = dateTime;
        this.company = company;
        this.weight = weight;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getCompany() {
        return company;
    }

    public long getWeight() {
        return weight;
    }

}
