package com.example.budget_buddy.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.budget_buddy.model.Expense;
import com.example.budget_buddy.model.User;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Integer> {
    List<Expense> findByUser(User user);
}