package org.javalynx.dao;

import org.hibernate.Query;
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
        Query query = session.createQuery("from User");
        System.out.println("1234");
        List<User> users = (List<User>) query.list();
        System.out.println("1234");

        System.out.println(users);
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public boolean addUser(User user) throws SQLException {
        Session session =  sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean removeUser(long id) throws SQLException {
        User user = new User();
        user.setId(id);
        Session session =  sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
        return true;
    }


    @Override
    public boolean removeUser(User user) throws SQLException {
        if (user.getId() == 0) return false;
        Session session =  sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean validateUser(User user) throws SQLException {
        String hql = "select count(id) FROM User where firstname = :firstname and lastname = :lastname and password = :password ";
        Session session =  sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("firstname", user.getFirstName());
        query.setParameter("lastname", user.getLastName());
        query.setParameter("password", user.getPassword());
        Long id = (Long) query.uniqueResult();
        transaction.commit();
        session.close();
        return id == 1;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        if (user.getId() == 0) return false;
        Session session =  sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public User getUserById(long user) throws SQLException {
        if (user == 0) return null;
        Session session =  sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user1 = (User) session.get(User.class, user);
        transaction.commit();
        session.close();
        return user1;
    }


    @Override
    public User getUserByFLname(String firstname, String lastname) throws SQLException {
        String hql = "FROM User where firstname = :firstname and lastname = :lastname";
        Session session =  sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("firstname", firstname);
        query.setParameter("lastname", lastname);
        User user = (User) query.uniqueResult();
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public Long getIdByFLname(String firstname, String lastname) throws SQLException {
        return getUserByFLname(firstname, lastname).getId();
    }

    @Override
    public Long getIdByUser(User user) throws SQLException {
        return getIdByFLname(user.getFirstName(), user.getLastName());
    }

}
