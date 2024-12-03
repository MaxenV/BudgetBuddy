package com.example.budget_buddy_android.dto;

import com.example.budget_buddy_android.models.Expense;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ExpenseDto {
    private int id;
    private String expenseName;
    private BigDecimal cost;
    private String category;
    private String description;
    private String expenseDateTime;

    public ExpenseDto(Expense expense) {
        this.id = expense.getId();
        this.expenseName = expense.getExpenseName();
        this.cost = expense.getCost();
        this.category = expense.getCategory();
        this.description = expense.getDescription();
        this.expenseDateTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.getDefault()).format(expense.getExpenseDateTime());
    }

}