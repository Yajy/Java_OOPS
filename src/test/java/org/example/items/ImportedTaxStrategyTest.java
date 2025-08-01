package org.example.items;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ImportedTaxStrategyTest {
    private final ImportedTax strategy = new ImportedTax();

    @Test
    void calculateTax_PriceBelow100_NoSurcharge() {
        // Given
        double price = 90.0;
        double expectedTax = 9.0; // 10% import duty, no surcharge

        // When
        double actualTax = strategy.calculateTax(price);

        // Then
        assertEquals(expectedTax, actualTax, 0.001, "Tax should be 10% with no surcharge");
    }

    @Test
    void calculateTax_PriceBetween100And200_5Surcharge() {
        // Given
        double price = 150.0;
        double importDuty = price * 0.10; // 15.0
        double expectedTax = importDuty + 5; // 20.0

        // When
        double actualTax = strategy.calculateTax(price);

        // Then
        assertEquals(expectedTax, actualTax, 0.001, "Tax should include 10% duty and 5 surcharge");
    }

    @Test
    void calculateTax_PriceAbove200_10Surcharge() {
        // Given
        double price = 250.0;
        double importDuty = price * 0.10; // 25.0
        double expectedTax = importDuty + 10; // 35.0

        // When
        double actualTax = strategy.calculateTax(price);

        // Then
        assertEquals(expectedTax, actualTax, 0.001, "Tax should include 10% duty and 10 surcharge");
    }

    @Test
    void calculateTotalPrice_ShouldConsiderQuantity() {
        // Given
        Item item = new Item("Imported Item", 150.0, 2, ItemType.IMPORTED);
        double importDuty = 150.0 * 0.10 * 2; // 30.0
        double surcharge = 5.0 * 2; // surcharge applies per item
        double expectedTotal = (150.0 * 2) + importDuty + surcharge; // 340.0

        // When
        double actualTotal = strategy.calculateTotalPrice(item);

        // Then
        assertEquals(expectedTotal, actualTotal, 0.001, "Total price should consider quantity and all taxes");
    }

    @Test
    void roundTax_ShouldRoundUpToNearest5Paise() {
        // Given
        double price = 99.99;
        double importDuty = Math.ceil((price * 0.10) * 20) / 20; // 10.0
        double expectedRoundedTax = importDuty; // No surcharge as price < 100

        // When
        double actualRoundedTax = strategy.calculateTax(price);

        // Then
        assertEquals(expectedRoundedTax, actualRoundedTax, 0.001, "Tax should be rounded up to nearest 5 paise");
    }
}
