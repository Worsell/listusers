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

@WebServlet("/admin/create")
public class CreateUserServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        User user = new User(firstname, lastname, password);
        user.setRole(role);

        try {
            userService.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/admin");
    }

}
