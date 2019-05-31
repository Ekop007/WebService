package ru.eltech.sapr.web.app.dao;

import ru.eltech.sapr.web.app.model.MoneyBag;
import ru.eltech.sapr.web.app.model.MoneyBagType;

import java.util.List;

public interface MoneyBagDaoInterface
{
    List<MoneyBag> getMoneyBags(long user);
    MoneyBag getMoneyBagsByID(long id, long user);
    MoneyBag createMoneyBags(String code, float cash, MoneyBagType type, long user);
    boolean deleteMoneyBagByID(long id, long user);
    void update(MoneyBag moneyBag, long user);
}
