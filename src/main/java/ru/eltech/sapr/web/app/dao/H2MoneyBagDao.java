package ru.eltech.sapr.web.app.dao;

import ru.eltech.sapr.web.app.model.MoneyBag;
import ru.eltech.sapr.web.app.model.MoneyBagType;
import ru.eltech.sapr.web.app.exception.MoneyBagServiceException;

import javax.sql.DataSource;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;


public class H2MoneyBagDao implements MoneyBagDaoInterface
{
    private final DataSource dataSource;

    public H2MoneyBagDao(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    private MoneyBag retrieveMoneyBag(ResultSet resultSet) throws SQLException
    {
        MoneyBagType type;
        switch (resultSet.getInt(3))
        {
            case 0:
                type = MoneyBagType.Card;
                break;
            case 1:
                type = MoneyBagType.Deposit;
                break;
            case 2:
                type = MoneyBagType.Cash;
                break;
            default:
                throw new RuntimeException("Unknown index phoneType");
        }
        return new MoneyBag(
                resultSet.getLong(1),
                type,
                resultSet.getString(4),
                resultSet.getFloat(2)
        );
    }

    @Override
    public List<MoneyBag> getMoneyBags(long user) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM MONEYBAGS WHERE USER_ID = ?")
            )
        {
            statement.setLong(1, user);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet == null)
            {
                throw new SQLException("Unable to load contacts");
            }
            List<MoneyBag> moneyBags = new ArrayList<>();
            while (resultSet.next())
            {
                moneyBags.add(retrieveMoneyBag(resultSet));
            }
            return moneyBags;
        }
        catch (SQLException e)
        {
            throw new MoneyBagServiceException(e);
        }
    }

    @Override
    public MoneyBag getMoneyBagsByID(long id, long user)
    {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM MONEYBAGS WHERE USER_ID = ? AND ID = ?")
            )
        {
            statement.setLong(1, user);
            statement.setLong(2, id);
            try (ResultSet resultSet = statement.executeQuery())
            {
                resultSet.next();
                return retrieveMoneyBag(resultSet);
            }
        }
        catch (SQLException e)
        {
            throw new MoneyBagServiceException(e);
        }
    }

    @Override
    public MoneyBag createMoneyBags(String code, float cash, MoneyBagType type, long user)
    {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO MONEYBAGS (CASH, TYPE, CODE, USER_ID) " +
                        "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)
            )
        {
            statement.setFloat(1, cash);
            statement.setInt(2, type.ordinal());
            statement.setString(3, code);
            statement.setLong(4, user);

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
                    return new MoneyBag(id, type, code, cash);
                }
                else
                {
                    throw new SQLException("Creating user failed, no ID obtained");
                }
            }
        }
        catch (SQLException e)
        {
            throw new MoneyBagServiceException(e);
        }
    }

    @Override
    public boolean deleteMoneyBagByID(long id, long user)
    {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM MONEYBAGS " +
                        "WHERE ID = ? AND USER_ID = ?")
            )
        {
            statement.setLong(1, id);
            statement.setLong(2, user);
            int deletedRows = statement.executeUpdate();
            if (deletedRows != 1)
            {
                throw new MoneyBagServiceException("Unable to delete contact");
            }
            return true;
        }
        catch (SQLException e)
        {
            throw new MoneyBagServiceException(e);
        }
    }

    @Override
    public void update(MoneyBag moneyBag, long user)
    {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE MONEYBAGS " +
                        " SET TYPE = ?, CODE = ?, CASH = ? WHERE ID = ?")
            )
        {
            statement.setInt(1, moneyBag.getType().ordinal());
            statement.setString(2, moneyBag.getCode());
            statement.setFloat(4, moneyBag.getCash());
            statement.setLong(3, user);

            int updateRows = statement.executeUpdate();
            if (updateRows != 1)
            {
                throw new SQLException("Unable to update user");
            }
        }
        catch (SQLException e)
        {
            throw new MoneyBagServiceException(e);
        }
    }
}
