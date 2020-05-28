package org.javalynx;

import org.javalynx.dao.UserDAO;
import org.javalynx.dao.UserHibernateDAO;
import org.javalynx.dao.UserJdbcDAO;
import org.jetbrains.annotations.Nullable;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class DAOFactory {
    @Nullable
    public static UserDAO getDAO() throws FileNotFoundException {

        //System.err.println();
        Scanner scanner = new Scanner(DAOFactory.class.getClassLoader().getResourceAsStream("dao.properties"));
        String tmp = scanner.nextLine();
        String a = tmp.split("=")[0];
        // System.out.println(a);
        String b = tmp.split("=")[1];
        //System.out.println(b);

        if (a.equals("dao") && b.equals("hibernate") ) {
            return new UserHibernateDAO(DBHelper.createSessionFactory());
        } else if (a.equals("dao") && b.equals("jdbc")) {
            return new UserJdbcDAO(DBHelper.getMysqlConnection());
        }
        return null;
    }
}
