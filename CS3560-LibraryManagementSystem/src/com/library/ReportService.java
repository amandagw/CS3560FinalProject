package com.library;

import java.time.LocalDate;
import java.util.*;

public class ReportService {
    // returns copy of entire transaction history
    public static List<Transaction> history() {
        return new ArrayList<>(Data.transactions);
    }

    // returns list of only overdue transactions
    public static List<Transaction> overdue() {
        List<Transaction> out = new ArrayList<>();
        LocalDate now = LocalDate.now();
        for (Transaction tx : Data.transactions)
            if (tx.getDueDate().isBefore(now))
                out.add(tx);
        return out; // returns list of overdue transactions
    }
}
