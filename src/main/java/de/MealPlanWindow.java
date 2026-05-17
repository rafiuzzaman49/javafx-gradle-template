package de.buw.se;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MealPlanWindow {

    private JFrame frame;
    private DefaultTableModel tableModel;
    private JComboBox<String> recipeComboBox;
    private JComboBox<String> dayComboBox;
    private JComboBox<String> mealTypeComboBox;

    public MealPlanWindow() {

        frame = new JFrame("Meal Plan");
        frame.setSize(780, 520);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JLabel titleLabel = new JLabel("WEEKLY MEAL PLAN", SwingConstants.CENTER);
        titleLabel.setBounds(230, 20, 300, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 70, 170));
        frame.add(titleLabel);

        JLabel dayLabel = new JLabel("Select Day:");
        dayLabel.setBounds(50, 80, 120, 30);
        frame.add(dayLabel);

        String[] days = {
                "Monday", "Tuesday", "Wednesday", "Thursday",
                "Friday", "Saturday", "Sunday"
        };

        dayComboBox = new JComboBox<>(days);
        dayComboBox.setBounds(170, 80, 180, 30);
        frame.add(dayComboBox);

        JLabel mealTypeLabel = new JLabel("Meal Type:");
        mealTypeLabel.setBounds(390, 80, 100, 30);
        frame.add(mealTypeLabel);

        String[] mealTypes = {"Breakfast", "Lunch", "Dinner"};

        mealTypeComboBox = new JComboBox<>(mealTypes);
        mealTypeComboBox.setBounds(490, 80, 180, 30);
        frame.add(mealTypeComboBox);

        JLabel recipeLabel = new JLabel("Select Recipe:");
        recipeLabel.setBounds(50, 130, 120, 30);
        frame.add(recipeLabel);

        recipeComboBox = new JComboBox<>();
        recipeComboBox.setBounds(170, 130, 300, 30);
        frame.add(recipeComboBox);

        JButton addMealButton = new JButton("Add Meal");
        addMealButton.setBounds(500, 130, 140, 30);
        frame.add(addMealButton);

        String[] columns = {"#", "Day", "Meal Type", "Recipe Name", "Ingredients", "Cost"};
        tableModel = new DefaultTableModel(columns, 0);

        JTable mealTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(mealTable);
        scrollPane.setBounds(50, 190, 680, 220);
        frame.add(scrollPane);

        JButton clearButton = new JButton("Clear Plan");
        clearButton.setBounds(230, 435, 130, 35);
        frame.add(clearButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(390, 435, 130, 35);
        frame.add(backButton);

        loadRecipeNames();
        loadMealPlanTable();

        addMealButton.addActionListener(e -> {

            int selectedIndex = recipeComboBox.getSelectedIndex();

            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(frame, "No recipe selected. Please add a recipe first.");
                return;
            }

            String selectedDay = (String) dayComboBox.getSelectedItem();
            String selectedMealType = (String) mealTypeComboBox.getSelectedItem();

            Recipe selectedRecipe = RecipeStorage.getRecipes().get(selectedIndex);

            MealEntry mealEntry = new MealEntry(
                    selectedDay,
                    selectedMealType,
                    selectedRecipe
            );

            RecipeStorage.addToMealPlan(mealEntry);

            loadMealPlanTable();
        });

        clearButton.addActionListener(e -> {
            RecipeStorage.clearMealPlan();
            loadMealPlanTable();
        });

        backButton.addActionListener(e -> frame.dispose());
    }

    private void loadRecipeNames() {

        recipeComboBox.removeAllItems();

        for (Recipe recipe : RecipeStorage.getRecipes()) {
            recipeComboBox.addItem(recipe.getRecipeName());
        }
    }

    private void loadMealPlanTable() {

        tableModel.setRowCount(0);

        int count = 1;

        for (MealEntry mealEntry : RecipeStorage.getMealPlan()) {

            Recipe recipe = mealEntry.getRecipe();

            tableModel.addRow(new Object[]{
                    count,
                    mealEntry.getDay(),
                    mealEntry.getMealType(),
                    recipe.getRecipeName(),
                    recipe.getIngredients().size(),
                    "€" + String.format("%.2f", recipe.getTotalCost())
            });

            count++;
        }
    }

    public void show() {
        frame.setVisible(true);
    }
}