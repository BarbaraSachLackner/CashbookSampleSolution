package org.lecture.cashbook;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Cashbook {

    private final List<Expense> expenses = new ArrayList<>();

    public void add(Expense expense) {
        this.expenses.add(expense);
    }

    public BigDecimal calculateTotalSum() {
        var sums = calculateTotalSumPerCategory();

        return sums.getRent().multiply(new BigDecimal(-1))
                .add(sums.getRestaurant().multiply(new BigDecimal(-1)))
                .add(sums.getSavings());
    }

    public CategorySum calculateTotalSumPerCategory() {
        BigDecimal rent = sum(Category.RENT);
        BigDecimal savings = sum(Category.SAVINGS);
        BigDecimal restaurant = sum(Category.RESTAURANT);

        return new CategorySum(rent, savings, restaurant);
    }

    private BigDecimal sum(Category cat) {
        BigDecimal catSum = BigDecimal.ZERO;
        for (Expense e : expenses) {
            if (e.getCategory().equals(cat)) {
                catSum = catSum.add(e.getAmount());
            }
        }

        return catSum;

        /*
        return expenses.stream()
                .filter(expense -> expense.getCategory().equals(cat))   //get all expenses with given category
                .map(Expense::getAmount)  //get the value of the member per expense
                .reduce(BigDecimal.ZERO, BigDecimal::add);  //sum up all elements per given category

         */
    }

    public void changeLastExpense(String note) {
        Expense lastExpense = getLastExpense();
        if (lastExpense != null) {
            lastExpense.setNote(note);
        }
    }

    public void changeLastExpense(Category category) {
        Expense lastExpense = getLastExpense();
        if (lastExpense != null) {
            lastExpense.setCategory(category);
        }
    }

    public Expense getLastExpense() {
        if (!this.expenses.isEmpty()) {
            return this.expenses.get(expenses.size() - 1);
        }
        return null;

    }


}
