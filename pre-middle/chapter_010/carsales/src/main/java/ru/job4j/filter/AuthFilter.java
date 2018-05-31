package ru.job4j.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Authorization filter
 */
public class AuthFilter implements Filter {

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse resp, final FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getRequestURI().contains("/login")) {
            chain.doFilter(req, resp);
        } else {
            HttpSession session = request.getSession();
            synchronized (session) {
                if (session.getAttribute("email") == null) {
                    ((HttpServletResponse) resp).sendRedirect("/WEB-INF/view/LoginView.jsp");
                    return;
                }
            }
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        //empty method
    }

    @Override
    public void destroy() {
        //empty method
    }
}
