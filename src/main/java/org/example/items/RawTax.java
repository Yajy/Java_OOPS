package org.example.items;

public class RawTax implements TaxStrategy {
    private static final double RAW_TAX_RATE = 0.125; // 12.5%

    @Override
    public double calculateTax(double price) {
        return roundTax(price * RAW_TAX_RATE);
    }
}
