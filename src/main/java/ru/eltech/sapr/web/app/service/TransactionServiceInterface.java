package ru.eltech.sapr.web.app.service;

import ru.eltech.sapr.web.app.model.Transaction;

import java.util.List;

public interface TransactionServiceInterface
{
    String SERVICE_NAME = "TransactionService";
    List<Transaction> getTransaction(long user);
    Transaction getTransactionByID(long id, long user);
    Transaction createTransactions(long from, long to, long user, float cash);
    boolean deleteTransactionByID(long id, long user);
    void updateTransaction(Transaction transaction, long user);
}
