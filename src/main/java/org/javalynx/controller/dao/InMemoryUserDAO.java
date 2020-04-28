package org.javalynx.controller.dao;

import org.javalynx.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InMemoryUserDAO implements UserDAO {

    private static InMemoryUserDAO inMemoryDao;

    List<User> userList = new ArrayList<>();

    private InMemoryUserDAO() {}

    @Override
    public List<User> getUsers() {
        return userList;
    }

    @Override
    public boolean addUser(User user) {
        userList.add(user);
        return true;
    }

    @Override
    public boolean removeUser(User user) {
        if (userList.contains(user)) {
            userList.remove(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean validateUser(User user) throws SQLException {
        return false;
    }



    public static InMemoryUserDAO getInstance() {
        if (inMemoryDao == null) {
            inMemoryDao = new InMemoryUserDAO();
        }
        return inMemoryDao;
    }
}
