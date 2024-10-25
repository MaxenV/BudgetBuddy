package com.example.budget_buddy;

import java.util.ArrayList;
import java.util.List;

public class TransactionService {
    private final List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getRecentTransactions() {
        return transactions.subList(Math.max(transactions.size() - 5, 0), transactions.size());
    }

    public double getTotalBalance() {
        return transactions.stream()
            .mapToDouble(transaction -> transaction.getType().equals("Income") ? transaction.getAmount() : -transaction.getAmount())
            .sum();
    }
}