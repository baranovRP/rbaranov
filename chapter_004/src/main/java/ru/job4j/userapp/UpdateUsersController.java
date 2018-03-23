package ru.job4j.userapp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Update user's servlet
 */
public class UpdateUsersController extends HttpServlet {

    private final UserStore users = UserStore.getInstance();

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        users.replace(id, new User(name, login, email));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
