package de.buw.se;

import java.util.ArrayList;

public class Recipe {

    private String recipeName;
    private ArrayList<Ingredient> ingredients;

    public Recipe(String recipeName) {
        this.recipeName = recipeName;
        this.ingredients = new ArrayList<>();
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public double getTotalCost() {

        double total = 0;

        for (Ingredient ingredient : ingredients) {
            total = total + ingredient.getTotalCost();
        }

        return total;
    }
}