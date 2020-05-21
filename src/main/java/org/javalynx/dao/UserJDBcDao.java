package org.javalynx.dao;

import org.javalynx.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJDBcDao implements UserDAO {

    Connection connection;

    public UserJDBcDao(Connection connection) {
        this.connection = connection;
        try {
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<User> getUsers() throws SQLException {
        Statement statement = connection.createStatement();
        List<User> users = new ArrayList<>();
        String sql = "SELECT firstname, lastname, password, id from baseusers.users";
        System.err.println(sql);
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            User user = new User();
            user.setFirstName(resultSet.getString(1));
            user.setLastName(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));
            user.setId(resultSet.getInt(4));
            users.add(user);
        }
        statement.close();
        System.out.println(users);
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
    public boolean removeUser(long id) throws SQLException {
        if(id == 0)
            return false;

        String sql = "DELETE FROM baseusers.users where id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setLong(1, id);

        int a = statement.executeUpdate();

        statement.close();


        return a == 1;
    }


    @Override
    public boolean removeUser(User user) throws SQLException {
        if(user.getId() == 0)
            return false;

        String sql = "DELETE FROM baseusers.users where id = ?";
        System.out.println(sql);
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setLong(1, user.getId());
        int a = statement.executeUpdate();
        statement.close();

        return a == 1;
    }



    @Override
    public boolean validateUser(User user) throws SQLException {
        if(user.getLastName() == null)
            return false;
        String sql = "SELECT count(firstname) from baseusers.users where firstname = ? AND lastname = ? AND password = ?";
        System.err.println(sql);

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getPassword());
        statement.execute();
        int a = 0;
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next())
            a++;
        resultSet.close();
        statement.close();
        return a == 1;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        if(user.getId() == 0)
            return false;

        String sql = "UPDATE baseusers.users set firstname = ?, lastname = ?, password = ? where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getPassword());
        statement.setLong(4, user.getId());
        int a = statement.executeUpdate();
        statement.close();

        return a == 1;
    }

    @Override
    public User getUserById(long id) throws SQLException {

        String sql = "SELECT id, firstname, lastname, password from baseusers.users where id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setLong(1, id);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        User user = new User();
        resultSet.next();
        user.setFirstName(resultSet.getString(2));
        user.setLastName(resultSet.getString(3));
        user.setPassword(resultSet.getString(4));
        user.setId(resultSet.getInt(1));
        statement.close();
        resultSet.close();
        return user;
    }

    @Override
    public User getUserByFLname(String firstname, String lastname) throws SQLException {
        System.out.println(firstname);
        System.out.println(lastname);

        String sql = "SELECT id, firstname, lastname, password from baseusers.users where firstname = ? AND lastname = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, firstname);
        statement.setString(2, lastname);

        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        User user = new User();
        if (resultSet.next()) {
            user.setFirstName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setPassword(resultSet.getString(4));
            user.setId(resultSet.getInt(1));
        }
        statement.close();
        resultSet.close();
        return user;

    }

    @Override
    public Long getIdByFLname(String firstname, String lastname) throws SQLException {
        return getUserByFLname(firstname, lastname).getId();
    }

    @Override
    public Long getIdByUser(User user) throws SQLException {
        return getIdByFLname(user.getFirstName(), user.getLastName());
    }

    private void createTable() throws SQLException {
        connection.setAutoCommit(false);
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists baseusers.users" +
                " (" +
                "id bigint NOT NULL AUTO_INCREMENT," +
                "firstname varchar(256)," +
                "lastname  varchar(256)," +
                "password varchar(256)," +
                "primary key (id)," +
                "UNIQUE KEY (firstname, lastname)" +
                ")");
        connection.commit();
        stmt.close();

    }

    public void deleteTable() throws SQLException {
        connection.setAutoCommit(false);
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS baseusers.users");
        connection.commit();
        stmt.close();
    }

}
