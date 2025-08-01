package org.example.items;

public class ImportedTax implements TaxStrategy {
    private static final double IMPORT_DUTY_RATE = 0.10; // 10%
    private static final double SURCHARGE_THRESHOLD_1 = 100;
    private static final double SURCHARGE_THRESHOLD_2 = 200;
    private static final double SURCHARGE_1 = 5;
    private static final double SURCHARGE_2 = 10;
    private static final double DEFAULT_SURCHARGE = 0;

        double importDuty = roundTax(price * IMPORT_DUTY_RATE);
        double surcharge = calculateSurcharge(price); // Using base price for threshold
        return importDuty + surcharge;
    }

    private double calculateSurcharge(double basePrice) {
        if (basePrice <= SURCHARGE_THRESHOLD_1) {
            return DEFAULT_SURCHARGE;
        } else if (basePrice <= SURCHARGE_THRESHOLD_2) {
            return SURCHARGE_1;
        } else if (costAfterDuty <= 200) {
            surcharge = 10;
        } else {
            return SURCHARGE_2;
        }
    }
}
