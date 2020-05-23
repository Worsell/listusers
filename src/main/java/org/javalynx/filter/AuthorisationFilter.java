package org.javalynx.filter;

import org.javalynx.model.User;
import org.javalynx.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

@WebFilter(urlPatterns = {"/admin", "/user", "/create", "/delete", "/update"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class AuthorisationFilter implements Filter {

    private UserService userService = UserService.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession(true);
        String firstname = (String) session.getAttribute("firstname");
        String lastname = (String) session.getAttribute("lastname");
        String password = (String) session.getAttribute("password");

        if (firstname == null || lastname == null || password == null) {
            ((HttpServletResponse) servletResponse).sendRedirect("/login");
            return;
        }
        User user = new User(firstname, lastname, password);
        try {
            // System.err.println(((HttpServletRequest) servletRequest)
             //       .getMethod());
            System.err.println("AAA");
            System.err.println(((HttpServletRequest) servletRequest)
                    .getServletPath());
            System.err.println("AAA");
            if (Objects.equals(((HttpServletRequest) servletRequest)
                    .getMethod(), "GET") && Objects.equals(((HttpServletRequest) servletRequest)
                    .getServletPath(), "/admin")) {
                System.out.println("FILTER::ADMIN");
                System.err.println(user);
                if (Objects.equals(userService.getStringAU(user), "admin")) {
                    System.err.println("FILTER::IF");
                    filterChain.doFilter(servletRequest, servletResponse);
                }
                else {
                    System.err.println("FILTER::ELSE");

                    servletRequest
                            .getRequestDispatcher("/login")
                            .forward(servletRequest, servletResponse);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
