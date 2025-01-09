package dev.prithwish.expense_tracker.service;

import dev.prithwish.expense_tracker.domain.Expense;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static dev.prithwish.expense_tracker.service.Utils.*;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final ReportGenerationService reportGenerationService;

    public List<Expense> expenses = new ArrayList<>();

    public ExpenseServiceImpl(ReportGenerationService reportGenerationService) {
        this.reportGenerationService = reportGenerationService;
    }

    @Override
    public Long addExpense(String description, Double amount) {
        if (amount <= 0) {
            throw new RuntimeException("Amount must be greater than 0");
        }
        Expense expense = new Expense();
        expense.setId(expenses.isEmpty() ? 1 : expenses.getLast().getId() + 1);
        expense.setDescription(description);
        expense.setAmount(amount);
        expense.setDate(getDateString(new Date()));
        expenses.add(expense);
        return expense.getId();
    }

    @Override
    public void updateExpense(Long id, String description, Double amount) {
        if (amount != null && amount <= 0) {
            throw new RuntimeException("Amount must be greater than 0");
        }
        Expense expenseToUpdate = getExpenseById(id);
        if (expenseToUpdate == null) {
            throw new RuntimeException("Expense with id " + id + " not found");
        }
        if (StringUtils.hasText(description)) {
            expenseToUpdate.setDescription(description);
        }
        if (amount != null) {
            expenseToUpdate.setAmount(amount);
        }
    }

    @Override
    public void deleteExpense(Long id) {
        Expense expenseToDelete = getExpenseById(id);
        if (expenseToDelete == null) {
            throw new RuntimeException("Expense with id " + id + " not found");
        }
        expenses.remove(expenseToDelete);
    }

    @Override
    public List<Expense> getAllExpenses(Integer month, Integer year) throws ParseException {
        return filterExpensesByMonth(expenses, month, year);
    }

    @Override
    public Double getExpensesSummary(Integer month, Integer year) throws ParseException {
        return getTotalExpenses(expenses, month, year);
    }

    @Override
    public String generateReport(Integer year) throws Exception {
        if (expenses.isEmpty()) {
            throw new RuntimeException("No expenses found");
        }
        return reportGenerationService.generateReport(expenses, year);
    }

    @Override
    public Expense getExpenseById(Long id) {
        return expenses.stream().filter(expense -> Objects.equals(expense.getId(), id)).findFirst().orElse(null);
    }
}
