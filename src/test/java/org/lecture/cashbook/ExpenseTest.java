package org.lecture.cashbook;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseTest {


    @Test
    public void output(){
        Expense savings = new Expense(new BigDecimal("250"), LocalDate.now(), Category.SAVINGS);
        String toString = "Expense: 250 €, " + Category.SAVINGS + " made on 01/03/2022. Note: none";
        assertEquals(toString, savings.toString());
    }

    @Test
    public void outputWithNote(){
        Expense savings = new Expense(new BigDecimal("250"), LocalDate.now(), Category.SAVINGS);
        savings.setNote("with note");
        String toString = "Expense: 250 €, " + Category.SAVINGS + " made on 01/03/2022. Note: with note.";
        assertEquals(toString, savings.toString());
    }
}
