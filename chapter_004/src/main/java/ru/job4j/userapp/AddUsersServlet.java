package ru.job4j.userapp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Add user's servlet.
 */
public class AddUsersServlet extends HttpServlet {

    private final UserStore users = UserStore.getInstance();

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        String contextPath = req.getContextPath();
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            StringBuilder sb = new StringBuilder();
            sb.append(new UserStringTemplate().fillUserTemplate(new User(),
                String.format("%s/add", contextPath), "Add"));
            writer.append(sb.toString());
            writer.flush();
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        String contextPath = req.getContextPath();
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        users.addUser(new User(name, login, email));
        resp.sendRedirect(String.format("%s/users", contextPath));
    }
}
