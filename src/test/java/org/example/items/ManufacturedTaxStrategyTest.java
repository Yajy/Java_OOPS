package org.example.items;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ManufacturedTaxStrategyTest {
    private final ManufacturedTax strategy = new ManufacturedTax();

    @Test
    void calculateTax_ShouldIncludeBaseTaxAndAdditionalTax() {
        // Given
        double price = 100.0;
        // Base tax (12.5%) = 12.5
        // Additional tax (2% of 112.5) = 2.25
        double expectedTax = 14.75;

        // When
        double actualTax = strategy.calculateTax(price);

        // Then
        assertEquals(expectedTax, actualTax, 0.001, "Tax should include 12.5% base tax and 2% additional tax");
    }

    @Test
    void calculateFinalPrice_ShouldIncludeAllTaxes() {
        // Given
        double price = 100.0;
        double expectedPrice = 114.75; // price + 12.5 + 2.25

        // When
        double actualPrice = strategy.calculateFinalPrice(price);

        // Then
        assertEquals(expectedPrice, actualPrice, 0.001, "Final price should include all taxes");
    }

    @Test
    void calculateTotalPrice_ShouldConsiderQuantity() {
        // Given
        Item item = new Item("Manufactured Item", 100.0, 2, ItemType.MANUFACTURED);
        double expectedTotal = 229.50; // (100 * 2) + (14.75 * 2)

        // When
        double actualTotal = strategy.calculateTotalPrice(item);

        // Then
        assertEquals(expectedTotal, actualTotal, 0.001, "Total price should consider quantity and all taxes");
    }

    @Test
    void roundTax_ShouldRoundUpToNearest5Paise() {
        // Given
        double price = 99.99;
        // Base tax (12.5%) = 12.49875
        // Additional tax (2% of 112.48875) = 2.24978
        // Total unrounded tax = 14.74853
        double expectedRoundedTax = 14.75;

        // When
        double actualRoundedTax = strategy.calculateTax(price);

        // Then
        assertEquals(expectedRoundedTax, actualRoundedTax, 0.001, "Tax should be rounded up to nearest 5 paise");
    }
}
