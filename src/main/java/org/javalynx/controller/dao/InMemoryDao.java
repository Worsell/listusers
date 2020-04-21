package org.javalynx.controller.dao;

import org.javalynx.model.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDao implements Dao {

    private static InMemoryDao inMemoryDao;

    List<User> userList = new ArrayList<>();

    private InMemoryDao() {}

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
    public User getUser(String firstName, String secondName) {
        return null;
    }

    @Override
    public User validateUser(String firstName, String secondName, String password) {
        return null;
    }

    public static InMemoryDao getInstance() {
        if (inMemoryDao == null) {
            inMemoryDao = new InMemoryDao();
        }
        return inMemoryDao;
    }
}
