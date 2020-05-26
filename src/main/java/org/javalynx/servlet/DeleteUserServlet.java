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

@WebServlet("/admin/delete")
public class DeleteUserServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = new User();
        user.setId(Long.parseLong(req.getParameter("id")));

        try {
            userService.removeUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/admin");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = new User();
        user.setId(Long.parseLong(req.getParameter("id")));

        try {
            userService.removeUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/admin");

    }
}
