package ru.job4j.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.model.Post;
import ru.job4j.model.User;
import ru.job4j.service.PostService;

import javax.naming.AuthenticationException;

/**
 * Edit post (advertisement) controller.
 */
@RestController
public class EditPostController {

    @Autowired
    private PostService service;

    @PostMapping(value = "/editad")
    public void editPost(@RequestAttribute("user") User user,
                         @RequestParam("postad") final String postad)
        throws AuthenticationException {
        Post post = new Gson().fromJson(postad, Post.class);
        service.editPostAd(user, post);
    }
}

