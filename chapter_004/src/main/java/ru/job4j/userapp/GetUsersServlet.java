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

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) {
        resp.setContentType("text/html");
        String contextPath = req.getContextPath();
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            writer.append(String.format("%s/index.jsp", contextPath));
            writer.flush();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
