package de.buw.se;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;

public class ShoppingListWindow {

    private JFrame frame;

    public ShoppingListWindow() {

        frame = new JFrame("Shopping List");
        frame.setSize(600, 450);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JLabel titleLabel = new JLabel("SHOPPING LIST", SwingConstants.CENTER);
        titleLabel.setBounds(170, 20, 250, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 70, 170));
        frame.add(titleLabel);

        JLabel subtitleLabel = new JLabel("(Generated from Weekly Meal Plan)", SwingConstants.CENTER);
        subtitleLabel.setBounds(150, 50, 300, 25);
        frame.add(subtitleLabel);

        String[] columns = {"Ingredient", "Total Quantity", "Unit"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        JTable shoppingTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(shoppingTable);
        scrollPane.setBounds(50, 90, 500, 230);
        frame.add(scrollPane);

        JButton generateButton = new JButton("Generate");
        generateButton.setBounds(190, 350, 100, 35);
        frame.add(generateButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(310, 350, 100, 35);
        frame.add(backButton);

        generateButton.addActionListener(e -> {

            tableModel.setRowCount(0);

            HashMap<String, Double> quantityMap = new HashMap<>();
            HashMap<String, String> unitMap = new HashMap<>();

            for (MealEntry mealEntry : RecipeStorage.getMealPlan()) {

                Recipe recipe = mealEntry.getRecipe();

                for (Ingredient ingredient : recipe.getIngredients()) {

                    String ingredientName = ingredient.getName();

                    double oldQuantity = quantityMap.getOrDefault(ingredientName, 0.0);
                    double newQuantity = oldQuantity + ingredient.getQuantity();

                    quantityMap.put(ingredientName, newQuantity);
                    unitMap.put(ingredientName, ingredient.getUnit());
                }
            }

            for (String ingredientName : quantityMap.keySet()) {

                tableModel.addRow(new Object[]{
                        ingredientName,
                        quantityMap.get(ingredientName),
                        unitMap.get(ingredientName)
                });
            }
        });

        backButton.addActionListener(e -> frame.dispose());
    }

    public void show() {
        frame.setVisible(true);
    }
}