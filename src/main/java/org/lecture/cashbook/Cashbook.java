package org.lecture.cashbook;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Cashbook {

    private final List<Expense> expenses = new ArrayList<>();

    public void add(Expense expense) {
        this.expenses.add(expense);
    }

    public BigDecimal calculateTotalSum() {
        var sums = calculateTotalSumPerCategory();

        return sums.rent().multiply(new BigDecimal(-1))
                .add(sums.savings())
                .add(sums.restaurant().multiply(new BigDecimal(-1)));
    }

    public CategorySum calculateTotalSumPerCategory() {
        BigDecimal rent = reduce(Category.RENT);
        BigDecimal savings = reduce(Category.SAVINGS);
        BigDecimal restaurant = reduce(Category.RESTAURANT);

        return new CategorySum(rent, savings, restaurant);
    }

    private BigDecimal reduce(Category cat) {
        return expenses.stream()
                .filter(expense -> expense.getCategory().equals(cat))   //get all expenses with given category
                .map(Expense::getAmount)  //get the value of the member per expense
                .reduce(BigDecimal.ZERO, BigDecimal::add);  //sum up all elements per given category
    }

    public void changeLastExpense(String note) {
        if (!this.expenses.isEmpty()) {
            this.expenses.get(expenses.size() - 1).setNote(note);
        }
    }

    public void changeLastExpense(Category category) {
        if (!this.expenses.isEmpty()) {
            this.expenses.get(expenses.size() - 1).setCategory(category);
        }
    }

    public Expense getLastExpense() {
        if (!this.expenses.isEmpty()) {
            return this.expenses.get(expenses.size() - 1);
        }
        return null;

    }


}
