package com.example.budget_buddy;

import javax.swing.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.awt.event.ActionListener;

public class TransactionPanel extends JPanel {
    private JTable transactionTable;
    private JTextField amountField;
    private JTextField descriptionField;
    private JTextField categoryField;
    private JButton addTransactionButton;
    private JButton returnButton;

    @Autowired
private RestTemplate restTemplate;
private DefaultListModel<Transaction> transactionListModel;
private JList<Transaction> transactionList;

    public TransactionPanel(ActionListener returnToMainListener) {
        setLayout(new BorderLayout());

        // Table for displaying transactions
        String[] columnNames = {"Date", "Amount", "Description", "Category"};
        Object[][] data = {}; // Populate this with data from the backend
        transactionTable = new JTable(data, columnNames);
        add(new JScrollPane(transactionTable), BorderLayout.CENTER);

        // Form for adding new transactions
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        formPanel.add(amountField);

        formPanel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        formPanel.add(descriptionField);

        formPanel.add(new JLabel("Category:"));
        categoryField = new JTextField();
        formPanel.add(categoryField);

        addTransactionButton = new JButton("Add Transaction");
        formPanel.add(addTransactionButton);
        
        add(formPanel, BorderLayout.SOUTH);

        // Action listener for adding transactions
        addTransactionButton.addActionListener(e -> handleAddTransaction());

        returnButton = new JButton("Return to Main Panel");
        returnButton.addActionListener(returnToMainListener);

        add(returnButton, BorderLayout.NORTH); // Add it to the top for easy access

        transactionListModel = new DefaultListModel<>();
    transactionList = new JList<>(transactionListModel);
    add(new JScrollPane(transactionList), BorderLayout.CENTER);
    }

    private void handleAddTransaction() {
        String amount = amountField.getText();
        String description = descriptionField.getText();
        String category = categoryField.getText();
        // Logic to save the transaction (e.g., call to a backend service)
        System.out.println("Transaction added: " + amount + ", " + description + ", " + category);
    }

    private void loadTransactions() {
        String url = "http://localhost:8005/api/transactions"; // Define your actual endpoint
        Transaction[] transactions = restTemplate.getForObject(url, Transaction[].class);
        updateTransactionList(transactions);
    }

    private void updateTransactionList(Transaction[] transactions) {
        transactionListModel.clear();
        for (Transaction transaction : transactions) {
            transactionListModel.addElement(transaction); // Assuming transactionListModel is linked to a JList
        }
    }

}