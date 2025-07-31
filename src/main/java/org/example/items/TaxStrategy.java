package org.example.items;

public interface TaxStrategy {
    double calculateTax(double price);

    default double calculateFinalPrice(double price) {
        return price + calculateTax(price);
    }
}
