package dev.prithwish.expense_tracker.service;

import dev.prithwish.expense_tracker.domain.Expense;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Utils {
    public static String getDateString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(date);
    }

    public static Date getDateFromString(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.parse(dateString);
    }

    public static List<Expense> filterExpensesByMonth(List<Expense> expenses, Integer month, Integer year) throws ParseException {
        if (year == null) {
            year = getYear(new Date());
        }
        List<Expense> list = new ArrayList<>();
        for (Expense expense : expenses) {
            Date date = getDateFromString(expense.getDate());
            if (getYear(date) == year && (month == 0 || date.getMonth() == month - 1)) {
                list.add(expense);
            }
        }
        return list;
    }

    public static Double getTotalExpenses(List<Expense> expenses, Integer month, Integer year) throws ParseException {
        if (year == null) {
            year = getYear(new Date());
        }
        double total = 0d;
        for (Expense expense : expenses) {
            Date date = getDateFromString(expense.getDate());
            if (getYear(date) == year && (month == 0 || date.getMonth() == month - 1)) {
                total += expense.getAmount();
            }
        }
        return total;
    }

    private static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }
}
