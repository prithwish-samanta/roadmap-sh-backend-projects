package dev.prithwish.expense_tracker.service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import dev.prithwish.expense_tracker.domain.Expense;
import dev.prithwish.expense_tracker.domain.ExpenseRecorder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CSVDataServiceImpl implements CSVDataService {
    @Value("${expenses.csv.file.path}")
    private String filePath;
    @Value("${expenses.csv.file.name}")
    private String fileName;

    @Override
    public void writeCsv(List<Expense> expenses) throws Exception {
        Writer writer = new FileWriter(filePath + fileName);
        StatefulBeanToCsv<ExpenseRecorder> beanToCsv = new StatefulBeanToCsvBuilder<ExpenseRecorder>(writer).build();
        List<ExpenseRecorder> records = expenses.stream().map(expense -> {
            ExpenseRecorder recorder = new ExpenseRecorder();
            recorder.setId(expense.getId());
            recorder.setAmount(expense.getAmount());
            recorder.setDescription(expense.getDescription());
            recorder.setDate(expense.getDate());
            return recorder;
        }).toList();
        beanToCsv.write(records);
        writer.close();
    }

    @Override
    public List<Expense> readCsv() throws Exception {
        FileReader reader = new FileReader(filePath + fileName);
        List<ExpenseRecorder> records = new CsvToBeanBuilder<ExpenseRecorder>(reader)
                .withType(ExpenseRecorder.class)
                .build()
                .parse();
        return records.stream().map(record -> {
            Expense expense = new Expense();
            expense.setId(record.getId());
            expense.setAmount(record.getAmount());
            expense.setDescription(record.getDescription());
            expense.setDate(record.getDate());
            return expense;
        }).collect(Collectors.toList());
    }
}
