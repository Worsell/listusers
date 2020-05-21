package org.javalynx.service;

import org.javalynx.DBHelper;
import org.javalynx.dao.UserDAO;
import org.javalynx.dao.UserJDBcDao;
import org.javalynx.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserService userService;

    private static UserDAO userDAO = new UserJDBcDao(DBHelper.getMysqlConnection());

    public List<User> getAllUsers() throws SQLException {
        return userDAO.getUsers();
    }

    public boolean updateUser(User olduser, User newuser) throws SQLException {
        // удобнее с точки зрения бизнесс логики (в случае когда не нужна высокая производительность)
        return userDAO.validateUser(olduser) && userDAO.updateUser(newuser.setId(userDAO.getIdByUser(olduser)));
    }

    public boolean removeUser(User user) throws SQLException {
        return userDAO.validateUser(user) && userDAO.removeUser(userDAO.getUserByFLname(user.getFirstName(), user.getLastName()));
    }

    public boolean addUser(User user) throws SQLException {
        return userDAO.addUser(user);
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }

        return userService;
    }
}
