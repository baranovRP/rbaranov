package ru.job4j.controller;

import com.google.gson.Gson;
import ru.job4j.dao.MetadataStore;
import ru.job4j.dao.PictureDaoImpl;
import ru.job4j.dao.PostDaoImpl;
import ru.job4j.dao.car.CarDaoImpl;
import ru.job4j.model.Metadata;
import ru.job4j.model.Picture;
import ru.job4j.model.Post;
import ru.job4j.model.User;
import ru.job4j.model.car.Car;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        String postad = req.getParameter("postad");
        Post post = new Gson().fromJson(postad, Post.class);
        post.setPublishDate(Timestamp.valueOf(LocalDateTime.now()));
        post.setUser(new User(1L));
        Long carId = new CarDaoImpl().create(post.getCar());
        post.setCar(new Car(carId));
        List<byte[]> pics = new ArrayList<>();
        InputStream in = null;
        for (Part p : req.getParts()) {
            if (!p.getName().equalsIgnoreCase("postad")) {
                in = p.getInputStream();
                pics.add(new byte[in.available()]);
            }
        }

        Long id = new PostDaoImpl().create(post);
        Post tempPost = new PostDaoImpl().findOne(id);
        pics.forEach(pic ->
            new PictureDaoImpl().create(new Picture(pic, tempPost)));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}

