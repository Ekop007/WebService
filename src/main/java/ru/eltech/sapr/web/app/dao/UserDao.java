package ru.eltech.sapr.web.app.dao;

import ru.eltech.sapr.web.app.model.User;
import ru.eltech.sapr.web.app.model.UserType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class UserDao implements UserDaoInterface
{
    private Map<Long, User> users = new HashMap<>();
    private AtomicLong generatorID = new AtomicLong();

    @Override
    public List<User> getUsers()
    {
        return new ArrayList<>(users.values());
    }

    @Override
    public User getUserByID(long id)
    {
        return users.get(id);
    }

    @Override
    public User createUser(String name, String surname, String email, String password, UserType type)
    {
        User newUser = new User(generatorID.incrementAndGet(), name, surname, email, password, type);
        users.put(newUser.getId(), newUser);
        return newUser;
    }

    @Override
    public boolean deleteUserByID(long id)
    {
        User delUser = users.remove(id);
        return delUser != null;
    }

    @Override
    public void update(User user)
    {
        users.put(user.getId(), user);
    }
}
