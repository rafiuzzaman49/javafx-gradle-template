package de.buw.se;

public class MealEntry {

    private String day;
    private String mealType;
    private Recipe recipe;

    public MealEntry(String day, String mealType, Recipe recipe) {
        this.day = day;
        this.mealType = mealType;
        this.recipe = recipe;
    }

    public String getDay() {
        return day;
    }

    public String getMealType() {
        return mealType;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}