package com.example.budget_buddy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainPanel extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private TransactionPanel transactionPanel;
    private BudgetPanel budgetPanel;

    public MainPanel() {
        setTitle("BudgetBuddy - Main Panel");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        ActionListener returnToMainListener = e -> cardLayout.show(mainPanel, "Home");

        // Initialize sub-panels
        JPanel homePanel = createHomePanel();
        transactionPanel = new TransactionPanel(returnToMainListener);
        budgetPanel = new BudgetPanel(returnToMainListener);

        // Add panels to the card layout
        mainPanel.add(homePanel, "Home");
        mainPanel.add(transactionPanel, "Transactions");
        mainPanel.add(budgetPanel, "Budgets");

        add(mainPanel);
    }

    private JPanel createHomePanel() {
        JPanel homePanel = new JPanel(new BorderLayout());

        // Header
        JLabel headerLabel = new JLabel("Welcome to BudgetBuddy", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        homePanel.add(headerLabel, BorderLayout.NORTH);

        // Buttons to navigate
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JButton manageTransactionsButton = new JButton("Manage Transactions");
        JButton manageBudgetsButton = new JButton("Manage Budgets");
        JButton viewExpensesButton = new JButton("View Expenses");
        JButton financialSummaryButton = new JButton("Financial Summary");

        buttonPanel.add(manageTransactionsButton);
        buttonPanel.add(manageBudgetsButton);
        buttonPanel.add(viewExpensesButton);
        buttonPanel.add(financialSummaryButton);

        homePanel.add(buttonPanel, BorderLayout.CENTER);

        // Action listeners to switch panels
        manageTransactionsButton.addActionListener(e -> cardLayout.show(mainPanel, "Transactions"));
        manageBudgetsButton.addActionListener(e -> cardLayout.show(mainPanel, "Budgets"));
        viewExpensesButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "View Expenses clicked"));
        financialSummaryButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Financial Summary clicked"));

        return homePanel;
    }
}