package ru.job4j.userapp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Remove user's servlet
 */
public class RemoveUsersServlet extends HttpServlet {

    private final UserStore users = UserStore.getInstance();

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        users.delete(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect(String.format("%s/users", req.getContextPath()));
    }
}
