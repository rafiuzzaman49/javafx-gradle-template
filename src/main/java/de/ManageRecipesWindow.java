package de.buw.se;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ManageRecipesWindow {

    private JFrame frame;
    private JTable recipeTable;
    private DefaultTableModel tableModel;

    public ManageRecipesWindow() {

        frame = new JFrame("Manage Recipes");
        frame.setSize(700, 450);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JLabel titleLabel = new JLabel("MANAGE RECIPES", SwingConstants.CENTER);
        titleLabel.setBounds(220, 20, 250, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 70, 170));
        frame.add(titleLabel);

        String[] columns = {"#", "Recipe Name", "Number of Ingredients", "Total Cost"};

        tableModel = new DefaultTableModel(columns, 0);
        recipeTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(recipeTable);
        scrollPane.setBounds(30, 70, 500, 280);
        frame.add(scrollPane);

        JButton addRecipeButton = new JButton("Add Recipe");
        addRecipeButton.setBounds(550, 90, 120, 35);
        frame.add(addRecipeButton);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBounds(550, 140, 120, 35);
        frame.add(refreshButton);

        JButton detailsButton = new JButton("View Details");
        detailsButton.setBounds(550, 190, 120, 35);
        frame.add(detailsButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(550, 240, 120, 35);
        deleteButton.setBackground(new Color(220, 70, 70));
        deleteButton.setForeground(Color.WHITE);
        frame.add(deleteButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(550, 315, 120, 35);
        frame.add(backButton);

        loadRecipes();

        addRecipeButton.addActionListener(e -> {
            AddRecipeWindow addRecipeWindow = new AddRecipeWindow();
            addRecipeWindow.show();
        });

        refreshButton.addActionListener(e -> loadRecipes());

        detailsButton.addActionListener(e -> {

            int selectedRow = recipeTable.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a recipe.");
                return;
            }

            Recipe recipe = RecipeStorage.getRecipes().get(selectedRow);

            StringBuilder message = new StringBuilder();
            message.append("Recipe: ").append(recipe.getRecipeName()).append("\n\n");
            message.append("Ingredients:\n");

            for (Ingredient ingredient : recipe.getIngredients()) {
                message.append("- ")
                        .append(ingredient.getName())
                        .append(" | ")
                        .append(ingredient.getQuantity())
                        .append(" ")
                        .append(ingredient.getUnit())
                        .append(" | €")
                        .append(String.format("%.2f", ingredient.getPricePerUnit()))
                        .append("\n");
            }

            message.append("\nTotal Cost: €")
                    .append(String.format("%.2f", recipe.getTotalCost()));

            JOptionPane.showMessageDialog(frame, message.toString());
        });

        deleteButton.addActionListener(e -> {

            int selectedRow = recipeTable.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a recipe to delete.");
                return;
            }

            RecipeStorage.deleteRecipe(selectedRow);
            loadRecipes();
        });

        backButton.addActionListener(e -> frame.dispose());
    }

    private void loadRecipes() {

        tableModel.setRowCount(0);

        int count = 1;

        for (Recipe recipe : RecipeStorage.getRecipes()) {

            tableModel.addRow(new Object[]{
                    count,
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