package dev.prithwish.expense_tracker.service;

import dev.prithwish.expense_tracker.domain.Expense;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static dev.prithwish.expense_tracker.service.Utils.filterExpensesByMonth;

@Service
public class ReportGenerationServiceImpl implements ReportGenerationService {
    @Value("${report.download.dir}")
    private String downloadDir;

    private XSSFSheet sheet;

    public ReportGenerationServiceImpl() {
    }

    @Override
    public String generateReport(List<Expense> expenses, Integer year) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        writeHeader(workbook, "Expenses");
        write(workbook, filterExpensesByMonth(expenses, 0, year));
        writeHeader(workbook, "JAN");
        write(workbook, filterExpensesByMonth(expenses, 1, year));
        writeHeader(workbook, "FEB");
        write(workbook, filterExpensesByMonth(expenses, 2, year));
        writeHeader(workbook, "MAR");
        write(workbook, filterExpensesByMonth(expenses, 3, year));
        writeHeader(workbook, "APRIL");
        write(workbook, filterExpensesByMonth(expenses, 4, year));
        writeHeader(workbook, "MAY");
        write(workbook, filterExpensesByMonth(expenses, 5, year));
        writeHeader(workbook, "JUN");
        write(workbook, filterExpensesByMonth(expenses, 6, year));
        writeHeader(workbook, "JUL");
        write(workbook, filterExpensesByMonth(expenses, 7, year));
        writeHeader(workbook, "AUG");
        write(workbook, filterExpensesByMonth(expenses, 8, year));
        writeHeader(workbook, "SEPT");
        write(workbook, filterExpensesByMonth(expenses, 9, year));
        writeHeader(workbook, "OCT");
        write(workbook, filterExpensesByMonth(expenses, 10, year));
        writeHeader(workbook, "NOV");
        write(workbook, filterExpensesByMonth(expenses, 11, year));
        writeHeader(workbook, "DEC");
        write(workbook, filterExpensesByMonth(expenses, 12, year));

        String filePath;
        if (year == null) {
            filePath = downloadDir + "/expenses-" + LocalDate.now().getYear() + ".xlsx";
        } else {
            filePath = downloadDir + "/expenses-" + year + ".xlsx";
        }
        if (new File(filePath).exists()) {
            filePath = downloadDir + "/expenses-" + UUID.randomUUID() + ".xlsx";
        }
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        }

        return filePath;
    }

    private void writeHeader(XSSFWorkbook workbook, String sheetName) {
        sheet = workbook.createSheet(sheetName);
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "ID", style);
        createCell(row, 1, "Date", style);
        createCell(row, 2, "Description", style);
        createCell(row, 3, "Amount", style);
    }

    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Double) {
            cell.setCellValue((Double) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }

    private void write(XSSFWorkbook workbook, List<Expense> expenses) {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (Expense record : expenses) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, record.getId(), style);
            createCell(row, columnCount++, record.getDate(), style);
            createCell(row, columnCount++, record.getDescription(), style);
            createCell(row, columnCount, record.getAmount(), style);
        }
        //add the total row
        if (!expenses.isEmpty()) {
            Row row = sheet.createRow(rowCount);
            createCell(row, 0, "TOTAL", style);
            double totalExpenses = expenses.stream().mapToDouble(Expense::getAmount).sum();
            createCell(row, 3, totalExpenses, style);
        }
    }
}
