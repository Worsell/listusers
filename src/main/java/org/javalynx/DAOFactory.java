package org.javalynx;

import org.javalynx.dao.UserDAO;
import org.javalynx.dao.UserHibernateDAO;
import org.javalynx.dao.UserJdbcDAO;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Scanner;

public class DAOFactory {
    @Nullable
    public static UserDAO getDAO() throws IOException {
        Scanner scanner = new Scanner(DAOFactory.class.getClassLoader().getResourceAsStream("dao.properties"));
        String tmp = scanner.nextLine();
        String a = tmp.split("=")[0];
        String b = tmp.split("=")[1];
        scanner.close();
        if (a.equals("dao") && b.equals("hibernate") ) {
            return new UserHibernateDAO(DBHelper.createSessionFactory());
        } else if (a.equals("dao") && b.equals("jdbc")) {
            return new UserJdbcDAO(DBHelper.getMysqlConnection());
        }
        return null;
    }
}
