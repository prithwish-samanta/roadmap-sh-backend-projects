package dev.prithwish.expense_tracker.service;

import dev.prithwish.expense_tracker.domain.Expense;

import java.text.ParseException;
import java.util.List;

public interface ExpenseService {
    Long addExpense(String description, Double amount);

    void updateExpense(Long id, String description, Double amount);

    void deleteExpense(Long id);

    List<Expense> getAllExpenses(Integer month) throws ParseException;

    Double getExpensesSummary(Integer month) throws ParseException;

    void generateReport();

    Expense getExpenseById(Long id);
}
