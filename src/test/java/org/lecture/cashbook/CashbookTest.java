package org.lecture.cashbook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CashbookTest {

    @Test
    public void calculateTotalSum() {
        Cashbook cashbook = new Cashbook();

        Expense rent = new Expense(new BigDecimal("300"), LocalDate.now(), Category.RENT);
        Expense rentFeb = new Expense(new BigDecimal("300"), LocalDate.of(2022, 2, 1), Category.RENT);
        Expense savings = new Expense(new BigDecimal("250"), LocalDate.now(), Category.SAVINGS);
        Expense restaurant = new Expense(new BigDecimal("50"), LocalDate.now(), Category.RESTAURANT);

        cashbook.add(rent);
        cashbook.add(rentFeb);
        cashbook.add(savings);
        cashbook.add(restaurant);

        Assertions.assertEquals(new BigDecimal(-400), cashbook.calculateTotalSum());
    }

    @Test
    public void calculateTotalSumPerCategory() {
        Cashbook cashbook = new Cashbook();

        Expense rent = new Expense(new BigDecimal("300"), LocalDate.now(), Category.RENT);
        Expense rentFeb = new Expense(new BigDecimal("300"), LocalDate.of(2022, 2, 1), Category.RENT);
        Expense savings = new Expense(new BigDecimal("250"), LocalDate.now(), Category.SAVINGS);
        Expense restaurant = new Expense(new BigDecimal("50"), LocalDate.now(), Category.RESTAURANT);

        cashbook.add(rent);
        cashbook.add(rentFeb);
        cashbook.add(savings);
        cashbook.add(restaurant);

        CategorySum sum = new CategorySum(new BigDecimal(600), new BigDecimal(250), new BigDecimal(50));
        Assertions.assertEquals(sum, cashbook.calculateTotalSumPerCategory());
    }


    @Test
    public void changeLastNote() {
        Cashbook cashbook = new Cashbook();

        LocalDate now = LocalDate.now();
        Expense rent = new Expense(new BigDecimal("300"), now, Category.RENT);
        Expense rentFeb = new Expense(new BigDecimal("300"), LocalDate.of(2022, 2, 1), Category.RENT);
        Expense savings = new Expense(new BigDecimal("250"), now, Category.SAVINGS);
        Expense restaurant = new Expense(new BigDecimal("50"), now, Category.RESTAURANT);
        restaurant.setNote("Note to change");

        cashbook.add(rent);
        cashbook.add(rentFeb);
        cashbook.add(savings);
        cashbook.add(restaurant);

        cashbook.changeLastExpense("Changed note");

        Assertions.assertEquals("Changed note", cashbook.getLastExpense().getNote());
    }

    @Test
    public void changeLastCategory() {
        Cashbook cashbook = new Cashbook();

        LocalDate now = LocalDate.now();
        Expense rent = new Expense(new BigDecimal("300"), now, Category.RENT);
        Expense rentFeb = new Expense(new BigDecimal("300"), LocalDate.of(2022, 2, 1), Category.RENT);
        Expense savings = new Expense(new BigDecimal("250"), now, Category.SAVINGS);
        Expense restaurant = new Expense(new BigDecimal("50"), LocalDate.of(2022, 2, 15), Category.RESTAURANT);

        cashbook.add(rent);
        cashbook.add(rentFeb);
        cashbook.add(savings);
        cashbook.add(restaurant);

        cashbook.changeLastExpense(Category.SAVINGS);

        Expense lastExpense = cashbook.getLastExpense();
        Assertions.assertEquals(Category.SAVINGS, lastExpense.getCategory());
        Assertions.assertEquals(new BigDecimal(50), lastExpense.getAmount());
        Assertions.assertEquals(LocalDate.of(2022, 2, 15), lastExpense.getDate());
    }
}
