package ru.eltech.sapr.web.app.dao;

import ru.eltech.sapr.web.app.model.User;
import ru.eltech.sapr.web.app.model.UserType;

import java.util.List;

public interface UserDaoInterface
{
    List<User> getUsers();
    User getUserByID(long id);
    User createUser(String name, String surname, String email, String password, UserType type);
    void update(User user);
    boolean deleteUserByID(long id);
}
