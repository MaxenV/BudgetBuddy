package com.example.budget_buddy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BudgetPanel extends JPanel {
    private JButton returnButton;
    private JTable budgetTable;
    private JTextField budgetAmountField;
    private JTextField budgetCategoryField;
    private JButton addBudgetButton;

    public BudgetPanel(ActionListener returnToMainListener) {
        setLayout(new BorderLayout());

        // Table for displaying budgets
        String[] columnNames = {"Category", "Amount"};
        Object[][] data = {}; // Populate with backend data
        budgetTable = new JTable(data, columnNames);
        add(new JScrollPane(budgetTable), BorderLayout.CENTER);

        // Form for adding new budgets
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.add(new JLabel("Budget Amount:"));
        budgetAmountField = new JTextField();
        formPanel.add(budgetAmountField);

        formPanel.add(new JLabel("Category:"));
        budgetCategoryField = new JTextField();
        formPanel.add(budgetCategoryField);

        addBudgetButton = new JButton("Add Budget");
        formPanel.add(addBudgetButton);

        add(formPanel, BorderLayout.SOUTH);

        // Action listener for adding budgets
        addBudgetButton.addActionListener(e -> handleAddBudget());

        returnButton = new JButton("Return to Main Panel");
        returnButton.addActionListener(returnToMainListener);

        add(returnButton, BorderLayout.NORTH); // Add it to the top for easy access
    }

    private void handleAddBudget() {
        String amount = budgetAmountField.getText();
        String category = budgetCategoryField.getText();
        // Logic to save the budget (e.g., call to a backend service)
        System.out.println("Budget added: " + amount + ", " + category);
    }
}