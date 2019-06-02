package ru.eltech.sapr.web.app.dao;

import ru.eltech.sapr.web.app.model.User;
import ru.eltech.sapr.web.app.model.UserType;
import ru.eltech.sapr.web.app.exception.UserServiceException;

import javax.sql.DataSource;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class H2UserDao implements UserDaoInterface
{
    private final DataSource dataSource;

    public H2UserDao(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> getUsers()
    {
        try (
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS");
            )
        {
            if (resultSet == null)
            {
                throw new SQLException("Unable to load usersS");
            }
            List<User> users = new ArrayList<>();
            while (resultSet.next())
            {
                users.add(retrieveUser(resultSet));
            }
            return users;
        }
        catch (SQLException e)
        {
            throw new UserServiceException(e);
        }
    }

    private User retrieveUser(ResultSet resultSet) throws SQLException
    {
        UserType type;
        switch (resultSet.getInt(4))
        {
            case 0:
                type = UserType.Admin;
                break;
            case 1:
                type = UserType.Editor;
                break;
            case 2:
                type = UserType.User;
                break;
            default:
                throw new SQLException("Unknown index userType");
        }
        return new User(
                resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(5),
                resultSet.getString(6),
                type
        );
    }

    @Override
    public User getUserByID(long id)
    {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("SELECT * FROM USERS WHERE id = ?")
            )
        {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery())
            {
                resultSet.next();
                return retrieveUser(resultSet);
            }
        } catch (SQLException e) {
            throw new UserServiceException(e);
        }
    }

    @Override
    public User createUser(String name, String surname, String email, String password, UserType type) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO USERS (NAME, SURNAME, EMAIL, PASSWORD, TYPE)" +
                        "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)
            )
        {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setInt(5, type.ordinal());

            int createdRows = statement.executeUpdate();
            if (createdRows != 1)
            {
                throw new SQLException("Unable to create new user");
            }

            try (ResultSet geteratedKeys = statement.getGeneratedKeys())
            {
                if (geteratedKeys.next())
                {
                    long id = geteratedKeys.getLong(1);
                    return new User(id, name, surname, email, password, type);
                }
                else
                {
                    throw new SQLException("Creating user failed, no ID obtained");
                }
            }
        }
        catch (SQLException e)
        {
            throw new UserServiceException(e);
        }
    }

    @Override
    public void update(User user)
    {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE USERS " +
                        "SET TYPE = ?, NAME = ?, SURNAME = ?, EMAIL = ?, PASSWORD = ?" +
                        "WHERE ID = ?")
            )
        {
            statement.setInt(1, user.getType().ordinal());
            statement.setString(2, user.getName());
            statement.setString(3, user.getSurname());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.setLong(6, user.getId());

            int updateRows = statement.executeUpdate();
            if (updateRows != 1)
            {
                throw new SQLException("Unable to update user");
            }
        }
        catch (SQLException e)
        {
            throw new UserServiceException(e);
        }
    }

    @Override
    public boolean deleteUserByID(long id)
    {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM USERS " +
                        "WHERE ID = ?")
            )
        {
            statement.setLong(1, id);
            int deletedRows = statement.executeUpdate();
            if (deletedRows != 1)
            {
                throw new UserServiceException("Unable to delete contact");
            }
            return true;
        }
        catch (SQLException e)
        {
            throw new UserServiceException(e);
        }
    }
}
