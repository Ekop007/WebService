package ru.eltech.sapr.web.app.model;

import java.util.Objects;

public class Transaction
{
    private long fromID;
    private long toID;
    private float cash;
    private final long id;

    public Transaction(long id, long from, long to, float cash)
    {
        this.id = id;
        this.fromID = from;
        this.toID = to;
        this.cash = cash;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction transaction = (Transaction) o;
        return id == transaction.id;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) fromID % 32;
        result = 31 * result + (int) toID % 32;
        return result;
    }

    public void changeCash(float cash)
    {
        this.cash = cash;
    }

    public float getCash()
    {
        return cash;
    }

    public long getId()
    {
        return id;
    }

    public long getFrom()
    {
        return fromID;
    }

    public long getTo()
{
    return toID;
}

    public void setFrom(long from) { this.fromID = from; }

    public void setTo(long to) { this.toID = to; }
}