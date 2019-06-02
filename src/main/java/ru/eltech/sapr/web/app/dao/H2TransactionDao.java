package ru.eltech.sapr.web.app.dao;


import ru.eltech.sapr.web.app.model.Transaction;
import ru.eltech.sapr.web.app.exception.TransactionServiceException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2TransactionDao implements TransactionDaoInterface
{
    private final DataSource dataSource;

    public H2TransactionDao(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    private Transaction retrieveTransaction(ResultSet resultSet) throws SQLException
    {
        return new Transaction(
                resultSet.getLong(1),
                resultSet.getLong(3),
                resultSet.getLong(4),
                resultSet.getFloat(2)
        );
    }

    @Override
    public List<Transaction> getTransactions(long userID)
    {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM TRANSACTIONS WHERE USER_ID = ?")
            )
        {
            statement.setLong(1, userID);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet == null)
            {
                throw new SQLException("Unable to load contacts");
            }
            List<Transaction> moneyBags = new ArrayList<>();
            while (resultSet.next())
            {
                moneyBags.add(retrieveTransaction(resultSet));
            }
            return moneyBags;
        }
        catch (SQLException e)
        {
            throw new TransactionServiceException(e);
        }
    }

    @Override
    public Transaction getTransactionByID(long id, long userID)
    {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM TRANSACTIONS WHERE USER_ID = ? AND ID = ?")
            )
        {
            statement.setLong(1, userID);
            statement.setLong(2, id);

            try (ResultSet resultSet = statement.executeQuery())
            {
                resultSet.next();
                return retrieveTransaction(resultSet);
            }
        }
        catch (SQLException e)
        {
            throw new TransactionServiceException(e);
        }
    }

    @Override
    public Transaction createTransaction(long from, long to, long user, float cash)
    {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO TRANSACTIONS (CASH, FROM_ID, TO_ID, USER_ID) " +
                        "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)
            )
        {
            statement.setFloat(1, cash);
            statement.setLong(2, from);
            statement.setLong(3, to);
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
                    return new Transaction(id, from, to, cash);
                }
                else
                {
                    throw new SQLException("Creating user failed, no ID obtained");
                }
            }
        }
        catch (SQLException e)
        {
            throw new TransactionServiceException(e);
        }
    }

    @Override
    public boolean deleteTransactionByID(long id, long userID)
    {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM TRANSACTIONS " +
                "WHERE ID = ? AND USER_ID = ?")
            )
        {
            statement.setLong(1, id);
            statement.setLong(2, userID);
            int deletedRows = statement.executeUpdate();
            if (deletedRows != 1)
            {
                throw new TransactionServiceException("Unable to delete contact");
            }
            return true;
        }
        catch (SQLException e)
        {
            throw new TransactionServiceException(e);
        }
    }

    @Override
    public void update(Transaction transaction, long userID)
    {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE TRANSACTIONS " +
                        " SET FROM_ID = ?, TO_ID = ?, CASH = ?, USER_ID = ? WHERE ID = ?")
            )
        {
            statement.setLong(1, transaction.getFrom());
            statement.setLong(2, transaction.getTo());
            statement.setFloat(4, transaction.getCash());
            statement.setLong(5, userID);
            statement.setLong(3, transaction.getId());

            int updateRows = statement.executeUpdate();
            if (updateRows != 1)
            {
                throw new SQLException("Unable to update user");
            }
        }
        catch (SQLException e)
        {
            throw new TransactionServiceException(e);
        }
    }
}
