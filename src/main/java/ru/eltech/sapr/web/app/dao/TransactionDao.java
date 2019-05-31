package ru.eltech.sapr.web.app.dao;

import ru.eltech.sapr.web.app.model.Transaction;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class TransactionDao implements TransactionDaoInterface
{
    private Map<Long, Map<Long, Transaction>> transactions = new HashMap<>();
    private AtomicLong generatorID = new AtomicLong();

    @Override
    public List<Transaction> getTransactions(long userID)
    {
        Collection<Transaction> userTrans = transactions.getOrDefault(userID, Collections.emptyMap()).values();
        return new ArrayList<>(userTrans);
    }

    @Override
    public Transaction getTransactionByID(long id, long userID)
    {
        return transactions.get(userID).get(id);
    }

    @Override
    public Transaction createTransaction(long from, long to, long user, float cash)
    {
        long transID = generatorID.incrementAndGet();
        Transaction newTransaction = new Transaction(transID, from, to, cash);
        transactions.putIfAbsent(user, new HashMap<>());
        transactions.get(user).put(transID, newTransaction);
        return newTransaction;
    }

    @Override
    public boolean deleteTransactionByID(long id, long userID)
    {
        Transaction delTransaction = transactions.get(userID).remove(id);
        return delTransaction != null;
    }

    @Override
    public void update(Transaction transaction, long userID)
    {
        transactions.get(userID).put(transaction.getId(), transaction);
    }
}
