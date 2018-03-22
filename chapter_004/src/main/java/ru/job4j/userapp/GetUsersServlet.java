package ru.job4j.userapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * User's servlet
 */
public class GetUsersServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(GetUsersServlet.class);

    private final UserStore users = UserStore.getInstance();

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) {
        resp.setContentType("text/html");
        String contextPath = req.getContextPath();
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("<a href='%s/add'>Add new user</a>", contextPath));
            sb.append("<ul>");
            for (User user : users.getAll()) {
                sb.append("<li>");
                sb.append(new UserStringTemplate().fillUserTemplate(user,
                    String.format("%s/update", contextPath), "Edit"));
                sb.append("<form action='")
                    .append(String.format("%s/remove", contextPath))
                    .append("' method='post'>");
                sb.append("<input type='hidden' id='")
                    .append(String.format("id-%d", user.getId()))
                    .append("' name='id' value='").append(user.getId()).append("'>");
                sb.append("<input type='submit' value='Delete'>");
                sb.append("</form>");
                sb.append("</li>");
            }
            sb.append("</ul>");
            writer.append(sb.toString());
            writer.flush();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
