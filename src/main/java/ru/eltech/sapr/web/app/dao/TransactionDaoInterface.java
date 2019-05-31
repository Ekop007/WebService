package ru.eltech.sapr.web.app.dao;

import ru.eltech.sapr.web.app.model.Transaction;

import java.util.List;

public interface TransactionDaoInterface
{
    List<Transaction> getTransactions(long userID);
    Transaction getTransactionByID(long id, long userID);
    Transaction createTransaction(long from, long to, long user, float cash);
    boolean deleteTransactionByID(long id, long userID);
    void update(Transaction transaction, long userID);
}
