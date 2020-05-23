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

@WebServlet("/delete")
public class DeleteUserServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String secondname = req.getParameter("lastname");
        String password = req.getParameter("password");

        User user = new User(firstname, secondname, password);
        try {
            userService.removeUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/login");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstname = req.getParameter("firstname");
        String secondname = req.getParameter("lastname");
        String password = req.getParameter("password");

        User user = new User(firstname, secondname, password);
        try {
            userService.removeUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        getServletContext()
                .getRequestDispatcher("/delete.jsp")
                .forward(req, resp);

        // resp.sendRedirect(req.getContextPath() + "/base");
    }
}
