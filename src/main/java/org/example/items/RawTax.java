package org.example.items;

public class RawTax implements TaxStrategy {
    @Override
    public double calculateTax(double price) {
        return 0.125 * price;
    }
}
