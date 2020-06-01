package org.javalynx.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin", "/admin/*", "/user", }, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class AuthorisationFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession(true);
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (session.getAttribute("id") != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            response.sendRedirect( "/login");
        }
    }

    @Override
    public void destroy() {

    }
}
