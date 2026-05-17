package de.buw.se;

import javax.swing.*;
import java.awt.*;

public class LoginWindow {

    private JFrame frame;

    public LoginWindow() {

        frame = new JFrame("Meal Tracker System - Login");
        frame.setSize(430, 360);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("MEAL TRACKER SYSTEM", SwingConstants.CENTER);
        titleLabel.setBounds(60, 35, 300, 35);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 70, 170));
        frame.add(titleLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(70, 110, 100, 30);
        frame.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(170, 110, 180, 30);
        frame.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(70, 160, 100, 30);
        frame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(170, 160, 180, 30);
        frame.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(120, 230, 90, 35);
        frame.add(loginButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(230, 230, 90, 35);
        frame.add(exitButton);

        loginButton.addActionListener(e -> {

            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter username and password.");
            } else {
                frame.dispose();

                DashboardWindow dashboardWindow = new DashboardWindow();
                dashboardWindow.show();
            }
        });

        exitButton.addActionListener(e -> System.exit(0));
    }

    public void show() {
        frame.setVisible(true);
    }
}