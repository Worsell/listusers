package org.javalynx.dao;

import org.javalynx.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    List<User> getUsers() throws SQLException;

    boolean addUser(User user) throws SQLException;

    boolean removeUser(long id) throws SQLException;

    boolean removeUser(User user) throws SQLException;

    boolean validateUser(User user) throws SQLException;

    boolean updateUser(User user) throws  SQLException;

    User getUserById(long user) throws  SQLException;

    User getUserByFLname(String firstname, String lastname) throws SQLException;

    Long getIdByFLname(String firstname, String lastname) throws SQLException;

    Long getIdByUser(User user) throws SQLException;

}
