package org.example.items;

import org.example.items.Item;
import org.example.items.TaxCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaxCalculatorTest {

    @org.junit.Test
    public void calculateSalesTaxPerItem_ForRawItem() {
        // Given
        Item item = new Item("Raw Material", 100.0, 1, "raw");
        double expectedTax = 12.5; // 12.5% of 100

        // When
        double actualTax = TaxCalculator.calculateSalesTaxPerItem(item);

        // Then
        assertEquals(expectedTax, actualTax, 0.001, "Raw item tax calculation failed");
    }

    @Test
    void calculateSalesTaxPerItem_ForManufacturedItem() {
        // Given
        Item item = new Item("Manufactured Item", 100.0, 1, "manufactured");
        double expectedTax = 14.75; // 12.5 + 2% of 112.5

        // When
        double actualTax = TaxCalculator.calculateSalesTaxPerItem(item);

        // Then
        assertEquals(expectedTax, actualTax, 0.001, "Manufactured item tax calculation failed");
    }

    @Test
    void calculateSalesTaxPerItem_ForImportedItem() {
        // Given
        Item item = new Item("Imported Item", 500.0, 1, "imported");
        double expectedTax = 77.5; // 50 (10%) + 27.5 (5% of 550)

        // When
        double actualTax = TaxCalculator.calculateSalesTaxPerItem(item);

        // Then
        assertEquals(expectedTax, actualTax, 0.001, "Imported item tax calculation failed");
    }

    @Test
    void calculateFinalPricePerItem_ForAllTypes() {
        // Given
        Item rawItem = new Item("Raw Material", 100.0, 1, "raw");
        Item manufacturedItem = new Item("Manufactured Item", 100.0, 1, "manufactured");
        Item importedItem = new Item("Imported Item", 500.0, 1, "imported");

        // When & Then
        assertEquals(112.5, TaxCalculator.calculateFinalPricePerItem(rawItem), 0.001,
                "Raw item final price calculation failed");
        assertEquals(114.75, TaxCalculator.calculateFinalPricePerItem(manufacturedItem), 0.001,
                "Manufactured item final price calculation failed");
        assertEquals(577.5, TaxCalculator.calculateFinalPricePerItem(importedItem), 0.001,
                "Imported item final price calculation failed");
    }

    @Test
    void calculateSalesTaxPerItem_ShouldThrowException_ForInvalidType() {
        // Given
        Item invalidItem = new Item("Invalid", 100.0, 1, "invalid");

        // When & Then
        assertThrows(IllegalArgumentException.class,
                () -> TaxCalculator.calculateSalesTaxPerItem(invalidItem),
                "Should throw IllegalArgumentException for invalid item type");
    }
}
