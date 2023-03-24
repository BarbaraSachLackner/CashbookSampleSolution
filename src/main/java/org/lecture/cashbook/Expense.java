package org.lecture.cashbook;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@EqualsAndHashCode
public class Expense {

    private final BigDecimal amount;
    private final LocalDate date;
    private Category category;
    private String note;

    public Expense(BigDecimal amount, LocalDate date, Category category) {
       this(amount, date,category, "");
    }

    public Expense(BigDecimal amount, LocalDate date, Category category, String note) {
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.note = note;
    }

    @Override
    public String toString() {
        String noteOutput = note;
        if (noteOutput.isBlank()) {
            noteOutput = "none";
        }
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return String.format("Expense: %s €, %s made on %s. Note: %s.", amount.toString() , category, formattedDate, noteOutput);

    }
}
