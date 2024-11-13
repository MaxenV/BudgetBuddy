package com.example.budget_buddy.controller;

import java.util.Date;
import java.util.List;

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
@RequestMapping("/expenses")
public class ExpensesController {
    private final ExpensesService expensesService;

    public ExpensesController(ExpensesService expensesService) {
        this.expensesService = expensesService;
    }

    @PostMapping("/")
    public Expense addExpense(@RequestBody Expense expense) {
        return expensesService.addExpense(expense);
    }

    @GetMapping("/")
    public List<ExpenseDto> getExpenses() {
        return expensesService.getExpensesByUser();
    }

    @GetMapping("/{id}")
    public ExpenseDto getExpenseById(@PathVariable Integer id) {
        return expensesService.getExpenseById(id);
    }

    @GetMapping("/date-range")
    public List<ExpenseDto> getExpensesByDateRange(@RequestParam Date startDate, @RequestParam Date endDate) {
        return expensesService.getExpensesByUserAndDateRange(startDate, endDate);
    }

    @GetMapping("/date")
    public List<ExpenseDto> getExpensesByDate(@RequestParam Date date) {
        return expensesService.getExpensesByUserAndDate(date);
    }

    @GetMapping("/category")
    public List<ExpenseDto> getExpensesByCategory(@RequestParam String category) {
        return expensesService.getExpensesByUserAndCategory(category);
    }
}