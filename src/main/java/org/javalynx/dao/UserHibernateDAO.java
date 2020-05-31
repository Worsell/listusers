package org.javalynx.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.javalynx.model.User;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class UserHibernateDAO implements UserDAO {

    private SessionFactory sessionFactory;

    public UserHibernateDAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Nullable
    public List<User> getUsers() throws SQLException {
        Session session = null;
        Transaction transaction = null;
        List<User> users = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("from User");
            users = (List<User>) query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        if (session != null) {
            session.close();
        }
        return users;
    }

    @Override
    public boolean addUser(User user) throws SQLException {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            if (session != null) {
                session.close();
            }
            return false;
        }
        session.close();
        return true;
    }

    @Override
    public boolean removeUser(long id) throws SQLException {
        User user = new User();
        user.setId(id);
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            if (session != null) {
                session.close();
            }
            return false;
        }
        session.close();
        return true;
    }


    @Override
    public boolean removeUser(User user) throws SQLException {
        return removeUser(user.getId());
    }

    @Override
    public boolean validateUser(User user) throws SQLException {
        String hql = "select count(id) FROM User where firstname = :firstname and lastname = :lastname and password = :password ";
        Session session = null;
        Long id = 0L;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("firstname", user.getFirstName());
            query.setParameter("lastname", user.getLastName());
            query.setParameter("password", user.getPassword());
            id = (Long) query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            if (session != null) {
                session.close();
            }
            return false;
        }
        session.close();
        return id == 1;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        if (user.getId() == 0) return false;
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            if (session != null) {
                session.close();
            }
            return false;
        }
        session.close();
        return true;
    }

    @Override
    @Nullable
    public User getUserById(long user) throws SQLException {
        if (user == 0) return null;
        User answer;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            answer = (User) session.get(User.class, user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            if (session != null) {
                session.close();
            }
            return null;
        }
        session.close();
        return answer;
    }


    @Override
    @Nullable
    public User getUserByFLname(String firstname, String lastname) throws SQLException {
        String hql = "FROM User where firstname = :firstname and lastname = :lastname";
        Session session = null;
        Transaction transaction = null;
        User user;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("firstname", firstname);
            query.setParameter("lastname", lastname);
            user = (User) query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            if (session != null) {
                session.close();
            }
            return null;
        }
        session.close();
        return user;
    }

    @Override
    @Nullable
    public Long getIdByFLname(String firstname, String lastname) throws SQLException {
        return Objects.requireNonNull(getUserByFLname(firstname, lastname)).getId();
    }

    @Override
    @Nullable
    public Long getIdByUser(User user) throws SQLException {
        return getIdByFLname(user.getFirstName(), user.getLastName());
    }

}
