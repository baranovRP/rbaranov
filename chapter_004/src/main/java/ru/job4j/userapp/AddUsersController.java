package ru.job4j.userapp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Add user's servlet.
 */
public class AddUsersController extends HttpServlet {

    private final UserStore users = UserStore.getInstance();

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("/WEB-INF/views/AddUserView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        String contextPath = req.getContextPath();
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        users.addUser(new User(name, login, email));
        resp.sendRedirect(String.format("%s/", contextPath));
    }
}
