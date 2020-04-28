package org.javalynx.controller.service;

import org.javalynx.DBHelper;
import org.javalynx.controller.dao.UserDAO;
import org.javalynx.controller.dao.UserJDBcDao;
import org.javalynx.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserService userService;

    private static UserDAO userDAO = new UserJDBcDao(DBHelper.getMysqlConnection());

    public List<User> getAllUsers() throws SQLException {
        System.err.println(userDAO.getUsers());
        return userDAO.getUsers();
    }

    public boolean editUser(User first, User second) throws SQLException {
        if (userDAO.getUsers().contains(first)) {
            User edited = userDAO.getUsers().get(userDAO.getUsers().indexOf(first));
            edited.setFirstName(second.getFirstName());
            edited.setLastName(second.getLastName());
            edited.setPassword(second.getPassword());
        }
        return false;
    }

    public boolean removeUser(User user) throws SQLException {
        userDAO.removeUser(user);
        return true;
    }

    public boolean addUser(User user) throws SQLException {
        System.err.println("ADD USER S");
        userDAO.addUser(user);
        return true;
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }

        return userService;
    }
}
