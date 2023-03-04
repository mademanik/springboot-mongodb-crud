package com.blimanik.mongodb.service;

import com.blimanik.mongodb.model.Expense;
import com.blimanik.mongodb.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void addExpense(Expense expense) {
        expenseRepository.insert(expense);
    }

    public void updateExpense(Expense expense) {
        Expense saveExpense = expenseRepository.findById(expense.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Expense by ID %s", expense.getId())));

        saveExpense.setExpenseName(expense.getExpenseName());
        saveExpense.setExpenseCategory(expense.getExpenseCategory());
        saveExpense.setExpenseAmount(expense.getExpenseAmount());

        expenseRepository.save(saveExpense);
    }

    public List<Expense> getAllExpense() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseByName(String name) {
        return expenseRepository.findByName(name).orElseThrow(() -> new RuntimeException(
                String.format("Cannot Find Expense by Name %s, name")
        ));
    }

    public void deleteExpense(String id) {
        expenseRepository.deleteById(id);
    }
}
