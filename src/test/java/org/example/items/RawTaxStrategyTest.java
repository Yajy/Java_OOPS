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
        double expectedPrice = 112.5; // price + 12.5% tax

        // When
        double actualPrice = strategy.calculateFinalPrice(price);

        // Then
        assertEquals(expectedPrice, actualPrice, 0.001, "Final price should include 12.5% tax");
    }

    @Test
    void calculateTotalPrice_ShouldConsiderQuantity() {
        // Given
        Item item = new Item("Raw Material", 100.0, 2, ItemType.RAW);
        double expectedTotal = 225.0; // (100 * 2) + (12.5% * 200)

        // When
        double actualTotal = strategy.calculateTotalPrice(item);

        // Then
        assertEquals(expectedTotal, actualTotal, 0.001, "Total price should consider quantity and tax");
    }

    @Test
    void roundTax_ShouldRoundUpToNearest5Paise() {
        // Given
        double price = 99.99;
        double rawTax = price * 0.125; // 12.49875
        double expectedRoundedTax = 12.50; // Rounds up to nearest 0.05

        // When
        double actualRoundedTax = strategy.calculateTax(price);

        // Then
        assertEquals(expectedRoundedTax, actualRoundedTax, 0.001, "Tax should be rounded up to nearest 5 paise");
    }
}
