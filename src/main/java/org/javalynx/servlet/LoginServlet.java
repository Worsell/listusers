package org.javalynx.servlet;

import org.javalynx.model.User;
import org.javalynx.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher("/login.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST:LOGIN");

        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String password = req.getParameter("password");
        User user = new User(firstname, lastname, password);
        String role = null;
        try {
            role = userService.getStringAU(user);
        } catch (SQLException e) {
            e.printStackTrace();
            getServletContext()
                    .getRequestDispatcher("/login")
                    .forward(req, resp);
        }
        HttpSession session = req.getSession(true);
        session.setAttribute("firstname", firstname);
        session.setAttribute("lastname", lastname);
        session.setAttribute("password", password);
        assert role != null;
        System.out.println(role);
        switch (role) {
            case "admin":
                System.out.println(firstname);
                System.out.println(lastname);
                System.out.println(password);
                resp.sendRedirect( "/admin");
                break;
            case "user":

                resp.sendRedirect( "/user");
                break;
            default:
        }

    }
}
