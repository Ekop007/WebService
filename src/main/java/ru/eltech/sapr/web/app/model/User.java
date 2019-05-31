package ru.eltech.sapr.web.app.model;

import java.util.Objects;

public class User
{
    private final long id;
    private final UserType type;
    private final String name;
    private final String surname;
    private final String email;
    private final String password;

    public User(long id, String name, String surname, String email, String password, UserType type)
    {
        this.id = id;
        this.type = type;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && password.equals(user.password) &&
                type == user.type && Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode()
    {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString()
    {
        return "" + id + " " + type + " " + name +
                " " + surname + " " + email;
    }

    public UserType getType()
    {
        return type;
    }

    public String getName()
    {
        return name;
    }

    public String getSurname()
    {
        return surname;
    }

    public long getId()
    {
        return id;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }
}
