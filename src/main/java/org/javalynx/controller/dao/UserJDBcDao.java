package org.javalynx.controller.dao;

import org.javalynx.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJDBcDao implements UserDAO {

    Connection connection;

    public UserJDBcDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<User> getUsers() throws SQLException {
        Statement statement = connection.createStatement();
        List<User> users = new ArrayList<>();
        String sql = "SELECT firstname, lastname, password from baseusers.users";
        System.err.println(sql);
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            User user = new User();
            user.setFirstName(resultSet.getString(1));
            user.setLastName(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));
            users.add(user);
        }
        statement.close();
        return users;
    }

    @Override
    public boolean addUser(User user) throws SQLException {
        String sql = "INSERT INTO baseusers.users (firstname, lastname, password) Values (?, ?, ?)";
        System.err.println(sql);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getPassword());

        statement.executeUpdate();
        statement.close();
        return true;
    }

    @Override
    public boolean removeUser(User user) throws SQLException {
        String sql = "DELETE FROM baseusers.users where firstname = ? AND lastname = ? AND password = ?";
        return SQLQuery(user, sql);
    }

    private boolean SQLQuery(User user, String sql) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getPassword());
        int a = statement.executeUpdate();
        statement.close();
        return a == 1;
    }

    @Override
    public boolean validateUser(User user) throws SQLException {
        String sql = "SELECT count(firstname) from baseusers.users where firstname = ? AND lastname = ? AND password = ?";
        System.err.println(sql);

        return SQLQuery(user, sql);
    }

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists baseusers.users" +
                " (" +
                "firstname varchar(256)," +
                "lastname  varchar(256)," +
                "password varchar(256)," +
                "primary key (firstname, lastname)," +
                "UNIQUE KEY (firstname, lastname)" +
                ")");
        stmt.close();

    }

    public void deleteTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS baseusers.users");
        stmt.close();

    }

}
