package ru.job4j.controller;

import ru.job4j.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Signin servlet.
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("/WEB-INF/view/LoginView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException, ServletException {
        String email = req.getParameter("email");
        String passw = req.getParameter("passw");

        if (new UserRepository().isCredential(email, passw)) {
            HttpSession session = req.getSession();
            synchronized (session) {
                session.setAttribute("email", email);
            }
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error", "Credentials is invalid");
            doGet(req, resp);
        }
    }
}