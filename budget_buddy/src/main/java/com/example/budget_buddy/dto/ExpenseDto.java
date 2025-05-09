package com.example.budget_buddy.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.example.budget_buddy.model.Expense;

public class ExpenseDto {
    private Integer id;
    private String expenseName;
    private BigDecimal cost;
    private String category;
    private String description;
    private Date expenseDateTime;

    public ExpenseDto() {
    }

    public ExpenseDto(Expense expense) {
        this.id = expense.getId();
        this.expenseName = expense.getExpenseName();
        this.cost = expense.getCost();
        this.category = expense.getCategory();
        this.description = expense.getDescription();
        this.expenseDateTime = expense.getExpenseDateTime();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public Date getExpenseDateTime() {
        return expenseDateTime;
    }

    public void setExpenseDateTime(Date expenseDateTime) {
        this.expenseDateTime = expenseDateTime;
    }

    public BigDecimal getcost() {
        return cost;
    }

    public void setcost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}