package dev.prithwish.expense_tracker.commands;

import dev.prithwish.expense_tracker.domain.Expense;
import dev.prithwish.expense_tracker.service.ExpenseService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.*;

import java.util.LinkedHashMap;
import java.util.List;

@ShellComponent
public class Command {
    private final ExpenseService expenseService;

    public Command(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @ShellMethod(key = "add", value = "Add an expense with a description and amount")
    public String add(@ShellOption String description, @ShellOption double amount) {
        try {
            Long id = expenseService.addExpense(description, amount);
            return "Expense added successfully (ID: " + id + ")";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @ShellMethod(key = "update", value = "Update an expense")
    public String update(
            @ShellOption long id,
            @ShellOption(defaultValue = ShellOption.NULL) String description,
            @ShellOption(defaultValue = ShellOption.NULL) Double amount) {
        try {
            expenseService.updateExpense(id, description, amount);
            return "Expense updated successfully (ID: " + id + ")";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @ShellMethod(key = "delete", value = "Delete an expense")
    public String delete(@ShellOption long id) {
        try {
            expenseService.deleteExpense(id);
            return "Expense deleted successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @ShellMethod(key = "list", value = "View all expenses")
    public Object list(@ShellOption(defaultValue = "0", help = "JAN-DEC can be denoted by 1-12") int month) {
        try {
            List<Expense> expenses = expenseService.getAllExpenses(month);
            if (expenses.isEmpty()) {
                TableModel emptyModel = new ArrayTableModel(new String[][]{
                        {"No tasks found."}
                });

                TableBuilder emptyTableBuilder = new TableBuilder(emptyModel);
                emptyTableBuilder.addFullBorder(BorderStyle.oldschool);
                return emptyTableBuilder.build();
            }
            TableModel tableModel = new BeanListTableModel<>(
                    expenses,
                    new LinkedHashMap<>() {{
                        put("id", "ID");
                        put("description", "Description");
                        put("date", "Date");
                        put("amount", "Amount");
                    }}
            );
            TableBuilder tableBuilder = new TableBuilder(tableModel);
            tableBuilder.addFullBorder(BorderStyle.oldschool);
            tableBuilder.addHeaderBorder(BorderStyle.oldschool);
            return tableBuilder.build();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @ShellMethod(key = "summary", value = "View a summary of all expenses")
    public String summary(@ShellOption(defaultValue = "0", help = "JAN-DEC can be denoted by 1-12") int month) {
        try {
            double totalExpenses = expenseService.getExpensesSummary(month);
            return "Total expenses: $" + totalExpenses;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @ShellMethod(key = "report", value = "Generate an expense report for an interval")
    public String report() {
        try {
            expenseService.generateReport();
            return "Expense report generated successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
