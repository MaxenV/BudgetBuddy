package com.example.budget_buddy.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    public Expense addExpense(Expense expense) {
        User user = getCurrentUser();
        expense.setUser(user);
        return expenseRepository.save(expense);
    }

    public List<ExpenseDto> getExpensesByUser() {
        User user = getCurrentUser();
        return expenseRepository.findByUser(user).stream()
                .map(expense -> new ExpenseDto(expense))
                .collect(Collectors.toList());
    }

    public ExpenseDto getExpenseById(Integer id) {
        User user = getCurrentUser();
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
        if (!expense.getUser().equals(user)) {
            throw new RuntimeException("Access denied");
        }
        return new ExpenseDto(expense);
    }

    public List<ExpenseDto> getExpensesByUserAndDateRange(Date startDate, Date endDate) {
        User user = getCurrentUser();
        return expenseRepository.findByUserAndExpenseDateTimeBetween(user, startDate, endDate).stream()
                .map(expense -> new ExpenseDto(expense))
                .collect(Collectors.toList());
    }

    public List<ExpenseDto> getExpensesByUserAndDate(Date date) {
        User user = getCurrentUser();
        return expenseRepository.findByUserAndExpenseDate(user, date).stream()
                .map(expense -> new ExpenseDto(expense))
                .collect(Collectors.toList());
    }

    public List<ExpenseDto> getExpensesByUserAndCategory(String category) {
        User user = getCurrentUser();
        return expenseRepository.findByUserAndCategory(user, category).stream()
                .map(expense -> new ExpenseDto(expense))
                .collect(Collectors.toList());
    }

    public Expense updateExpense(Integer expenseId, Expense updatedExpense) {
        User user = getCurrentUser();
        Expense existingExpense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        if (!existingExpense.getUser().equals(user)) {
            throw new RuntimeException("Access denied");
        }

        existingExpense.setExpenseName(updatedExpense.getExpenseName());
        existingExpense.setCost(updatedExpense.getCost());
        existingExpense.setCategory(updatedExpense.getCategory());
        existingExpense.setDescription(updatedExpense.getDescription());
        existingExpense.setExpenseDateTime(updatedExpense.getExpenseDateTime());

        return expenseRepository.save(existingExpense);
    }

    public void deleteExpense(Integer expenseId) {
        User user = getCurrentUser();
        Expense existingExpense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        if (!existingExpense.getUser().equals(user)) {
            throw new RuntimeException("Access denied");
        }

        expenseRepository.delete(existingExpense);
    }

}
