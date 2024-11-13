package com.example.budget_buddy.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.budget_buddy.model.Expense;
import com.example.budget_buddy.model.User;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Integer> {
    List<Expense> findByUser(User user);

    List<Expense> findByUserAndExpenseDateTimeBetween(User user, Date startDate, Date endDate);

    List<Expense> findByUserAndExpenseDateTime(User user, Date date);

    List<Expense> findByUserAndCategory(User user, String category);

}