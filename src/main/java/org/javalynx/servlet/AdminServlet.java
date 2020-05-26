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
import java.util.ArrayList;
import java.util.List;


@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> list = new ArrayList<>();
        try {
            list = userService.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("usersFromServlet", list);

        getServletContext()
                        .getRequestDispatcher("/WEB-INF/admin.jsp")
                        .forward(req, resp);
    }

}
