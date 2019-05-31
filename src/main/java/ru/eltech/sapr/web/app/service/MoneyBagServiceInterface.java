package ru.eltech.sapr.web.app.service;

import ru.eltech.sapr.web.app.model.MoneyBag;
import ru.eltech.sapr.web.app.model.MoneyBagType;

import java.util.List;

public interface MoneyBagServiceInterface
{
    String SERVICE_NAME = "MoneyBagService";
    List<MoneyBag> getMoneyBags(long user);
    MoneyBag getMoneyBagsByID(long id, long user);
    MoneyBag createMoneyBags(String code, float cash, MoneyBagType type, long user);
    boolean deleteMoneyBagByID(long id, long user);
    void updateMoneyBag(MoneyBag moneyBag, long user);
}
