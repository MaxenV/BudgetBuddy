package com.example.budget_buddy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.budget_buddy.model.Expense;
import com.example.budget_buddy.model.User;
import com.example.budget_buddy.repository.ExpenseRepository;

@Service
public class ExpensesService {
    private ExpenseRepository expenseRepository;

    public ExpensesService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense addExpense(User user, Expense expense) {
        expense.setUser(user);
        return expenseRepository.save(expense);
    }

    public List<Expense> getExpensesByUser(User user) {
        return expenseRepository.findByUser(user);
    }
}
