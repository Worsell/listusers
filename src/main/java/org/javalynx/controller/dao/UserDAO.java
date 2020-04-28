package org.javalynx.controller.dao;

import org.javalynx.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    List<User> getUsers() throws SQLException;

    boolean addUser(User user) throws SQLException;

    boolean removeUser(User user) throws SQLException;


    boolean validateUser(User user) throws SQLException;


}
