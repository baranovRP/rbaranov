package ru.job4j.controller;

import ru.job4j.dao.PictureDaoImpl;
import ru.job4j.dao.PostDaoImpl;
import ru.job4j.dao.car.CarDaoImpl;
import ru.job4j.model.Picture;
import ru.job4j.model.Post;
import ru.job4j.model.User;
import ru.job4j.model.car.Car;
import ru.job4j.model.car.parts.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.TRUE;

public class AddPostController extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/postad.html").forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        Post post = new Post()
            .setContent(req.getParameter("content"))
            .setPrice(Double.valueOf(req.getParameter("price")))
            .setIsActive(TRUE)
            .setCar(
                new Car()
                    .setBody(new Body().setId(Long.valueOf(req.getParameter("body"))))
                    .setCategory(new Category().setId(Long.valueOf(req.getParameter("category"))))
                    .setFuel(new Fuel().setId(Long.valueOf(req.getParameter("fuel"))))
                    .setCarModel(
                        new CarModel()
                            .setId(Long.valueOf(req.getParameter("carModel")))
                            .setManufacture(
                                new Manufacture()
                                    .setId(Long.valueOf(req.getParameter("manufacture")))))
                    .setTransmission(new Transmission().setId(Long.valueOf(req.getParameter("transmission"))))
                    .setEngineSize(Double.valueOf(req.getParameter("engineSize")))
                .setMileage(Long.valueOf(req.getParameter("mileage")))
                .setYear(Integer.valueOf(req.getParameter("year"))))
            .setUser(new User(1L))
            .setPublishDate(Timestamp.valueOf(LocalDateTime.now()));

        Long carId = new CarDaoImpl().create(post.getCar());
        post.setCar(new Car(carId));
        List<byte[]> pics = new ArrayList<>();

        for (Part p : req.getParts()) {
            if (p.getName().equalsIgnoreCase("pictures")) {
                byte[] pic;
                try (InputStream in = p.getInputStream()) {
                    pic = new byte[in.available()];
                    in.read(pic);
                }
                pics.add(pic);
            }
        }

        Long id = new PostDaoImpl().create(post);
        Post tempPost = new PostDaoImpl().findOne(id);
        pics.forEach(pic ->
            new PictureDaoImpl().create(new Picture(pic, tempPost)));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}

