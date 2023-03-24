package org.lecture.cashbook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CashbookTest {

    private final Cashbook cashbook = new Cashbook();

    @BeforeEach
    public void setUp() {
        Expense rent = new Expense(new BigDecimal("300"), LocalDate.now(), Category.RENT);
        Expense rentFeb = new Expense(new BigDecimal("300"), LocalDate.of(2022, 2, 1), Category.RENT);
        Expense savings = new Expense(new BigDecimal("250"), LocalDate.now(), Category.SAVINGS);
        Expense restaurant = new Expense(new BigDecimal("50"), LocalDate.now(), Category.RESTAURANT);

        cashbook.add(rent);
        cashbook.add(rentFeb);
        cashbook.add(savings);
        cashbook.add(restaurant);
    }

    @Test
    public void calculateTotalSum() {
        Assertions.assertEquals(new BigDecimal(-400), cashbook.calculateTotalSum());
    }

    @Test
    public void calculateTotalSumPerCategory() {
        CategorySum sum = new CategorySum(new BigDecimal(600), new BigDecimal(250), new BigDecimal(50));
        Assertions.assertEquals(sum, cashbook.calculateTotalSumPerCategory());
    }


    @Test
    public void changeLastNote() {
        Expense restaurant = new Expense(new BigDecimal("100"), LocalDate.now(), Category.RESTAURANT, "Note to change");
        cashbook.add(restaurant);
        cashbook.changeLastExpense("Changed note");
        Assertions.assertEquals("Changed note", cashbook.getLastExpense().getNote());
    }

    @Test
    public void changeLastCategory() {
        LocalDate date = LocalDate.of(2023, 3, 1);
        Expense wrongCategory = new Expense(new BigDecimal("100"), date, Category.RESTAURANT);
        cashbook.add(wrongCategory);
        cashbook.changeLastExpense(Category.SAVINGS);

        Expense lastExpense = cashbook.getLastExpense();
        Assertions.assertEquals(Category.SAVINGS, lastExpense.getCategory());
        Assertions.assertEquals(new BigDecimal(100), lastExpense.getAmount());
        Assertions.assertEquals(date, lastExpense.getDate());
    }
}
