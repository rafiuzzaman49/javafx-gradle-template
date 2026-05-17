package de.buw.se;

public class Ingredient {

    private final String name;
    private final double quantity;
    private final String unit;
    private final double pricePerUnit;

    public Ingredient(String name, double quantity, String unit, double pricePerUnit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.pricePerUnit = pricePerUnit;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public double getTotalCost() {
        return quantity * pricePerUnit;
    }
}
