package com.example.budget_buddy.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.budget_buddy.dto.ExpenseDto;
import com.example.budget_buddy.model.Expense;
import com.example.budget_buddy.model.User;
import com.example.budget_buddy.service.ExpensesService;

@RestController
@RequestMapping("/expenses")
public class ExpensesController {
    private final ExpensesService expensesService;

    public ExpensesController(ExpensesService expensesService) {
        this.expensesService = expensesService;
    }

    @PostMapping("/")
    public Expense addExpense(@RequestBody Expense expense) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return expensesService.addExpense(currentUser, expense);
    }

    @GetMapping("/")
    public List<ExpenseDto> getExpenses() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return expensesService.getExpensesByUser(currentUser);
    }
}