package org.example.items;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ImportedTaxStrategyTest {
    private final ImportedTax strategy = new ImportedTax();

    @Test
    void calculateTax_WhenPriceLessThan100_ShouldAddFlatSurchargeOf5() {
        // Given
        double price = 80.0;
        // 8 (10% import duty) + 5 (surcharge) = 13
        double expectedTax = 13.0;

        // When
        double actualTax = strategy.calculateTax(price);

        // Then
        assertEquals(expectedTax, actualTax, 0.001, "Import tax for items <= 100 should include 10% duty + 5 surcharge");
    }

    @Test
    void calculateTax_WhenPriceBetween100And200_ShouldAddFlatSurchargeOf10() {
        // Given
        double price = 150.0;
        // 15 (10% import duty) + 10 (surcharge) = 25
        double expectedTax = 25.0;

        // When
        double actualTax = strategy.calculateTax(price);

        // Then
        assertEquals(expectedTax, actualTax, 0.001, "Import tax for items between 100 and 200 should include 10% duty + 10 surcharge");
    }

    @Test
    void calculateTax_WhenPriceAbove200_ShouldAdd5PercentSurcharge() {
        // Given
        double price = 500.0;
        // 50 (10% import duty) + 27.5 (5% of 550) = 77.5
        double expectedTax = 77.5;

        // When
        double actualTax = strategy.calculateTax(price);

        // Then
        assertEquals(expectedTax, actualTax, 0.001, "Import tax for items > 200 should include 10% duty + 5% of (price + duty)");
    }

    @Test
    void calculateFinalPrice_ShouldIncludeAllTaxesAndSurcharges() {
        // Given
        double price = 500.0;
        // 500 + 77.5 = 577.5
        double expectedFinalPrice = 577.5;

        // When
        double actualFinalPrice = strategy.calculateFinalPrice(price);

        // Then
        assertEquals(expectedFinalPrice, actualFinalPrice, 0.001, "Final price should include import duty and surcharge");
    }
}
