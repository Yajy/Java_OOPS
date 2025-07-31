package org.example.items;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ItemManager itemManager = new ItemManager();
        Scanner scanner = new Scanner(System.in);

        try {
            if (args.length == 0) {
                System.out.println("No command-line args provided. Switching to interactive mode.");
                itemManager.addItemInteractively(scanner);
            } else {
                itemManager.addItemFromArgs(args);
            }

            while (true) {
                System.out.print("Do you want to enter details of any other item (y/n): ");
                String response = scanner.nextLine().trim();
                if (response.equalsIgnoreCase("y")) {
                    itemManager.addItemInteractively(scanner);
                } else if (response.equalsIgnoreCase("n")) {
                    break;
                } else {
                    System.out.println("Please enter 'y' or 'n'.");
                }
            }

            System.out.println("\n--- Item Summary ---");
            for (Item item : itemManager.getItems()) {
                double tax = TaxCalculator.calculateSalesTaxPerItem(item);
                double finalPrice = TaxCalculator.calculateFinalPricePerItem(item);
                System.out.printf(
                        "Name: %s | Price: %.2f | Quantity: %d | Type: %s | Sales Tax/item: %.2f | Final Price/item: %.2f\n",
                        item.getName(), item.getPrice(), item.getQuantity(), item.getType(),
                        tax, finalPrice);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        } finally {
            scanner.close();
        }
    }
}
