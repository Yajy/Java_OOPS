package org.example.items;

public class Item {
    private final String name;
    private final double price;
    private final int quantity;
    private final ItemType type;

    public Item(String name, double price, int quantity, ItemType type) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
    }

    //as per requirement having getter and setter
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public ItemType getType() { return type; }
}
