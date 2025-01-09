package dev.prithwish.expense_tracker.service;

import dev.prithwish.expense_tracker.domain.Expense;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static dev.prithwish.expense_tracker.service.Utils.*;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final ReportGenerationService reportGenerationService;
    private final CSVDataService csvDataService;

    public ExpenseServiceImpl(ReportGenerationService reportGenerationService, CSVDataService csvDataService) {
        this.reportGenerationService = reportGenerationService;
        this.csvDataService = csvDataService;
    }

    @Override
    public Long addExpense(String description, Double amount) throws Exception {
        if (amount <= 0) {
            throw new RuntimeException("Amount must be greater than 0");
        }
        List<Expense> expenses = csvDataService.readCsv();
        Expense expense = new Expense();
        expense.setId(expenses.isEmpty() ? 1 : expenses.getLast().getId() + 1);
        expense.setDescription(description);
        expense.setAmount(amount);
        expense.setDate(getDateString(new Date()));
        expenses.add(expense);
        csvDataService.writeCsv(expenses);
        return expense.getId();
    }

    @Override
    public void updateExpense(Long id, String description, Double amount) throws Exception {
        if (amount != null && amount <= 0) {
            throw new RuntimeException("Amount must be greater than 0");
        }
        List<Expense> expenses = csvDataService.readCsv();
        Expense expenseToUpdate = expenses.stream()
                .filter(expense -> Objects.equals(expense.getId(), id))
                .findFirst().orElse(null);
        if (expenseToUpdate == null) {
            throw new RuntimeException("Expense with id " + id + " not found");
        }
        if (StringUtils.hasText(description)) {
            expenseToUpdate.setDescription(description);
        }
        if (amount != null) {
            expenseToUpdate.setAmount(amount);
        }
        csvDataService.writeCsv(expenses);
    }

    @Override
    public void deleteExpense(Long id) throws Exception {
        List<Expense> expenses = csvDataService.readCsv();
        Expense expenseToDelete = expenses.stream()
                .filter(expense -> Objects.equals(expense.getId(), id))
                .findFirst().orElse(null);
        if (expenseToDelete == null) {
            throw new RuntimeException("Expense with id " + id + " not found");
        }
        expenses.remove(expenseToDelete);
        csvDataService.writeCsv(expenses);
    }

    @Override
    public List<Expense> getAllExpenses(Integer month, Integer year) throws Exception {
        List<Expense> expenses = csvDataService.readCsv();
        return filterExpensesByYearAndMonth(expenses, month, year);
    }

    @Override
    public Double getExpensesSummary(Integer month, Integer year) throws Exception {
        List<Expense> expenses = csvDataService.readCsv();
        return getTotalExpenses(expenses, month, year);
    }

    @Override
    public String generateReport(Integer year) throws Exception {
        List<Expense> expenses = filterExpensesByYearAndMonth(csvDataService.readCsv(), 0, year);
        if (expenses.isEmpty()) {
            throw new RuntimeException("No expenses found");
        }
        return reportGenerationService.generateReport(expenses, year);
    }
}
