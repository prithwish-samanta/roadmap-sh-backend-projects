package dev.prithwish.expense_tracker.domain;

import com.opencsv.bean.CsvBindByName;

public class ExpenseRecorder {
    @CsvBindByName(column = "Id")
    private Long id;
    @CsvBindByName(column = "Description")
    private String description;
    @CsvBindByName(column = "Amount")
    private Double amount;
    @CsvBindByName(column = "Date")
    private String date;

    public ExpenseRecorder() {
    }

    public ExpenseRecorder(Long id, String description, Double amount, String date) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
