package com.vz89.hometask.repository.db;

import com.vz89.hometask.model.Account;
import com.vz89.hometask.model.AccountStatus;
import com.vz89.hometask.repository.AccountRepository;
import com.vz89.hometask.service.DbService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbAccountRepositoryImpl implements AccountRepository {
    private static final String ID = "id";
    private static final String ACCOUNT_NAME = "account_name";
    private static final String ACCOUNT_STATUS = "account_status";
    private DbService dbService = new DbService();
    private Connection connection;

    @Override
    public Account getById(Long id) {
        Account account = null;
        try {
            connection = dbService.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM account WHERE id='" + id + "'");
            while (resultSet.next()) {
                Long skillId = resultSet.getLong(ID);
                String name = resultSet.getString(ACCOUNT_NAME);
                String status = resultSet.getString(ACCOUNT_STATUS);
                account = new Account(skillId, name, AccountStatus.valueOf(status));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        try {
            connection = dbService.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM account");
            while (resultSet.next()) {
                Long id = resultSet.getLong(ID);
                String name = resultSet.getString(ACCOUNT_NAME);
                String status = resultSet.getString(ACCOUNT_STATUS);
                accounts.add(new Account(id, name, AccountStatus.valueOf(status)));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public Account save(Account account) {
        try {
            connection = dbService.getConnection();
            Statement statement = connection.createStatement();
            statement.addBatch("Insert Into account (account_name, account_status) VALUES('" + account.getName() + "','" + AccountStatus.ACTIVE + "')");
            statement.executeBatch();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        account.setAccountStatus(AccountStatus.ACTIVE);
        return account;
    }

    @Override
    public Account update(Account account) {
        try {
            connection = dbService.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE account SET account_name='" + account.getId() + "',account_status='" + account.getAccountStatus() + "' WHERE id='" + account.getId() + "'");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public void deleteById(Long id) {
        try {
            connection = dbService.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM account WHERE id='" + id + "'");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
