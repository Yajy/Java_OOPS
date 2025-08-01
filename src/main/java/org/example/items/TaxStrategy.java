package org.example.items;

public interface TaxStrategy {
    double calculateTax(double price);

    default double calculateFinalPrice(double price) {
        return price + calculateTax(price);
    }

    default double roundTax(double amount) {
        return Math.ceil(amount * 20) / 20;
    }

    default double calculateTotalPrice(Item item) {
        double basePrice = item.getPrice() * item.getQuantity();
        return calculateFinalPrice(basePrice);
    }

    static TaxStrategy createStrategy(ItemType type) {
        return switch (type) {
            case RAW -> new RawTax();
            case MANUFACTURED -> new ManufacturedTax();
            case IMPORTED -> new ImportedTax();
        };
    }
}
