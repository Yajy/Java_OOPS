package org.example.items;

public class TaxCalculator {
    private static TaxStrategy getStrategy(ItemType type) {
        return TaxStrategy.createStrategy(type);
    }

    public static double calculateSalesTaxPerItem(Item item) {
        TaxStrategy strategy = getStrategy(item.getType());
        return strategy.calculateTax(item.getPrice());
    }

    public static double calculateFinalPricePerItem(Item item) {
        TaxStrategy strategy = getStrategy(item.getType());
        return strategy.calculateFinalPrice(item.getPrice());
    }

    public static double calculateTotalPrice(Item item) {
        TaxStrategy strategy = getStrategy(item.getType());
        return strategy.calculateTotalPrice(item);
    }
}
