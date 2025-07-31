package org.example.items;

public class TaxCalculator {
    private static TaxStrategy getStrategy(String type) {
        return switch (type.toLowerCase()) {
            case "raw" -> new RawTax();
            case "manufactured" -> new ManufacturedTax();
            case "imported" -> new ImportedTax();
            default -> throw new IllegalArgumentException("Invalid item type: " + type);
        };
    }

    public static double calculateSalesTaxPerItem(Item item) {
        TaxStrategy strategy = getStrategy(item.getType());
        return strategy.calculateTax(item.getPrice());
    }

    public static double calculateFinalPricePerItem(Item item) {
        TaxStrategy strategy = getStrategy(item.getType());
        return strategy.calculateFinalPrice(item.getPrice());
    }
}
