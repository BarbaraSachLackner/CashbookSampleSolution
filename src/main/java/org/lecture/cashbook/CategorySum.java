package org.lecture.cashbook;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CategorySum {

    private final BigDecimal rent;
    private final BigDecimal savings;
    private final BigDecimal restaurant;

    public CategorySum(BigDecimal rent, BigDecimal savings, BigDecimal restaurant) {
        this.rent = rent;
        this.savings = savings;
        this.restaurant = restaurant;
    }
}
