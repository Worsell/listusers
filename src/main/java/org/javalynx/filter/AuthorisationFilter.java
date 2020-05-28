package org.javalynx.filter;

import org.javalynx.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;

@WebFilter(urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class AuthorisationFilter implements Filter {

    private UserService userService = UserService.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession(true);

        Pattern adminPattern = Pattern.compile("/admin.*");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        boolean loggedInAdmin = false;
        boolean islogged = true;
        if (session.getAttribute("id") != null && session.getAttribute("role") != null)
            // Убрать базу
            loggedInAdmin = session.getAttribute("role").equals("admin");
        else
            islogged = false;

        boolean allowedPathAdmin = adminPattern.matcher(path).find();
        boolean isjsp = Pattern.compile(".*.jsp").matcher(path).find();
        boolean isallowedAll = path.equals("/login") || path.equals("/logout");
        if ((loggedInAdmin && allowedPathAdmin) || isjsp || isallowedAll) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            if (!allowedPathAdmin && islogged) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else {
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
