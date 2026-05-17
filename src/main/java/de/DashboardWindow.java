package de.buw.se;

import javax.swing.*;
import java.awt.*;

public class DashboardWindow {

    private JFrame frame;

    public DashboardWindow() {

        frame = new JFrame("Meal Tracker System");
        frame.setSize(520, 430);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JLabel titleLabel = new JLabel("MEAL TRACKER SYSTEM", SwingConstants.CENTER);
        titleLabel.setBounds(90, 25, 340, 35);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 70, 170));
        frame.add(titleLabel);

        JLabel welcomeLabel = new JLabel("Welcome, User!");
        welcomeLabel.setBounds(35, 75, 200, 25);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 13));
        frame.add(welcomeLabel);

        JButton manageRecipesButton = createMenuButton("Manage Recipes");
        manageRecipesButton.setBounds(35, 115, 210, 55);
        frame.add(manageRecipesButton);

        JButton budgetButton = createMenuButton("Budget Summary");
        budgetButton.setBounds(270, 115, 210, 55);
        frame.add(budgetButton);

        JButton mealPlanButton = createMenuButton("Meal Plan");
        mealPlanButton.setBounds(35, 185, 210, 55);
        frame.add(mealPlanButton);

        JButton settingsButton = createMenuButton("Settings");
        settingsButton.setBounds(270, 185, 210, 55);
        frame.add(settingsButton);

        JButton shoppingListButton = createMenuButton("Shopping List");
        shoppingListButton.setBounds(35, 255, 210, 55);
        frame.add(shoppingListButton);

        JButton aboutButton = createMenuButton("About");
        aboutButton.setBounds(270, 255, 210, 55);
        frame.add(aboutButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(205, 335, 110, 40);
        exitButton.setFont(new Font("Arial", Font.BOLD, 13));
        exitButton.setBackground(new Color(220, 70, 70));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        frame.add(exitButton);

        manageRecipesButton.addActionListener(e -> {
            ManageRecipesWindow manageRecipesWindow = new ManageRecipesWindow();
            manageRecipesWindow.show();
        });

        mealPlanButton.addActionListener(e -> {
            MealPlanWindow mealPlanWindow = new MealPlanWindow();
            mealPlanWindow.show();
        });

        shoppingListButton.addActionListener(e -> {
            ShoppingListWindow shoppingListWindow = new ShoppingListWindow();
            shoppingListWindow.show();
        });

        budgetButton.addActionListener(e -> {
            BudgetSummaryWindow budgetSummaryWindow = new BudgetSummaryWindow();
            budgetSummaryWindow.show();
        });

        settingsButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Settings feature will be added later.");
        });

        aboutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    frame,
                    "Meal Tracker System\n\n" +
                            "This application helps students manage recipes,\n" +
                            "plan meals, generate shopping lists,\n" +
                            "and estimate food budget."
            );
        });

        exitButton.addActionListener(e -> System.exit(0));
    }

    private JButton createMenuButton(String text) {

        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFocusPainted(false);
        button.setBackground(new Color(245, 245, 245));

        return button;
    }

    public void show() {
        frame.setVisible(true);
    }
}