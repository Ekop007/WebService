package ru.eltech.sapr.web.app.service;

import ru.eltech.sapr.web.app.dao.TransactionDaoInterface;
import ru.eltech.sapr.web.app.model.Transaction;
import ru.eltech.sapr.web.app.dao.TransactionDao;

import java.util.List;

public class TransactionService implements TransactionServiceInterface
{
    private TransactionDaoInterface data;

    public TransactionService(TransactionDaoInterface interf) { this.data = interf; }

    @Override
    public List<Transaction> getTransaction(long user)
    {
        return data.getTransactions(user);
    }

    @Override
    public Transaction getTransactionByID(long id, long user)
    {
        return data.getTransactionByID(id, user);
    }

    @Override
    public Transaction createTransactions(long from, long to, long user, float cash)
    {
        return data.createTransaction(from, to, user, cash);
    }

    @Override
    public boolean deleteTransactionByID(long id, long user)
    {
        return data.deleteTransactionByID(id, user);
    }

    @Override
    public void updateTransaction(Transaction transaction, long user)
    {
        data.update(transaction, user);
    }
}