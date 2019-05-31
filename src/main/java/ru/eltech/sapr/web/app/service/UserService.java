package ru.eltech.sapr.web.app.service;

import ru.eltech.sapr.web.app.model.User;
import ru.eltech.sapr.web.app.dao.UserDao;
import ru.eltech.sapr.web.app.model.UserType;

import java.util.List;

public class UserService implements UserServiceInterface
{
    private UserDao data = new UserDao();

    @Override
    public List<User> getUsers()
    {
        return data.getUsers();
    }

    @Override
    public User getUserByID(long id)
    {
        return data.getUserByID(id);
    }

    @Override
    public User createUser(String name, String surname, String email, String password, UserType type)
    {
        return data.createUser(name, surname, email, password, type);
    }

    @Override
    public boolean deleteUserByID(long id)
    {
        return data.deleteUserByID(id);
    }
}
