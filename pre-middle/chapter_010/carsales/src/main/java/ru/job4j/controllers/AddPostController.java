package ru.job4j.controllers;

import com.google.gson.Gson;
import ru.job4j.dao.MetadataStore;
import ru.job4j.dao.PostDaoImpl;
import ru.job4j.models.Metadata;
import ru.job4j.models.Post;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AddPostController extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        Metadata data = new MetadataStore().getMetadata();
        resp.setContentType("text/json");
        PrintWriter wr = new PrintWriter(resp.getOutputStream());
        wr.append(new Gson().toJson(data));
        wr.flush();
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader().lines().sequential()
            .reduce(System.lineSeparator(), (accumulator, actual)
                -> accumulator + actual);
        Post post = new Gson().fromJson(body, Post.class);
        post.setPublishDate(Timestamp.valueOf(LocalDateTime.now()));
        new PostDaoImpl().create(post);
    }

}

