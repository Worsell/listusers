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


@WebServlet("/admin/update")
public class UpdateUserServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher("/WEB-INF/update.jsp")
                .forward(req, resp);

    }


    //TODO
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = new User(req.getParameter("firstname"), req.getParameter("lastname"), req.getParameter("password"));
        user.setRole(req.getParameter("role"));
        user.setId(Long.parseLong(req.getParameter("id")));
        try {
            userService.updateUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect( "/admin");
    }
}
