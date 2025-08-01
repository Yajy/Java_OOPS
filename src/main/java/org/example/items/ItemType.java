package org.example.items;

public enum ItemType {
    RAW,
    MANUFACTURED,
    IMPORTED;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
