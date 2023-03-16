package org.lecture.cashbook;

public class CashbookDriverClass {

    private static final Cashbook cashbook = new Cashbook();

    public static void main(String[] args) {
        CashbookService service = new CashbookService(cashbook);
        service.start();
    }
}
