package com.library;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionService {
    private static final double LATE_FEE_PER_DAY = 1.0; // constant late fee rate = $1 for every day overdue

    // creates new transactoion to borrow item starting today, for given number of days
    public static void borrow(int userId, int itemId, int days) {
        LocalDate issued = LocalDate.now(); // sets today's date
        LocalDate due = issued.plusDays(days);  // calculates due date
        // creates new transaction and adds to data list
        Data.transactions.add(
                new Transaction(
                        Data.nextTxId(),
                        userId,
                        itemId,
                        issued,
                        due));
    }

    // handles return of borrowed item today and calculates/returns late fee
    public static double returnItem(int txId) throws Exception {
        // creates iterator to safely remove transaction while iterating
        Iterator<Transaction> it = Data.transactions.iterator();
        // iterates through active transactions
        while (it.hasNext()) {
            Transaction tx = it.next();
            // checks if transaction ID matches
            if (tx.getId() == txId) {
                long overdueDays = ChronoUnit.DAYS.between(tx.getDueDate(), LocalDate.now());   // calculates number of days overdue
                double fee = overdueDays > 0 ? overdueDays * LATE_FEE_PER_DAY : 0;  // calculates overdue fee
                it.remove(); // remove the transaction from active list
                return fee; // returns the fee
            }
        }
        throw new Exception("Transaction ID not found");    // throws error if no matching transaction found
    }

    // lists all active transactions for specific user
    public static List<Transaction> forUser(int userId) {
        // filters transactions to only include ones of given user
        return Data.transactions.stream()
                .filter(tx -> tx.getUserId() == userId)
                .collect(Collectors.toList());
    }

    // lists a copy of all current transactions; used by reports
    public static List<Transaction> all() {
        return List.copyOf(Data.transactions);  // returns copy of transaction list
    }
}
