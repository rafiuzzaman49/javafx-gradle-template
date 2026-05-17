package de.buw.se;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AddRecipeWindow {

    private JFrame frame;
    private Recipe currentRecipe;
    private DefaultTableModel ingredientTableModel;

    public AddRecipeWindow() {

        frame = new JFrame("Add Recipe");
        frame.setSize(700, 560);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JLabel titleLabel = new JLabel("ADD RECIPE", SwingConstants.CENTER);
        titleLabel.setBounds(220, 20, 250, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 70, 170));
        frame.add(titleLabel);

        JLabel recipeNameLabel = new JLabel("Recipe Name:");
        recipeNameLabel.setBounds(50, 70, 120, 30);
        frame.add(recipeNameLabel);

        JTextField recipeNameField = new JTextField();
        recipeNameField.setBounds(180, 70, 250, 30);
        frame.add(recipeNameField);

        JLabel ingredientNameLabel = new JLabel("Ingredient Name:");
        ingredientNameLabel.setBounds(50, 125, 130, 30);
        frame.add(ingredientNameLabel);

        JTextField ingredientNameField = new JTextField();
        ingredientNameField.setBounds(180, 125, 250, 30);
        frame.add(ingredientNameField);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(50, 170, 120, 30);
        frame.add(quantityLabel);

        JTextField quantityField = new JTextField();
        quantityField.setBounds(180, 170, 250, 30);
        frame.add(quantityField);

        JLabel unitLabel = new JLabel("Unit:");
        unitLabel.setBounds(50, 215, 120, 30);
        frame.add(unitLabel);

        JTextField unitField = new JTextField();
        unitField.setBounds(180, 215, 250, 30);
        frame.add(unitField);

        JLabel priceLabel = new JLabel("Price per Unit:");
        priceLabel.setBounds(50, 260, 120, 30);
        frame.add(priceLabel);

        JTextField priceField = new JTextField();
        priceField.setBounds(180, 260, 250, 30);
        frame.add(priceField);

        JButton addIngredientButton = new JButton("Add Ingredient");
        addIngredientButton.setBounds(460, 170, 150, 35);
        frame.add(addIngredientButton);

        String[] columns = {"Ingredient", "Quantity", "Unit", "Price/Unit", "Total Cost"};
        ingredientTableModel = new DefaultTableModel(columns, 0);

        JTable ingredientTable = new JTable(ingredientTableModel);
        JScrollPane scrollPane = new JScrollPane(ingredientTable);
        scrollPane.setBounds(50, 320, 580, 120);
        frame.add(scrollPane);

        JButton saveRecipeButton = new JButton("Save Recipe");
        saveRecipeButton.setBounds(220, 465, 130, 35);
        frame.add(saveRecipeButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(370, 465, 100, 35);
        frame.add(cancelButton);

        addIngredientButton.addActionListener(e -> {

            try {
                String recipeName = recipeNameField.getText();
                String ingredientName = ingredientNameField.getText();
                double quantity = Double.parseDouble(quantityField.getText());
                String unit = unitField.getText();
                double pricePerUnit = Double.parseDouble(priceField.getText());

                if (recipeName.isEmpty() || ingredientName.isEmpty() || unit.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill all fields.");
                    return;
                }

                if (currentRecipe == null) {
                    currentRecipe = new Recipe(recipeName);
                    recipeNameField.setEditable(false);
                }

                Ingredient ingredient = new Ingredient(
                        ingredientName,
                        quantity,
                        unit,
                        pricePerUnit
                );

                currentRecipe.addIngredient(ingredient);

                ingredientTableModel.addRow(new Object[]{
                        ingredient.getName(),
                        ingredient.getQuantity(),
                        ingredient.getUnit(),
                        "€" + String.format("%.2f", ingredient.getPricePerUnit()),
                        "€" + String.format("%.2f", ingredient.getTotalCost())
                });

                ingredientNameField.setText("");
                quantityField.setText("");
                unitField.setText("");
                priceField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numbers for quantity and price.");
            }
        });

        saveRecipeButton.addActionListener(e -> {

            if (currentRecipe == null || currentRecipe.getIngredients().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please add at least one ingredient before saving.");
                return;
            }

            RecipeStorage.addRecipe(currentRecipe);

            JOptionPane.showMessageDialog(frame, "Recipe saved successfully!");

            frame.dispose();
        });

        cancelButton.addActionListener(e -> frame.dispose());
    }

    public void show() {
        frame.setVisible(true);
    }
}