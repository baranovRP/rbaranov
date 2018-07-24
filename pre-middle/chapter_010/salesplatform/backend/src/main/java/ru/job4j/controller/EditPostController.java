package ru.job4j.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.PostRepository;
import ru.job4j.model.Post;
import ru.job4j.model.User;

import javax.naming.AuthenticationException;

/**
 * Edit advertisement controller.
 */
@RestController
public class EditPostController {

    @PostMapping(value = "/editad"/*, consumes = "multipart/form-data;charset=UTF-8"*/)
    public void editPost(@RequestAttribute("user") User user,
                         @RequestParam("postad") final String postad)
        throws AuthenticationException {
        Post post = new Gson().fromJson(postad, Post.class);
        new PostRepository().editPostAd(user, post);
    }
}

