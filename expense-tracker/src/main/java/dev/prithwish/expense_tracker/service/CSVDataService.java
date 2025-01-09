package dev.prithwish.expense_tracker.service;

import dev.prithwish.expense_tracker.domain.Expense;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface CSVDataService {
    void writeCsv(List<Expense> expenses) throws Exception;

    List<Expense> readCsv() throws Exception;
}
