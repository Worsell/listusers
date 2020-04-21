package org.javalynx.controller.dao;

import org.javalynx.model.User;

import java.util.List;

public interface Dao {

    List<User> getUsers();

    boolean addUser(User user);

    boolean removeUser(User user);

    User getUser(String firstName, String secondName);

    User validateUser(String firstName, String secondName, String password);


}
