package org.javalynx;

import org.javalynx.dao.UserDAO;
import org.javalynx.dao.UserHibernateDAO;
import org.javalynx.dao.UserJdbcDAO;
import org.jetbrains.annotations.Nullable;

public class DAOFactory {
    @Nullable
    public static <T> UserDAO getDAO(Class<T> tClass) {
        if (tClass == UserHibernateDAO.class) {
            return new UserHibernateDAO(DBHelper.createSessionFactory());
        } else if (tClass == UserJdbcDAO.class) {
            return new UserJdbcDAO(DBHelper.getMysqlConnection());
        }
        return null;
    }
}
