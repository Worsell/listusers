package org.javalynx.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.javalynx.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDAO {

    private SessionFactory sessionFactory;

    public UserHibernateDAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getUsers() throws SQLException {
        Session session =  sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = (List<User>) session.createQuery("from user").list();
        transaction.commit();
        return users;
    }

    @Override
    public boolean addUser(User user) throws SQLException {
        Session session =  sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        return true;
    }

    @Override
    public boolean removeUser(long id) throws SQLException {
        return false;
    }


    @Override
    public boolean removeUser(User user) throws SQLException {
        Session session =  sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        return true;
    }

    @Override
    public boolean validateUser(User user) throws SQLException {
        return false;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        return false;
    }

    @Override
    public User getUserById(long user) throws SQLException {
        return null;
    }


    @Override
    public User getUserByFLname(String firstname, String lastname) throws SQLException {
        return null;
    }

    @Override
    public Long getIdByFLname(String firstname, String lastname) throws SQLException {
        return null;
    }

    @Override
    public Long getIdByUser(User user) throws SQLException {
        return null;
    }


}
