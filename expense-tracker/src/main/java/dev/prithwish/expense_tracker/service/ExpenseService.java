package dev.prithwish.expense_tracker.service;

import dev.prithwish.expense_tracker.domain.Expense;

import java.util.List;

public interface ExpenseService {
    Long addExpense(String description, Double amount) throws Exception;

    void updateExpense(Long id, String description, Double amount) throws Exception;

    void deleteExpense(Long id) throws Exception;

    List<Expense> getAllExpenses(Integer month, Integer year) throws Exception;

    Double getExpensesSummary(Integer month, Integer year) throws Exception;

    String generateReport(Integer year) throws Exception;
}
