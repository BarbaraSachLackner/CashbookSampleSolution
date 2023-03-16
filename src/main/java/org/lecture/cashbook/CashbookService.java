package org.lecture.cashbook;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class CashbookService {
    private static final Scanner scanner = new Scanner(System.in);
    private static Cashbook cashbook;

    public CashbookService(Cashbook cashbook) {
        CashbookService.cashbook = cashbook;
    }


    public void start() {
        String menu = """
                1 - add Expense
                2 - change Note
                3 - change category
                4 - output all expenses
                5 - output all sums per categories
                6 - output total sum
                Everything else: Exit
                """;


        while (true) {
            System.out.println(menu);
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> addExpense();
                case "2" -> changeLastExpenseNote();
                case "3" -> changeLastExpenseCategory();
                case "4" -> print();
                case "5" -> printCategories();
                case "6" -> printTotalSum();
                default -> System.exit(0);

            }
        }
    }

    private void printTotalSum() {
        System.out.print("Total sum in Cashbook: ");
        System.out.print(cashbook.calculateTotalSum());
        System.out.println();
        System.out.println();
        System.out.println();
    }

    private static void printCategories() {
        var sums = cashbook.calculateTotalSumPerCategory();
        System.out.println("Rent: " + sums.getRent());
        System.out.println("Savings: " + sums.getSavings());
        System.out.println("Restaurant: " + sums.getRestaurant());
        System.out.println();
        System.out.println();
        System.out.println();
    }

    private void print() {
        //cashbook.getExpenses().forEach(System.out::println);
        for (Expense e : cashbook.getExpenses()) {
            System.out.println(e);
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

    private void changeLastExpenseCategory() {
        Expense lastExpense = cashbook.getLastExpense();
        if (lastExpense != null) {
            System.out.println("Current category: ");
            System.out.println(lastExpense.getCategory());
            System.out.println();
            System.out.println("Enter a new Category: ");
            Category cat = getCategory();
            cashbook.changeLastExpense(cat);
        } else {
            System.out.println("Cashbook has no entries yet");
        }
    }

    private void changeLastExpenseNote() {
        Expense lastExpense = cashbook.getLastExpense();
        if (lastExpense != null) {
            System.out.println("Current note: ");
            System.out.println(lastExpense.getNote());
            System.out.println();
            System.out.println("Enter a new note: ");
            String note = scanner.nextLine();
            cashbook.changeLastExpense(note);
        } else {
            System.out.println("Cashbook has no entries yet");
        }
    }

    private void addExpense() {

        System.out.println("Add Expense...");
        System.out.println("Category");
        Category cat = getCategory();
        System.out.println("Amount: ");
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.nextLine();
        LocalDate date = getDate();

        Expense expense = new Expense(amount, date, cat);
        System.out.println("Note (optional): ");
        String note = scanner.nextLine();

        expense.setNote(note);
        cashbook.add(expense);
    }

    private LocalDate getDate() {
        System.out.println("Date (Format yyyy-mm-dd)");
        String dateAsString = scanner.nextLine();
        String[] dateSplit = dateAsString.split("-");
        return LocalDate.of(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]), Integer.parseInt(dateSplit[2]));
    }

    private static Category getCategory() {
        while (true) {
            System.out.println("Category: ");
            String category = """
                    1 - Rent
                    2 - Savings
                    3 - Restaurant
                    """;
            System.out.println(category);
            var input = scanner.nextLine();


            var returnValue = switch (input) {
                case "1" -> Category.RENT;
                case "2" -> Category.SAVINGS;
                case "3" -> Category.RESTAURANT;
                default -> null;
            };

            if (returnValue == null) {
                System.out.println("Must be 1, 2 or 3");
            } else {
                return returnValue;
            }
        }
    }
}
