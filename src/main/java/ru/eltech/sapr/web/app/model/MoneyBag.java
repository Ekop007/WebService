package ru.eltech.sapr.web.app.model;

import java.util.Objects;

public class MoneyBag
{
    private final long id;
    private final MoneyBagType type;
    private final String code;
    private float cash;

    public MoneyBag(long id, MoneyBagType type, String code, float cash)
    {
        this.id = id;
        this.type = type;
        this.code = code;
        this.cash = cash;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoneyBag moneyBag = (MoneyBag) o;
        return id == moneyBag.id && moneyBag.cash == cash &&
                type == moneyBag.type && Objects.equals(code, moneyBag.code);
    }

    @Override
    public int hashCode()
    {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + type.hashCode();
        result = 31 * result + code.hashCode();
        return result;
    }

    public float getCash()
    {
        return cash;
    }

    public void addCash(float cost)
    {
        cash += cost;
    }

    public void removeCash(float cost)
    {
        cash -= cost;
    }

    public long getId()
    {
        return id;
    }

    public String getCode() { return code; }

    public MoneyBagType getType()
    {
        return type;
    }
}
