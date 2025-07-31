package org.example.items;

public class ImportedTax implements TaxStrategy {
    @Override
    public double calculateTax(double price) {
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
    }
}
