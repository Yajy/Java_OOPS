package org.example.items;

public class ManufacturedTax implements TaxStrategy {
    private static final double MANUFACTURED_TAX_RATE = 0.125; // 12.5%
    private static final double ADDITIONAL_TAX_RATE = 0.02; // 2%

    @Override
    public double calculateTax(double price) {
        double baseTax = price * MANUFACTURED_TAX_RATE;
        double additionalTax = (price + baseTax) * ADDITIONAL_TAX_RATE;
        return roundTax(baseTax + additionalTax);
    }
}
