package ru.eltech.sapr.web.app.service;

import ru.eltech.sapr.web.app.dao.MoneyBagDaoInterface;
import ru.eltech.sapr.web.app.model.MoneyBag;
import ru.eltech.sapr.web.app.model.MoneyBagType;

import java.util.List;

public class MoneyBagService implements MoneyBagServiceInterface
{
    private MoneyBagDaoInterface data;

    public MoneyBagService(MoneyBagDaoInterface interf) { this.data = interf; }


    @Override
    public List<MoneyBag> getMoneyBags(long user)
    {
        return data.getMoneyBags(user);
    }

    @Override
    public MoneyBag getMoneyBagsByID(long id, long user)
    {
        return data.getMoneyBagsByID(id, user);
    }

    @Override
    public MoneyBag createMoneyBags(String code, float cash, MoneyBagType type, long user)
    {
        return data.createMoneyBags(code, cash, type, user);
    }

    public boolean deleteMoneyBagByID(long id, long user)
    {
        return data.deleteMoneyBagByID(id, user);
    }

    @Override
    public void updateMoneyBag(MoneyBag moneyBag, long user)
    {
        data.update(moneyBag, user);
    }

}
