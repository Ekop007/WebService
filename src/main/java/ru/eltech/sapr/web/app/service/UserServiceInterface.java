package ru.eltech.sapr.web.app.service;

import ru.eltech.sapr.web.app.model.User;
import ru.eltech.sapr.web.app.model.UserType;

import java.util.List;

public interface UserServiceInterface
{
    String SERVICE_NAME = "UserService";
    List<User> getUsers();
    User getUserByID(long id);
    User createUser(String name, String surname, String email, String password, UserType type);
    boolean deleteUserByID(long id);
}
