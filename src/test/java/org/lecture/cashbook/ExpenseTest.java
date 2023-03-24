package org.lecture.cashbook;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseTest {


    @Test
    public void outputWithoutNote() {
        LocalDate now = LocalDate.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Expense savings = new Expense(new BigDecimal("250"), now, Category.SAVINGS);
        String toString = "Expense: 250 €, " + Category.SAVINGS + " made on " + formattedDate + ". Note: none.";
        assertEquals(toString, savings.toString());
    }

    @Test
    public void outputWithNote() {
        LocalDate now = LocalDate.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Expense savings = new Expense(new BigDecimal("250"), now, Category.SAVINGS, "with note");
        String toString = "Expense: 250 €, " + Category.SAVINGS + " made on " + formattedDate + ". Note: with note.";
        assertEquals(toString, savings.toString());
    }
}
