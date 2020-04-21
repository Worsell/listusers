package org.javalynx.controller.service;

import org.javalynx.controller.dao.Dao;
import org.javalynx.controller.dao.InMemoryDao;
import org.javalynx.model.User;

import java.util.List;

public class CarService {

    private static CarService carService;

    private static Dao dao = InMemoryDao.getInstance();

    public List<User> getAllUsers() {
        User u = new User("f1", "l1", "p1");
        dao.addUser(u);
        u = new User("f2", "l2", "p2");
        dao.addUser(u);
        u = new User("f3", "l3", "p3");
        dao.addUser(u);
        u = new User("f4", "l4", "p4");
        dao.addUser(u);

        return dao.getUsers();
    }

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService();
        }

        return carService;
    }
}
