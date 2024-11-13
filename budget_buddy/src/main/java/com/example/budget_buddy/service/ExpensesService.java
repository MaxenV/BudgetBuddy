package com.example.budget_buddy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.budget_buddy.dto.ExpenseDto;
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

    public List<ExpenseDto> getExpensesByUser(User user) {
        return expenseRepository.findByUser(user).stream()
                .map(expense -> new ExpenseDto(expense))
                .collect(Collectors.toList());
    }
}
