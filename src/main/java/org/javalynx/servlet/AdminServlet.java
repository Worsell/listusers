package org.javalynx.servlet;

import org.javalynx.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                getServletContext()
                        .getRequestDispatcher("/admin.jsp")
                        .forward(req, resp);
    }

}
