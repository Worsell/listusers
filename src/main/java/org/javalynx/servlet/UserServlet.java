package org.javalynx.servlet;

import org.javalynx.model.User;
import org.javalynx.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = null;
        try {
            Object o = req.getSession(true).getAttribute("id");
            user = userService.getUserByID((Long)o);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("firstname", user.getFirstName());
        req.setAttribute("lastname", user.getLastName());
        req.setAttribute("role", user.getRole());
        req.setAttribute("password", user.getPassword());

        getServletContext()
                .getRequestDispatcher("/WEB-INF/user.jsp")
                .forward(req, resp);
    }

}
