package de.buw.se;

import javax.swing.*;
import java.awt.*;

public class BudgetSummaryWindow {

    private JFrame frame;

    public BudgetSummaryWindow() {

        frame = new JFrame("Budget Summary");
        frame.setSize(430, 350);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JLabel titleLabel = new JLabel("BUDGET SUMMARY", SwingConstants.CENTER);
        titleLabel.setBounds(90, 30, 250, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 70, 170));
        frame.add(titleLabel);

        double totalCost = RecipeStorage.getTotalBudget();
        double budgetLimit = 20.00;
        double remainingBudget = budgetLimit - totalCost;

        JLabel totalCostLabel = new JLabel("Total Estimated Cost:");
        totalCostLabel.setBounds(70, 100, 180, 30);
        frame.add(totalCostLabel);

        JLabel totalCostValue = new JLabel("€" + String.format("%.2f", totalCost));
        totalCostValue.setBounds(260, 100, 100, 30);
        totalCostValue.setForeground(new Color(0, 130, 50));
        frame.add(totalCostValue);

        JLabel budgetLimitLabel = new JLabel("Budget Limit:");
        budgetLimitLabel.setBounds(70, 150, 180, 30);
        frame.add(budgetLimitLabel);

        JLabel budgetLimitValue = new JLabel("€" + String.format("%.2f", budgetLimit));
        budgetLimitValue.setBounds(260, 150, 100, 30);
        budgetLimitValue.setForeground(new Color(0, 70, 170));
        frame.add(budgetLimitValue);

        JLabel remainingLabel = new JLabel("Remaining Budget:");
        remainingLabel.setBounds(70, 200, 180, 30);
        frame.add(remainingLabel);

        JLabel remainingValue = new JLabel("€" + String.format("%.2f", remainingBudget));
        remainingValue.setBounds(260, 200, 100, 30);

        if (remainingBudget >= 0) {
            remainingValue.setForeground(new Color(0, 130, 50));
        } else {
            remainingValue.setForeground(Color.RED);
        }

        frame.add(remainingValue);

        JButton backButton = new JButton("Back");
        backButton.setBounds(160, 270, 100, 35);
        frame.add(backButton);

        backButton.addActionListener(e -> frame.dispose());
    }

    public void show() {
        frame.setVisible(true);
    }
}