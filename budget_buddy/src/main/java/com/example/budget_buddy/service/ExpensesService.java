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
import com.example.budget_buddy.repository.UserRepository;

@Service
public class ExpensesService {
    private ExpenseRepository expenseRepository;
    private UserRepository userRepository;

    public ExpensesService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    private void validateExpense(Expense expense) throws IllegalAccessException {
        if (expense.getExpenseName() == null || expense.getExpenseName().isEmpty())
            throw new IllegalAccessException("Expense name cannot be empty");
        if (expense.getCategory() == null || expense.getCategory().isEmpty())
            throw new IllegalAccessException("Expense category cannot be empty");
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    public Expense addExpense(Expense expense) throws IllegalAccessException {
        validateExpense(expense);
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

    public List<ExpenseDto> getExpensesByUser(Integer userId) {
        User currentUser = getCurrentUser();

        if (!currentUser.getIsAdmin()) {
            throw new RuntimeException("Access denied");
        }
        System.out.println("userID: " + userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

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

    public ExpenseDto getExpenseById(Integer userId, Integer id) {
        User currentUser = getCurrentUser();

        if (!currentUser.getIsAdmin()) {
            throw new RuntimeException("Access denied");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

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
