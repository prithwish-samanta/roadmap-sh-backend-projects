package dev.prithwish.expense_tracker.service;

import dev.prithwish.expense_tracker.domain.Expense;

import java.util.List;

public interface ReportGenerationService {
    String generateReport(List<Expense> expenses, Integer year) throws Exception;
}
