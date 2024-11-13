package com.example.budget_buddy.dto;

import java.util.Date;

import com.example.budget_buddy.model.Expense;

public class ExpenseDto {
    private Integer id;
    private String expenseName;
    private Date expenseDateTime;
    private Date createdAt;
    private Date updatedAt;

    public ExpenseDto(Integer id, String expenseName, Date expenseDateTime, Date createdAt, Date updatedAt) {
        this.id = id;
        this.expenseName = expenseName;
        this.expenseDateTime = expenseDateTime;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ExpenseDto() {
    }

    public ExpenseDto(Expense expense) {
        this.id = expense.getId();
        this.expenseName = expense.getExpenseName();
        this.expenseDateTime = expense.getExpenseDateTime();
        this.createdAt = expense.getCreatedAt();
        this.updatedAt = expense.getUpdatedAt();
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}