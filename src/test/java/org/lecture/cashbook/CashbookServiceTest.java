package org.lecture.cashbook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CashbookServiceTest {

    private final Cashbook cashbook = new Cashbook();

    @BeforeEach
    public void setUp() {
        Expense rentFeb = new Expense(new BigDecimal("300"), LocalDate.of(2023, 2, 1), Category.RENT);
        Expense rent = new Expense(new BigDecimal("300"), LocalDate.of(2023, 3, 29), Category.RENT);
        Expense savings = new Expense(new BigDecimal("250"), LocalDate.of(2023, 3, 29), Category.SAVINGS);
        Expense restaurant = new Expense(new BigDecimal("50"), LocalDate.of(2023, 3, 29), Category.RESTAURANT);

        cashbook.add(rent);
        cashbook.add(rentFeb);
        cashbook.add(savings);
        cashbook.add(restaurant);
    }

    @Test
    public void changeLastNoteViaConsole() {
        ByteArrayInputStream bais = new ByteArrayInputStream("2\nNote to change\n9".getBytes());
        InputStream originalSystemIn = System.in;
        System.setIn(bais);
        CashbookService service = new CashbookService(cashbook);
        service.start();
        Assertions.assertEquals("Note to change", cashbook.getLastExpense().getNote());
        System.setIn(originalSystemIn);

    }
}
