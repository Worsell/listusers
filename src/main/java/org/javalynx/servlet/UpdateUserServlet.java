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


@WebServlet("/update")
public class UpdateUserServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);

        session.setAttribute("oldfirstname", req.getParameter("oldfirstname"));
        session.setAttribute("oldlastname", req.getParameter("oldlastname"));
        session.setAttribute("oldpassword", req.getParameter("oldpassword"));

        getServletContext()
                .getRequestDispatcher("/update.jsp")
                .forward(req, resp);

    }


    //TODO
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);

        String ofirstname = (String) session.getAttribute("oldfirstname");
        String olastname = (String) session.getAttribute("oldlastname");
        String opassword = (String) session.getAttribute("oldpassword");



        String nfirstname = req.getParameter("newfirstname");
        String nlastname = req.getParameter("newlastname");
        String npassword = req.getParameter("newpassword");

        User ouser = new User(ofirstname, olastname, opassword);
        User nuser = new User(nfirstname, nlastname, npassword);

        try {
            userService.updateUser(ouser, nuser);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
