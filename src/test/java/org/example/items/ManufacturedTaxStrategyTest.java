package org.example.items;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ManufacturedTaxStrategyTest {
    private final ManufacturedTax strategy = new ManufacturedTax();

    @Test
    void calculateTax_ShouldIncludeBaseTaxAndManufacturingTax() {
        // Given
        double price = 100.0;
        // 12.5 (base tax) + 2.25 (2% of 112.5) = 14.75
        double expectedTax = 14.75;

        // When
        double actualTax = strategy.calculateTax(price);

        // Then
        assertEquals(expectedTax, actualTax, 0.001, "Manufactured item tax should include base tax and 2% of (price + base tax)");
    }

    @Test
    void calculateFinalPrice_ShouldIncludeAllTaxes() {
        // Given
        double price = 100.0;
        // 100 + 14.75 = 114.75
        double expectedFinalPrice = 114.75;

        // When
        double actualFinalPrice = strategy.calculateFinalPrice(price);

        // Then
        assertEquals(expectedFinalPrice, actualFinalPrice, 0.001, "Final price should include all taxes");
    }
}
