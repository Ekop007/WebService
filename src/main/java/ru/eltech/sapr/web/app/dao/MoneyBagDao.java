package ru.eltech.sapr.web.app.dao;

import ru.eltech.sapr.web.app.model.MoneyBag;
import ru.eltech.sapr.web.app.model.MoneyBagType;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class MoneyBagDao implements MoneyBagDaoInterface
{
    private Map<Long, Map<Long, MoneyBag>> moneyBags = new HashMap<>();
    private AtomicLong generatorID = new AtomicLong();

    @Override
    public List<MoneyBag> getMoneyBags(long user)
    {
        Collection<MoneyBag> userMoneyBags = moneyBags.getOrDefault(user, Collections.emptyMap()).values();
        return new ArrayList<>(userMoneyBags);
    }

    @Override
    public MoneyBag getMoneyBagsByID(long id, long user) { return moneyBags.get(user).get(id); }

    @Override
    public MoneyBag createMoneyBags(String code, float cash, MoneyBagType type, long user)
    {
        long newID = generatorID.incrementAndGet();
        MoneyBag newMoneyBag = new MoneyBag(newID, type, code, cash);
        moneyBags.putIfAbsent(user, new HashMap<>());
        moneyBags.get(user).put(newMoneyBag.getId(), newMoneyBag);
        return newMoneyBag;
    }

    @Override
    public boolean deleteMoneyBagByID(long id, long user)
    {
        MoneyBag delMoneyBag = moneyBags.get(user).remove(id);
        return delMoneyBag != null;
    }

    @Override
    public void update(MoneyBag moneyBag, long user)
    {
        moneyBags.get(user).put(moneyBag.getId(), moneyBag);
    }
}
