package org.example.items;


public class Item {
    private final String name;
    private final double price;
    private final int quantity;
    private final String type;

    public Item(String name, double price, int quantity, String type) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.type = type.toLowerCase();
    }

    //as per requirement having getter and setter
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public String getType() { return type; }
}
