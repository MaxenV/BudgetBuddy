package com.example.budget_buddy.controller;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.budget_buddy.dto.ExpenseDto;
import com.example.budget_buddy.model.Expense;
import com.example.budget_buddy.service.ExpensesService;

@RestController
@RequestMapping("/expense")
public class ExpensesController {
    private final ExpensesService expensesService;

    public ExpensesController(ExpensesService expensesService) {
        this.expensesService = expensesService;
    }

    @PostMapping("/add")
    public Expense addExpense(@RequestBody Expense expense) {
        return expensesService.addExpense(expense);
    }

    @GetMapping("/all")
    public List<ExpenseDto> getExpenses() {
        return expensesService.getExpensesByUser();
    }

    @GetMapping("/{id}")
    public ExpenseDto getExpenseById(@PathVariable Integer id) {
        return expensesService.getExpenseById(id);
    }

    @GetMapping("/date-range")
    public List<ExpenseDto> getExpensesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date endDate) {
        return expensesService.getExpensesByUserAndDateRange(startDate, endDate);
    }

    @GetMapping("/day")
    public List<ExpenseDto> getExpensesByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        return expensesService.getExpensesByUserAndDate(date);
    }

    @GetMapping("/category")
    public List<ExpenseDto> getExpensesByCategory(@RequestParam String category) {
        return expensesService.getExpensesByUserAndCategory(category);
    }
}