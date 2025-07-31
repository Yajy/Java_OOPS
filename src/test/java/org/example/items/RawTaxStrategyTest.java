package org.example.items;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RawTaxStrategyTest {
    private final RawTax strategy = new RawTax();

    @Test
    void calculateTax_ShouldReturn12Point5Percent() {
        // Given
        double price = 100.0;
        double expectedTax = 12.5; // 12.5% of 100

        // When
        double actualTax = strategy.calculateTax(price);

        // Then
        assertEquals(expectedTax, actualTax, 0.001, "Raw item tax should be 12.5% of price");
    }

    @Test
    void calculateFinalPrice_ShouldIncludeTax() {
        // Given
        double price = 100.0;
        double expectedFinalPrice = 112.5; // price + 12.5% tax

        // When
        double actualFinalPrice = strategy.calculateFinalPrice(price);

        // Then
        assertEquals(expectedFinalPrice, actualFinalPrice, 0.001, "Final price should include 12.5% tax");
    }
}
