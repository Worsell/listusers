package org.javalynx.servlet;

import org.javalynx.controller.service.UserService;
import org.javalynx.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/edit")
public class EditUserServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.err.println("GET: /edit");
        getServletContext()
                .getRequestDispatcher("/edit.jsp")
                .forward(req, resp);

        // resp.sendRedirect(req.getContextPath() + "/base");
    }


    //TODO
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.err.println("POST: /edit");

        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String password = req.getParameter("password");
        User user = new User(firstname, lastname, password);
        System.err.println(user);
        try {
            userService.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() + "/base");
    }
}
