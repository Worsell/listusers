package org.javalynx.dao;

import org.javalynx.model.User;
import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {

    Connection connection;

    public UserJdbcDAO(Connection connection) {
        this.connection = connection;
        try {
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<User> getUsers() throws SQLException {
        connection.setAutoCommit(false);
        List<User> users = new ArrayList<>();
        Statement statement = connection.createStatement();
        String sql = "SELECT firstname, lastname, password, id from baseusers.users";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setFirstName(resultSet.getString(1));
                user.setLastName(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setId(resultSet.getInt(4));
                users.add(user);
            }
            connection.commit();
        } catch (Exception e ){
            connection.rollback();
        }
        statement.close();
        connection.setAutoCommit(true);
        return users;
    }

    @Override
    public boolean addUser(User user) throws SQLException {
        connection.setAutoCommit(false);

        String sql = "INSERT INTO baseusers.users (firstname, lastname, password) Values (?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            statement.close();
            connection.setAutoCommit(true);
            return false;
        }
        statement.close();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public boolean removeUser(long id) throws SQLException {
        if(id == 0)
            return false;
        connection.setAutoCommit(false);

        String sql = "DELETE FROM baseusers.users where id = ?";
        int a = 0;
        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            statement.setLong(1, id);
            a = statement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
        }
        statement.close();
        connection.setAutoCommit(true);
        return a == 1;
    }


    @Override
    public boolean removeUser(User user) throws SQLException {
        if(user.getId() == 0)
            return false;
        connection.setAutoCommit(false);

        String sql = "DELETE FROM baseusers.users where id = ?";
        int a = 0;
        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            statement.setLong(1, user.getId());
            a = statement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
        }
        statement.close();
        connection.setAutoCommit(true);
        return a == 1;
    }



    @Override
    public boolean validateUser(User user) throws SQLException {
        if(user.getLastName() == null)
            return false;
        connection.setAutoCommit(false);

        String sql = "SELECT count(firstname) from baseusers.users where firstname = ? AND lastname = ? AND password = ?";
        int a = 0;
        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getPassword());
            statement.execute();
            connection.commit();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next())
                a++;
        } catch (Exception e) {
            connection.rollback();
        }
        statement.close();
        connection.setAutoCommit(true);
        return a == 1;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        if(user.getId() == 0)
            return false;
        connection.setAutoCommit(false);
        int a = 0;
        String sql = "UPDATE baseusers.users set firstname = ?, lastname = ?, password = ? where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getPassword());
            statement.setLong(4, user.getId());
            a = statement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
        }
        statement.close();
        connection.setAutoCommit(true);
        return a == 1;
    }

    @Override
    public User getUserById(long id) throws SQLException {
        connection.setAutoCommit(false);
        String sql = "SELECT id, firstname, lastname, password from baseusers.users where id = ?";
        User user = new User();
        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            statement.setLong(1, id);
            statement.execute();
            connection.commit();
            ResultSet resultSet = statement.getResultSet();

            resultSet.next();
            user.setFirstName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setPassword(resultSet.getString(4));
            user.setId(resultSet.getInt(1));

        } catch (Exception e) {
            connection.rollback();
        }
        statement.close();
        connection.setAutoCommit(true);
        return user;
    }

    @Override
    public User getUserByFLname(String firstname, String lastname) throws SQLException {

        connection.setAutoCommit(false);
        String sql = "SELECT id, firstname, lastname, password from baseusers.users where firstname = ? AND lastname = ?";
        User user = new User();

        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            statement.setString(1, firstname);
            statement.setString(2, lastname);
            statement.execute();
            connection.commit();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                user.setId(resultSet.getInt(1));
            }
        } catch (Exception e) {
            connection.rollback();
        }
        statement.close();
        connection.setAutoCommit(true);
        return user;
    }

    @Override
    @Nullable
    public Long getIdByFLname(String firstname, String lastname) throws SQLException {
        return getUserByFLname(firstname, lastname).getId();
    }

    @Override
    @Nullable
    public Long getIdByUser(User user) throws SQLException {
        return getIdByFLname(user.getFirstName(), user.getLastName());
    }

    private void createTable() throws SQLException {
        connection.setAutoCommit(false);
        Statement stmt = connection.createStatement();
        try {
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
        } catch (Exception e) {
            connection.rollback();
        }
        stmt.close();
        connection.setAutoCommit(true);
    }

    public void deleteTable() throws SQLException {
        connection.setAutoCommit(false);
        Statement stmt = connection.createStatement();
        try {
            stmt.executeUpdate("DROP TABLE IF EXISTS baseusers.users");
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
        }
        stmt.close();
        connection.setAutoCommit(true);

    }

}
