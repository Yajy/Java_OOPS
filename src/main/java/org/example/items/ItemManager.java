package org.example.items;

import java.util.*;

public class ItemManager {
    private final List<Item> items = new ArrayList<>();

    public void addItemFromArgs(String[] args) {
        Map<String, String> options = parseArgs(args);
        Item item = createValidatedItem(options);
        items.add(item);
    }

    //asking for item to be added repeatedly
    public void addItemInteractively(Scanner scanner) {
        while (true) {
            System.out.println("Enter item details in format: -name ItemName -price 100 -quantity 2 -type raw");
            String input = scanner.nextLine();
            String[] args = input.trim().split("\\s+");
            try {
                Map<String, String> options = parseArgs(args);
                Item item = createValidatedItem(options);
                items.add(item);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }
    }


    private Map<String, String> parseArgs(String[] args) {
        Map<String, String> map = new HashMap<>();
        if (args.length % 2 != 0) {
            throw new IllegalArgumentException("Invalid number of arguments. Each option must have a value.");
        }
        for (int i = 0; i < args.length; i += 2) {
            if (!args[i].startsWith("-")) {
                throw new IllegalArgumentException("Invalid option: " + args[i]);
            }
            String key = args[i].substring(1).toLowerCase();
            String value = args[i + 1];
            map.put(key, value);
        }
        return map;
    }

    private Item createValidatedItem(Map<String, String> options) {
        if (!options.containsKey("name") || !options.containsKey("price") ||
                !options.containsKey("quantity") || !options.containsKey("type")) {
            throw new IllegalArgumentException("Missing required options: -name, -price, -quantity, -type");
        }

        String name = options.get("name");
        String type = options.get("type");

        if (!type.equalsIgnoreCase("raw") &&
                !type.equalsIgnoreCase("manufactured") &&
                !type.equalsIgnoreCase("imported")) {
            throw new IllegalArgumentException("Invalid item type. Allowed: raw, manufactured, imported.");
        }

        double price;
        try {
            price = Double.parseDouble(options.get("price"));
            if (price < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid price: must be a positive number.");
        }

        int quantity;
        try {
            quantity = Integer.parseInt(options.get("quantity"));
            if (quantity <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid quantity: must be a positive integer.");
        }

        return new Item(name, price, quantity, type);
    }

    public List<Item> getItems() {
        return items;
    }
}
