package ru.job4j.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.model.Post;
import ru.job4j.model.User;
import ru.job4j.service.PostService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Add post (advertisement) controller.
 */
@RestController
public class AddPostController {

    @Autowired
    private PostService service;

    @PostMapping(value = "/postad"/*, consumes = "multipart/form-data;charset=UTF-8"*/)
    public void addPost(@RequestAttribute("user") User user,
                        @RequestParam("postad") final String postad,
                        @RequestParam("pic") List<MultipartFile> files) throws IOException {
        Post post = new Gson().fromJson(postad, Post.class);
        List<byte[]> pics = readPics(files);
        service.addPostAd(user, post, pics);
    }

    private List<byte[]> readPics(final List<MultipartFile> parts) throws IOException {
        List<byte[]> pics = new ArrayList<>();
        for (MultipartFile p : parts) {
            if (!p.getName().equalsIgnoreCase("postad")) {
                byte[] pic;
                try (InputStream in = p.getInputStream()) {
                    pic = new byte[in.available()];
                    in.read(pic);
                }
                pics.add(pic);
            }
        }
        return pics;
    }
}

