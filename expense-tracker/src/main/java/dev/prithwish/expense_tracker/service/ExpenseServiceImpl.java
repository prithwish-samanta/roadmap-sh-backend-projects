package dev.prithwish.expense_tracker.service;

import dev.prithwish.expense_tracker.domain.Expense;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    List<Expense> expenses = new ArrayList<>();

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
    public List<Expense> getAllExpenses(Integer month) throws ParseException {
        if (month == 0) {
            return expenses;
        }
        List<Expense> list = new ArrayList<>();
        for (Expense expense : expenses) {
            Date currentDate = getDateFromString(expense.getDate());
            if (currentDate.getMonth() == month - 1) {
                list.add(expense);
            }
        }
        return list;
    }

    @Override
    public Double getExpensesSummary(Integer month) throws ParseException {
        if (month == 0) {
            return expenses.stream().mapToDouble(Expense::getAmount).sum();
        }
        double total = 0d;
        for (Expense expense : expenses) {
            Date currentDate = getDateFromString(expense.getDate());
            if (currentDate.getMonth() == month - 1) {
                total += expense.getAmount();
            }
        }
        return total;
    }

    @Override
    public void generateReport() {
        // TODO: implement a logic for creating a excel report
    }

    @Override
    public Expense getExpenseById(Long id) {
        return expenses.stream().filter(expense -> Objects.equals(expense.getId(), id)).findFirst().orElse(null);
    }

    private String getDateString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(date);
    }

    private Date getDateFromString(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.parse(dateString);
    }
}
