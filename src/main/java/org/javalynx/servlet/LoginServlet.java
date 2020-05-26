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
                .getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String password = req.getParameter("password");
        User u = new User(firstname, lastname, password);
        User user = null;
        try {
            user = userService.getUserByFLP(u);
        } catch (SQLException e) {
            e.printStackTrace();
            getServletContext()
                    .getRequestDispatcher("/login")
                    .forward(req, resp);
        }
        HttpSession session = req.getSession(true);
        System.out.println(user.getId());
        session.setAttribute("id", user.getId());
        assert user.getRole() != null;
        switch (user.getRole()) {
            case "admin":
                resp.sendRedirect( "/admin");
                break;
            case "user":
                resp.sendRedirect( "/user");
                break;
            default:
        }

    }
}
