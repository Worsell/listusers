package org.javalynx.service;

import org.javalynx.DAOFactory;
import org.javalynx.dao.UserDAO;
import org.javalynx.model.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class UserService {

    private static UserService userService;

    private UserDAO userDAO;



    public UserService(UserDAO userDAO) throws SQLException {
        this.userDAO = userDAO;
        User user = new User("admin", "admin", "admin");
        user.setRole("admin");
        userDAO.addUser(user);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDAO.getUsers();
    }

    public boolean updateUser(User user) throws SQLException {
        // удобнее с точки зрения бизнесс логики (в случае когда не нужна высокая производительность)
        return userDAO.updateUser(user);
    }

    public boolean removeUser(User user) throws SQLException {
        return userDAO.removeUser(user);
    }

    public boolean addUser(User user) throws SQLException {
        return userDAO.addUser(user);
    }

    public String getStringAU(User user) throws SQLException {
        String answer = userDAO.getUserById(userDAO.getIdByUser(user)).getRole();
        if(Objects.equals(answer, "admin") || Objects.equals(answer, "user"))
            return answer;
        else
            return null;
    }
    public String getStringAU(Long id) throws SQLException {
        String answer = userDAO.getUserById(id).getRole();
        if(Objects.equals(answer, "admin") || Objects.equals(answer, "user"))
            return answer;
        else
            return null;
    }


    public User getUserByFLP(User user) throws SQLException {
        return userDAO.validateUser(user) ? userDAO.getUserByFLname(user.getFirstName(), user.getLastName()) : null;
    }
    public User getUserByID(Long id) throws SQLException {
        return userDAO.getUserById(id);
    }



    public static UserService getInstance() {
        if (userService == null) {
            try {
                userService = new UserService(DAOFactory.getDAO());
            } catch (SQLException | FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return userService;
    }
}
