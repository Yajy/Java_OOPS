package org.example.items;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaxCalculatorTest {

    @Test
    void calculateSalesTaxPerItem_Raw() {
        Item item = new Item("Raw Material", 100.0, 1, ItemType.RAW);
        double expectedTax = 12.5; // 12.5% of 100

        double actualTax = TaxCalculator.calculateSalesTaxPerItem(item);

        assertEquals(expectedTax, actualTax, 0.001, "Raw item tax calculation failed");
    }

    @Test
    void calculateSalesTaxPerItem_Manufactured() {
        Item item = new Item("Manufactured Item", 100.0, 1, ItemType.MANUFACTURED);
        double expectedTax = 14.75; // 12.5% base + 2% additional

        double actualTax = TaxCalculator.calculateSalesTaxPerItem(item);

        assertEquals(expectedTax, actualTax, 0.001, "Manufactured item tax calculation failed");
    }

    @Test
    void calculateSalesTaxPerItem_Imported() {
        Item item = new Item("Imported Item", 150.0, 1, ItemType.IMPORTED);
        double expectedTax = 20.0; // 10% import duty (15) + 5 surcharge

        double actualTax = TaxCalculator.calculateSalesTaxPerItem(item);

        assertEquals(expectedTax, actualTax, 0.001, "Imported item tax calculation failed");
    }

    @Test
    void calculateFinalPricePerItem_Raw() {
        Item item = new Item("Raw Material", 100.0, 1, ItemType.RAW);
        double expectedPrice = 112.5; // price + 12.5% tax

        double actualPrice = TaxCalculator.calculateFinalPricePerItem(item);

        assertEquals(expectedPrice, actualPrice, 0.001, "Raw item final price calculation failed");
    }

    @Test
    void calculateFinalPricePerItem_Manufactured() {
        Item item = new Item("Manufactured Item", 100.0, 1, ItemType.MANUFACTURED);
        double expectedPrice = 114.75; // price + 12.5% base + 2% additional

        double actualPrice = TaxCalculator.calculateFinalPricePerItem(item);

        assertEquals(expectedPrice, actualPrice, 0.001, "Manufactured item final price calculation failed");
    }

    @Test
    void calculateFinalPricePerItem_Imported() {
        Item item = new Item("Imported Item", 150.0, 1, ItemType.IMPORTED);
        double expectedPrice = 170.0; // price + 10% duty + 5 surcharge

        double actualPrice = TaxCalculator.calculateFinalPricePerItem(item);

        assertEquals(expectedPrice, actualPrice, 0.001, "Imported item final price calculation failed");
    }

    @Test
    void calculateTotalPrice_ShouldConsiderQuantity() {
        Item item = new Item("Raw Material", 100.0, 2, ItemType.RAW);
        double expectedTotal = 225.0; // (100 * 2) + (12.5 * 2)

        double actualTotal = TaxCalculator.calculateTotalPrice(item);

        assertEquals(expectedTotal, actualTotal, 0.001, "Total price calculation with quantity failed");
    }
}
