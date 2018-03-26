package ru.job4j.userapp.servlets;

import ru.job4j.userapp.UserDaoImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Remove user's servlet
 */
public class RemoveUsersController extends HttpServlet {

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        new UserDaoImpl().deleteUser(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
