package org.example.items;

public class ManufacturedTax implements TaxStrategy {
    @Override
    public double calculateTax(double price) {
        double taxOnRaw = 0.125 * price;
        return taxOnRaw + 0.02 * (price + taxOnRaw);
    }
}
