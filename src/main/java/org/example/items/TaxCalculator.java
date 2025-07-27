package org.example.items;

public class TaxCalculator {

    public static double calculateSalesTaxPerItem(Item item) {
        double price = item.getPrice();
        String type = item.getType();
        switch (type) {
            case "raw":
                return 0.125 * price;
            case "manufactured":
                double taxOnRaw = 0.125 * price;
                return taxOnRaw + 0.02 * (price + taxOnRaw);
            case "imported":
                double importDuty = 0.10 * price;
                double costAfterDuty = price + importDuty;
                double surcharge;
                if (costAfterDuty <= 100) {
                    surcharge = 5;
                } else if (costAfterDuty <= 200) {
                    surcharge = 10;
                } else {
                    surcharge = 0.05 * costAfterDuty;
                }
                return importDuty + surcharge;
            default:
                throw new IllegalArgumentException("Invalid item type: " + type);
        }
    }

    public static double calculateFinalPricePerItem(Item item) {
        return item.getPrice() + calculateSalesTaxPerItem(item);
    }
}
