package de.buw.se;

import java.util.ArrayList;

public class RecipeStorage {

    private static ArrayList<Recipe> recipes = new ArrayList<>();
    private static ArrayList<MealEntry> mealPlan = new ArrayList<>();

    public static void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public static ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public static void deleteRecipe(int index) {
        if (index >= 0 && index < recipes.size()) {
            recipes.remove(index);
        }
    }

    public static void addToMealPlan(MealEntry mealEntry) {
        mealPlan.add(mealEntry);
    }

    public static ArrayList<MealEntry> getMealPlan() {
        return mealPlan;
    }

    public static void clearMealPlan() {
        mealPlan.clear();
    }

    public static double getTotalBudget() {

        double total = 0;

        for (MealEntry mealEntry : mealPlan) {
            total = total + mealEntry.getRecipe().getTotalCost();
        }

        return total;
    }
}